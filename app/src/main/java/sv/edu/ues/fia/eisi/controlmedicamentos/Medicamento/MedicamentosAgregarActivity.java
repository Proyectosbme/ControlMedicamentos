package sv.edu.ues.fia.eisi.controlmedicamentos.Medicamento;

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
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medicamento;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MedicamentosAgregarActivity extends AppCompatActivity {

    private ArrayList<String> listaPersonasEnfermedad;
    private ArrayList<Enfermedad> PersonasListEnfermedad;
    private BDMedicamentosControl.DatabaseHelper conn;
    private ArrayAdapter<CharSequence> adaptador;
    private BDMedicamentosControl helper;
    private Spinner comboEnfermedad;
    private String idUsuarioEnfermedad,idMedico,idEnfermedad,nomMedicamento,tipMedicamento;
    private TextView txtUsuario;
    private EditText ediNombreE,ediTipoE;
    private ArrayList<String> listaPersonasUsuario;
    private ArrayList<Usuario> PersonasListUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos_agregar);
        conn = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        helper=new BDMedicamentosControl(this);
        comboEnfermedad=(Spinner) findViewById(R.id.comboselectEnfermedad);
        txtUsuario=(TextView) findViewById(R.id.EdiUsuarioenferm);
        ediNombreE=(EditText) findViewById(R.id.ediNombreMedicamento);
        ediTipoE=(EditText) findViewById(R.id.ediTipoMedicamento);
        SQLiteDatabase db=conn.getReadableDatabase();
        listaPersonasUsuario = new ArrayList<String>();
        PersonasListUsuario=new ArrayList<Usuario>();


        consultarListaPersonas();
        adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPersonasEnfermedad);
        comboEnfermedad.setAdapter(adaptador);
        comboEnfermedad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){


                idUsuarioEnfermedad=PersonasListEnfermedad.get(i-1).getIdUsuario();
                idMedico=PersonasListEnfermedad.get(i-1).getIdMedico();
                idEnfermedad=PersonasListEnfermedad.get(i-1).getIdEnfermedad();

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
                    listaPersonasUsuario.add("Nombre :"+PersonasListUsuario.get(f).getNombre()+
                                           " Especialidad :"+PersonasListUsuario.get(f).getApellido()+"\n");
                }

                txtUsuario.setText(PersonasListUsuario.get(0).getNombre()+" " +PersonasListUsuario.get(0).getApellido());



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
        PersonasListEnfermedad=new ArrayList<Enfermedad>();
        /*this.idEnfermedad = idEnfermedad;
        this.idMedico = idMedico;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipo = tipo;*/
        Cursor cursor=db.rawQuery("select idEnfermedad,idMedico,idUsuario,nombreEnfermedad,fecha,tipo from enfermedad ",
                null);

        while (cursor.moveToNext()){
            Enfermedad enfermedad = new Enfermedad();
            enfermedad.setIdEnfermedad(cursor.getString(0));
            enfermedad.setIdMedico(cursor.getString(1));
            enfermedad.setIdUsuario(cursor.getString(2));
            enfermedad.setNombre(cursor.getString(3));
            enfermedad.setFecha(cursor.getString(4));
            enfermedad.setTipo(cursor.getString(5));
            PersonasListEnfermedad.add(enfermedad);
        }
        obtenerListaUsuario();

    }
    private void obtenerListaUsuario() {
        listaPersonasEnfermedad = new ArrayList<String>();
        listaPersonasEnfermedad.add("Seleccione");
        for (int f=0; f<PersonasListEnfermedad.size();f++)
        {
            listaPersonasEnfermedad.add("Nombre :"+PersonasListEnfermedad.get(f).getNombre()+" Tipo :"+PersonasListEnfermedad.get(f).getTipo()+"\n");
        }
    }

    public void Agregar_Medicamentos(View view) {

        try {
            nomMedicamento=ediNombreE.getText().toString();
            tipMedicamento=ediTipoE.getText().toString();

            String regInsertados;
            Medicamento medicamento = new Medicamento();
            medicamento.setIdMedico(idMedico);
            medicamento.setIdEnfermedad(idEnfermedad);
            medicamento.setIdUsuario(idUsuarioEnfermedad);
            medicamento.setNombreEnf(nomMedicamento);
            medicamento.setTipo(tipMedicamento);

            helper.abrir();
            regInsertados=helper.insertarMedicamento(medicamento);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();

        }

    }
}