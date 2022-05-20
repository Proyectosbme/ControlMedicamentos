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

public class ContactoActualizarActivity extends AppCompatActivity {
    private EditText ediDireccion,EdiTelefono;
    private String direccion,telefono,usuarioid;
    private Spinner comboUsuario;
    private ArrayList<Contacto> listaContacto;
    private ArrayList<String> listaPersonas;
    private ArrayList<Medico> PersonasList;
    private BDMedicamentosControl.DatabaseHelper conn;
    private BDMedicamentosControl helper;
    boolean res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_actualizar);
        helper=new BDMedicamentosControl(this);
        comboUsuario=(Spinner) findViewById(R.id.cbMAccontacto);
        ediDireccion=(EditText)findViewById(R.id.ediMAcNombre);
        EdiTelefono=(EditText)findViewById(R.id.ediMAcspecialidad);
        conn = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPersonas);
        comboUsuario.setAdapter(adaptador);
        listaContacto=new ArrayList<Contacto>();
        SQLiteDatabase db=conn.getReadableDatabase();
        comboUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    listaContacto.clear();
                    ediDireccion.setText("");
                    EdiTelefono.setText("");
                    usuarioid=PersonasList.get(i-1).getIdMedico();

                    String[] id1 = {usuarioid};
                    Cursor cursor = db.rawQuery(
                            "select idMedico,direccion,telefono from medicoContacto "+
                                    "where idMedico =?",id1, null);

                    while (cursor.moveToNext()){
                        Contacto contacto = new Contacto();
                        contacto.setIdMedico(cursor.getString(0));
                        contacto.setDireccion(cursor.getString(1));
                        contacto.setTelefono(cursor.getString(2));
                        listaContacto.add(contacto);
                    }
                    if (listaContacto.size()!=0){
                        ediDireccion.setText(listaContacto.get(i-1).getDireccion());
                        EdiTelefono.setText(listaContacto.get(i-1).getTelefono());
                        res =true;
                    }else{
                        res=false;
                    }


                }
                else{
                    ediDireccion.setText("");
                    EdiTelefono.setText("");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();
        PersonasList=new ArrayList<Medico>();
        Cursor cursor=db.rawQuery("select idMedico,nombre,especialidad from medico ", null);

        while (cursor.moveToNext()){
            Medico medico = new Medico();
            medico.setIdMedico(cursor.getString(0));
            medico.setNombre(cursor.getString(1));
            medico.setEspecialidad(cursor.getString(2));
            PersonasList.add(medico);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i=0; i<PersonasList.size();i++)
        {
            listaPersonas.add("Nombre :"+PersonasList.get(i).getNombre()+" Especialidad :"+PersonasList.get(i).getEspecialidad()+"\n");
        }
    }

    public void Contacto_Actualizar(View view) {

        try {
            if (res){


        direccion= ediDireccion.getText().toString();
        telefono=EdiTelefono.getText().toString();

        String regInsertados;
        Contacto contacto =new Contacto();
        contacto.setIdMedico(usuarioid);
        contacto.setDireccion(direccion);
        contacto.setTelefono(telefono);

            helper.abrir();
            regInsertados=helper.actualizarContacto(contacto);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "agregar primero el contacto", Toast.LENGTH_SHORT).show();

            }
        }
        catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
}