package sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Contacto;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Enfermedad;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class EnfermedadesConsultarActivity extends AppCompatActivity {

    private Spinner comboUsuario;
    private ListView listviewEnfermedades;
    private ArrayList<String> listaInformacion;
    private ArrayList<String> listaPersonas;
    private ArrayList<Usuario> PersonasList;
    private ArrayList<Enfermedad> listaUsuario;
    private String idUsuario,nombreUsuario,apellidoUser;

    private BDMedicamentosControl.DatabaseHelper DBH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_consultar);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        comboUsuario=(Spinner) findViewById(R.id.cbEnfermedadConsulta);
        listviewEnfermedades = (ListView) findViewById(R.id.lsVConsultarEnfermedad);

        consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPersonas);
        comboUsuario.setAdapter(adaptador);
        comboUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    idUsuario=PersonasList.get(i-1).getIdUsuario();
                    nombreUsuario=PersonasList.get(i-1).getNombre();
                    apellidoUser=PersonasList.get(i-1).getApellido();
                }
                else{}
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=DBH.getReadableDatabase();
        Usuario persona=null;
        PersonasList=new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("select idUsuario,nombre,apellido from usuario", null);

        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(cursor.getString(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setApellido(cursor.getString(2));
            PersonasList.add(usuario);
        }
        obtenerLista2();

    }

    private void obtenerLista2() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i=0; i<PersonasList.size();i++)
        {
            listaPersonas.add("Nombre :"+PersonasList.get(i).getNombre()+" "+PersonasList.get(i).getApellido()+"\n");
        }
    }

    public void consultarUsuarios2() {
        try {
            SQLiteDatabase db=DBH.getReadableDatabase();
            listaUsuario= new ArrayList<Enfermedad>();
            String[] id1 = {idUsuario};
            Cursor cursor = db.rawQuery(
                    "select idUsuario,nombreEnfermedad,fecha,tipo from enfermedad "+
                            "where idUsuario =?",id1, null);

            while (cursor.moveToNext()){
                Enfermedad enfermedad = new Enfermedad();
                enfermedad.setIdUsuario(cursor.getString(0));
                enfermedad.setNombre(cursor.getString(1));
                enfermedad.setFecha(cursor.getString(2));
                enfermedad.setTipo(cursor.getString(3));
                listaUsuario.add(enfermedad);
            }
            obtenerLista();
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i=0; i<listaUsuario.size();i++){
            listaInformacion.add("Nombre Usuario :"+nombreUsuario+" "+apellidoUser+"\n"+
                                 "Enfermedad :"+listaUsuario.get(i).getNombre()+"\n"+
                                 "Fecha :"+listaUsuario.get(i).getFecha()+"\n"+
                                 "tipo :"+listaUsuario.get(i).getTipo());
        }
    }

    public void Cond_enfermedad(View view) {
        try{
            consultarUsuarios2();
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaInformacion);
            if (listaInformacion.size()!=0){
                listviewEnfermedades.setAdapter(adapter);

            }else{
                Toast.makeText(this,"No hay enfermdedad para el usuario " , Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }

    }
}
