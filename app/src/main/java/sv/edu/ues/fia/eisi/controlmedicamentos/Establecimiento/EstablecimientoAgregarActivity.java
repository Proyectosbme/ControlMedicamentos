package sv.edu.ues.fia.eisi.controlmedicamentos.Establecimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Enfermedad;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Establecimiento;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class EstablecimientoAgregarActivity extends AppCompatActivity {
    private BDMedicamentosControl.DatabaseHelper DBH;
    private ArrayAdapter<CharSequence> adaptador;
    private BDMedicamentosControl helper;
    private Spinner comboUsuarioEsta;
    private EditText ediNombre,ediDireccion,ediTelefono;
    private String nombre,direccion,telefono,idUsuario;
    private ArrayList<String> listaPersonasUsuario;
    private ArrayList<Usuario> ListUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento_agregar);
        ediNombre=(EditText) findViewById(R.id.ediNomUsuarioEstable);
        comboUsuarioEsta=(Spinner) findViewById(R.id.comboEstablcimientoUsuario);
        ediDireccion=(EditText) findViewById(R.id.ediDireccionEstablecimiento);
        ediTelefono=(EditText) findViewById(R.id.ediEstablecimientoTelefono);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        helper=new BDMedicamentosControl(this);
        consultarListaPersonas();
        adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPersonasUsuario);
        comboUsuarioEsta.setAdapter(adaptador);
        comboUsuarioEsta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    idUsuario=ListUsuario.get(i-1).getIdUsuario();
                     }
                else{
                    idUsuario="";
                    nombre="";
                    direccion="";
                    telefono="";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void consultarListaPersonas() {
        SQLiteDatabase db=DBH.getReadableDatabase();
        ListUsuario=new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("select idUsuario,nombre,apellido from usuario", null);

        while (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(cursor.getString(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setApellido(cursor.getString(2));
            ListUsuario.add(usuario);
        }
        obtenerLista2();
    }

    private void obtenerLista2() {
        listaPersonasUsuario = new ArrayList<String>();
        listaPersonasUsuario.add("Seleccione");
        for (int i=0; i<ListUsuario.size();i++)
        {
            listaPersonasUsuario.add("Nombre :"+ListUsuario.get(i).getNombre()+" "+ListUsuario.get(i).getApellido()+"\n");
        }
    }

    public void Agregar_establecimiento(View view) {
        try {
            if (idUsuario!=""){
                nombre=ediNombre.getText().toString();
                direccion=ediDireccion.getText().toString();
                telefono=ediTelefono.getText().toString();
                if (nombre!="" || direccion!="" || telefono!=""){

                            String regInsertados;
                            Establecimiento establecimiento=new Establecimiento();
                            establecimiento.setIdUsuario(idUsuario);
                            establecimiento.setNombre(nombre);
                            establecimiento.setDireccion(direccion);
                            establecimiento.setTelefono(telefono);
                            helper.abrir();
                            regInsertados=helper.insertarEstablecimiento(establecimiento);
                            helper.cerrar();
                            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
                    nombre="";
                    direccion="";
                    telefono="";
                    ediNombre.setText("");
                    ediDireccion.setText("");
                    ediTelefono.setText("");
                }else
                {
                    Toast.makeText(this,"llene todos los campos" , Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"seleccione un usuario" , Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }


}