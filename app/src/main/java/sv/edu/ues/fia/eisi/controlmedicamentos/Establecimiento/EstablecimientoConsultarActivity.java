package sv.edu.ues.fia.eisi.controlmedicamentos.Establecimiento;

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
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Establecimiento;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class EstablecimientoConsultarActivity extends AppCompatActivity {
    private ListView listviewEstablecimiento;
    private ArrayList<String> listaInformacion;
    private ArrayList<Usuario> listaUsuario;
    private ArrayList<String> listaInformacionEstablecimiento;
    private ArrayList<Establecimiento> listaEstablecimiento;
    private BDMedicamentosControl.DatabaseHelper DBH;
    private String idUsuario;
    private Spinner comboUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento_consultar);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        listviewEstablecimiento = (ListView) findViewById(R.id.lsVConsultarEstab);

        //____________________________________________________________
        comboUsuario=(Spinner) findViewById(R.id.cbconsultaEstab);
        consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaInformacion);
        comboUsuario.setAdapter(adaptador);
        comboUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){

                     idUsuario=listaUsuario.get(i-1).getIdUsuario();

                                    }
                else{ }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=DBH.getReadableDatabase();
        Usuario persona=null;
        listaUsuario=new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("select idUsuario,nombre,apellido from usuario ", null);

        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(cursor.getString(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setApellido(cursor.getString(2));
            listaUsuario.add(usuario);
        }
        obtenerLista2();

    }
    private void obtenerLista2() {
        listaInformacion = new ArrayList<String>();
        listaInformacion.add("Seleccione");
        for (int i=0; i<listaUsuario.size();i++)
        {
            listaInformacion.add("Nombre :"+listaUsuario.get(i).getNombre()+"---"+listaUsuario.get(i).getApellido()+"\n");
        }
    }

    //_____________________________________________LIST VIEW
    public void consultarEstablecimiento() {
        try {
            SQLiteDatabase db=DBH.getReadableDatabase();
            listaEstablecimiento= new ArrayList<Establecimiento>();
            String[] id1 = {idUsuario};
            Cursor cursor = db.rawQuery(
                    "select idUsuario,nombre,direccion,telefono from establecimiento "+
                            "where idUsuario =?",id1, null);

            while (cursor.moveToNext()){
                Establecimiento establecimiento = new Establecimiento();
                establecimiento.setIdUsuario(cursor.getString(0));
                establecimiento.setNombre(cursor.getString(1));
                establecimiento.setDireccion(cursor.getString(2));
                establecimiento.setTelefono(cursor.getString(3));
                listaEstablecimiento.add(establecimiento);
            }
            obtenerLista();
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
    private void obtenerLista() {
        if (listaUsuario.size()!=0){
            listaInformacionEstablecimiento = new ArrayList<String>();
            for (int i=0; i<listaEstablecimiento.size();i++){
                listaInformacionEstablecimiento.add(
                        "nombre :"+listaEstablecimiento.get(i).getNombre()+"\n"+
                        "Direccion :"+listaEstablecimiento.get(i).getDireccion()+"\n"+
                        "Telefono :"+listaEstablecimiento.get(i).getTelefono()+"\n"+
                        "---------------------------------------------------------");
            }
        }else{
            Toast.makeText(this,"No hay establecimiento para el usuario selecionado :", Toast.LENGTH_SHORT).show();
            listaInformacion = new ArrayList<String>();
            listaInformacion.add(
                    "Nombre :\n"+
                    "Direccion :\n"+
                    "Telefono :");
        }
    }
    public void cond_estab(View view) {
        try{
            consultarEstablecimiento();
            if (listaInformacionEstablecimiento.size()!=0){
                ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaInformacionEstablecimiento);
                listviewEstablecimiento.setAdapter(adapter);
            }else{
                listaInformacionEstablecimiento.clear();
                ArrayAdapter adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaInformacionEstablecimiento);
                listviewEstablecimiento.setAdapter(adapter2);
                Toast.makeText(this,"No hay establecimiento para el usuario selecionado:", Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }

}