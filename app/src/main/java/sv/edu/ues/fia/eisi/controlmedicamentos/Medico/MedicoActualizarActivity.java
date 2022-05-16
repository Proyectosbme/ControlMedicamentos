package sv.edu.ues.fia.eisi.controlmedicamentos.Medico;

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
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MedicoActualizarActivity extends AppCompatActivity {

    private EditText ediNombreM,EdiEspecialidadM;
    private String NombreMA,EspecialidadMA,usuarioidA;
    private Spinner comboUsuario;
    private ArrayList<String> listaPersonas;
    private ArrayList<Medico> PersonasList;
    private BDMedicamentosControl.DatabaseHelper conn;
    private BDMedicamentosControl helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_actualizar);
        comboUsuario=(Spinner) findViewById(R.id.comboMedico);
        ediNombreM=(EditText)findViewById(R.id.ediMANombre);
        EdiEspecialidadM=(EditText)findViewById(R.id.ediMAEspecialidad);
        helper=new BDMedicamentosControl(this);
        conn = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());

        consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPersonas);
        comboUsuario.setAdapter(adaptador);
        comboUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    usuarioidA=PersonasList.get(i-1).getIdUsuariom();
                }
                else{

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Medico persona=null;
        PersonasList=new ArrayList<Medico>();
        Cursor cursor=db.rawQuery("select idUsuario,nombre,especialidad from medico ", null);

        while (cursor.moveToNext()){
            Medico medico = new Medico();
            medico.setIdUsuariom(cursor.getString(0));
            medico.setNombre(cursor.getString(1));
            medico.setEspecialidad(cursor.getString(2));
            PersonasList.add(medico);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i=0; i<PersonasList.size();i++){
            listaPersonas.add("IdU"+PersonasList.get(i).getIdUsuariom()+"-->"+
                    "Nombre :"+PersonasList.get(i).getNombre()+"--"+PersonasList.get(i).getEspecialidad()+"\n");
        }
    }

     public void Actualizar_Medico(View view) {
        NombreMA= ediNombreM.getText().toString();
        EspecialidadMA=EdiEspecialidadM.getText().toString();
        String regInsertados;
        Medico medicos  =new Medico();
        medicos.setIdUsuariom(usuarioidA);
        medicos.setNombre(NombreMA);
        medicos.setEspecialidad(EspecialidadMA);

         Medico medico =new Medico();
         medico.setIdUsuariom(usuarioidA);
        try{
            helper.abrir();
            regInsertados=helper.actualizarMedico(medicos,medico);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
}