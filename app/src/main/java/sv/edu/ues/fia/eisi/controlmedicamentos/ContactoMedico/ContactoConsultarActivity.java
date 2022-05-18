package sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Contacto;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class ContactoConsultarActivity extends AppCompatActivity {

    private ListView listviewpersonas;
    private ArrayList<String> listaInformacion;
    private ArrayList<Contacto> listaUsuario;
    private BDMedicamentosControl.DatabaseHelper DBH;
    private String idMedico;
    String nombreb;
    ///_________________________________________________________________
    private Spinner comboUsuario;
    private ArrayList<String> listaPersonas;
    private ArrayList<Medico> PersonasList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_consultar);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        listviewpersonas = (ListView) findViewById(R.id.lsVConsultarContacto);

        //____________________________________________________________
        comboUsuario=(Spinner) findViewById(R.id.cbContactoconsulta);
        consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPersonas);
        comboUsuario.setAdapter(adaptador);
        comboUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    idMedico=PersonasList.get(i-1).getIdMedico();
                }
                else{}
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }//__________________________SPENNER
    private void consultarListaPersonas() {
        SQLiteDatabase db=DBH.getReadableDatabase();
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
        obtenerLista2();

    }
    private void obtenerLista2() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i=0; i<PersonasList.size();i++)
        {
            listaPersonas.add("Id :"+PersonasList.get(i).getIdMedico()+"-->"+
                    "Nombre :"+PersonasList.get(i).getNombre()+" "+PersonasList.get(i).getEspecialidad()+"\n");
        }
    }




//_____________________________________________LIST VIEW
    public void consultarUsuarios2() {
        try {


        SQLiteDatabase db=DBH.getReadableDatabase();
        listaUsuario= new ArrayList<Contacto>();
        String[] id1 = {idMedico};
        Cursor cursor = db.rawQuery(
                "select idUsuario,idMedico,direccion,telefono from medicoContacto "+
                        "where idMedico =?",id1, null);

        while (cursor.moveToNext()){
            Contacto contacto = new Contacto();
            contacto.setIdUsuario(cursor.getString(0));
            contacto.setIdMedico(cursor.getString(1));
            contacto.setDireccion(cursor.getString(2));
            contacto.setTelefono(cursor.getString(3));
            listaUsuario.add(contacto);
        }
        obtenerLista();
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i=0; i<listaUsuario.size();i++){
            listaInformacion.add(
                    "Id :"+listaUsuario.get(i).getIdUsuario()+"\n"+
                            "IdMedico :"+listaUsuario.get(i).getIdMedico()+"\n"+
                              "Direccion :"+listaUsuario.get(i).getDireccion()+"\n"+
                            "Telefono :"+listaUsuario.get(i).getTelefono());
        }
    }

    public void COND_CONTACTO(View view) {
        //_______________________________________

        try{
            consultarUsuarios2();
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaInformacion);
            if (listaInformacion.size()!=0){
                listviewpersonas.setAdapter(adapter);

            }else{
                Toast.makeText(this,"No hay usuarios con el nombre "+nombreb , Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
}
