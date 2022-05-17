package sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Contacto;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class ContactoAgregarActivity extends AppCompatActivity {
    private EditText ediDireccion,EdiTelefono;
    private String direccion,telefono,usuarioid,medicoid;
    private Spinner comboUsuario;
    private ArrayList<String> listaPersonas;
    private ArrayList<Medico> PersonasList;
    private BDMedicamentosControl.DatabaseHelper conn;
    private BDMedicamentosControl helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_agregar);
        helper=new BDMedicamentosControl(this);
        comboUsuario=(Spinner) findViewById(R.id.comboMcontacto);
        ediDireccion=(EditText)findViewById(R.id.ediMediCNombre);
        EdiTelefono=(EditText)findViewById(R.id.ediMediCspecialidad);
        conn = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());

        consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPersonas);
        comboUsuario.setAdapter(adaptador);
        comboUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    medicoid=PersonasList.get(i-1).getIdMedico();
                    usuarioid =PersonasList.get(i-1).getIdUsuariom();
                }
                else{}
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Usuario persona=null;
        PersonasList=new ArrayList<Medico>();
        Cursor cursor=db.rawQuery("select idMedico,idUsuario,nombre,especialidad from medico ", null);

        while (cursor.moveToNext()){
            Medico medico = new Medico();
            medico.setIdMedico(cursor.getString(0));
            medico.setIdUsuariom(cursor.getString(1));
            medico.setNombre(cursor.getString(2));
            medico.setEspecialidad(cursor.getString(3));
            PersonasList.add(medico);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i=0; i<PersonasList.size();i++)
        {
            listaPersonas.add("Id :"+PersonasList.get(i).getIdMedico()+"-->"+
                    "Nombre :"+PersonasList.get(i).getNombre()+" "+PersonasList.get(i).getEspecialidad()+"\n");
        }
    }
    public void Contacto_agregar(View view) {

        try {
        direccion= ediDireccion.getText().toString();
        telefono=EdiTelefono.getText().toString();

        String regInsertados;
        Contacto contacto =new Contacto();
        contacto.setIdMedico(medicoid);
        contacto.setIdUsuario(usuarioid);
        contacto.setDireccion(direccion);
        contacto.setTelefono(telefono);
        Toast.makeText(this,medicoid+usuarioid+direccion+telefono , Toast.LENGTH_SHORT).show();


            helper.abrir();
            regInsertados=helper.insertarContacto(contacto);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }

        catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }

    }
}