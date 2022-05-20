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
    private ArrayList<String> listaInformacionEnfermedad;
    private ArrayList<String> listaPersonasUsuario;
    private ArrayList<Usuario> PersonasListUsuario;
    private ArrayList<Enfermedad> listaEnfermedad;
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
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPersonasUsuario);
        comboUsuario.setAdapter(adaptador);
        comboUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    idUsuario=PersonasListUsuario.get(i-1).getIdUsuario();
                    nombreUsuario=PersonasListUsuario.get(i-1).getNombre();
                    apellidoUser=PersonasListUsuario.get(i-1).getApellido();
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
        PersonasListUsuario=new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("select idUsuario,nombre,apellido from usuario", null);

        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(cursor.getString(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setApellido(cursor.getString(2));
            PersonasListUsuario.add(usuario);
        }
        obtenerLista2();

    }

    private void obtenerLista2() {
        listaPersonasUsuario = new ArrayList<String>();
        listaPersonasUsuario.add("Seleccione");
        for (int i=0; i<PersonasListUsuario.size();i++)
        {
            listaPersonasUsuario.add("Nombre :"+PersonasListUsuario.get(i).getNombre()+" "+PersonasListUsuario.get(i).getApellido()+"\n");
        }
    }

    public void consultarUsuarios2() {
        try {
            SQLiteDatabase db=DBH.getReadableDatabase();
            listaEnfermedad= new ArrayList<Enfermedad>();
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
                listaEnfermedad.add(enfermedad);
            }
            obtenerLista();
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
    private void obtenerLista() {
        listaInformacionEnfermedad = new ArrayList<String>();
        for (int i=0; i<listaEnfermedad.size();i++){
            listaInformacionEnfermedad.add("Nombre Usuario :"+nombreUsuario+" "+apellidoUser+"\n"+
                                 "Enfermedad :"+listaEnfermedad.get(i).getNombre()+"\n"+
                                 "Fecha :"+listaEnfermedad.get(i).getFecha()+"\n"+
                                 "tipo :"+listaEnfermedad.get(i).getTipo());
        }
    }

    public void Cond_enfermedad(View view) {
        try{
            consultarUsuarios2();
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaInformacionEnfermedad);
            if (listaInformacionEnfermedad.size()!=0){
                listviewEnfermedades.setAdapter(adapter);

            }else{
                Toast.makeText(this,"No hay enfermdedad para el usuario " , Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }

    }
}
