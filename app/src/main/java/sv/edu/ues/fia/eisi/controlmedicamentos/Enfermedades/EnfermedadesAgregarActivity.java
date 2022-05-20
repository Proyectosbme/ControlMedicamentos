package sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Contacto;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Enfermedad;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class EnfermedadesAgregarActivity extends AppCompatActivity {
    private EditText ediNombreEnfermedad,ediNombre,editipo;
    private Spinner spTipo;
    private String NombreEnf,fecha,idMedico,tipo;
    private Spinner comboEMedico;
    private ArrayList<String> listaPersonasUsuario;
    private ArrayList<String> listaPersonasMedico;
    private  int dia,mes,ano;
    private BDMedicamentosControl helper;

    private TextView etifecha;
    private ArrayList<Medico> PersonasListMedico;
    private ArrayList<Usuario> PersonasListUsuario;
    private BDMedicamentosControl.DatabaseHelper conn;
    private ArrayAdapter<CharSequence> adaptador;
    private String idUsuarioEnfermedad="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_agregar);
        comboEMedico=(Spinner) findViewById(R.id.comboUsuarioEnfermedad);
        ediNombre=(EditText) findViewById(R.id.EdiUsuarioenferm);
        etifecha=(TextView) findViewById(R.id.ediFechaEnfermedad);
        ediNombreEnfermedad=(EditText) findViewById(R.id.ediNombreEnfermedad);
        editipo=(EditText)findViewById(R.id.ediTipoEnfermedad);


        conn = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());

        //______________________________________________________________________________________________
        consultarListaPersonas();
        SQLiteDatabase db=conn.getReadableDatabase();
        PersonasListUsuario=new ArrayList<Usuario>();
        helper=new BDMedicamentosControl(this);

        listaPersonasUsuario = new ArrayList<String>();
        adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPersonasMedico);
        comboEMedico.setAdapter(adaptador);
        comboEMedico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                   PersonasListUsuario.clear();
                    listaPersonasUsuario.clear();

                    idUsuarioEnfermedad=PersonasListMedico.get(i-1).getIdUsuariom();
                    idMedico=PersonasListMedico.get(i-1).getIdMedico();

                    String[] id1 = {idUsuarioEnfermedad};
                    Cursor cursor = db.rawQuery("select idUsuario,nombre,apellido from usuario "+
                            "where idUsuario =?",id1, null);
                    while (cursor.moveToNext()){
                        Usuario usuario1 = new Usuario();
                        usuario1.setIdUsuario(cursor.getString(0));
                        usuario1.setNombre(cursor.getString(1));
                        usuario1.setApellido(cursor.getString(2));
                        PersonasListUsuario.add(usuario1);
                                                  }
                    listaPersonasUsuario.add("Seleccione");
                    for (int f=0; f<PersonasListUsuario.size();f++)
                    {
                        listaPersonasUsuario.add("Nombre :"+PersonasListUsuario.get(f).getNombre()+" Especialidad :"+PersonasListUsuario.get(f).getApellido()+"\n");
                    }

                    ediNombre.setText(PersonasListUsuario.get(0).getNombre()+" " +PersonasListUsuario.get(0).getApellido());

                }
                else{}
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

    }
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();
        PersonasListMedico=new ArrayList<Medico>();
        Cursor cursor=db.rawQuery("select idMedico,idUsuario,nombre,especialidad from medico ",
                null);

      while (cursor.moveToNext()){
                        Medico medico = new Medico();
                        medico.setIdMedico(cursor.getString(0));
                        medico.setIdUsuariom(cursor.getString(1));
                        medico.setNombre(cursor.getString(2));
                        medico.setEspecialidad(cursor.getString(3));
                        PersonasListMedico.add(medico);
        }
        obtenerListaUsuario();

    }

    private void obtenerListaUsuario() {
        listaPersonasMedico = new ArrayList<String>();
        listaPersonasMedico.add("Seleccione");
        if (PersonasListMedico.size() !=0){


        for (int f=0; f<PersonasListMedico.size();f++)
        {
            listaPersonasMedico.add("Nombre :"+PersonasListMedico.get(f).getNombre()+" Especialidad :"+PersonasListMedico.get(f).getEspecialidad()+"\n");
        }
        }else{
            Toast.makeText(this,"seleccione Medico" , Toast.LENGTH_SHORT).show();

        }
    }

    public void fecha_infermedad(View view) {
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                etifecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
            }
        }
                ,dia,mes,ano);
        datePickerDialog.show();
    }

    public void Agregar_enfermedad(View view) {

        try {
            NombreEnf=ediNombreEnfermedad.getText().toString();
            fecha=etifecha.getText().toString();
            tipo=editipo.getText().toString();

                  String regInsertados;
                 Enfermedad enfermedad =new Enfermedad();

                  enfermedad.setIdUsuario(idUsuarioEnfermedad);
                  enfermedad.setIdMedico(idMedico);
                  enfermedad.setNombre(NombreEnf);
                  enfermedad.setFecha(fecha);
                  enfermedad.setTipo(tipo);

                  helper.abrir();
                  regInsertados=helper.insertarEnfermedad(enfermedad);
                  helper.cerrar();

                  Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();


              }catch (Exception e){
                  Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();

        }

    }


}