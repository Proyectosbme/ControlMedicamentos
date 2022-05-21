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
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Enfermedad;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medicamento;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class EnfermedadesEditarActivity extends AppCompatActivity {

    private ArrayList<Enfermedad> listaEnfermedad;
    private ArrayList<String> listaInformacionEnfermedad;
    private BDMedicamentosControl.DatabaseHelper DBH;
    private BDMedicamentosControl helper;
    private Spinner comboEnfermedad;
    private String NombreEnf,fecha,idEnfermedad,tipo;
    private  int dia,mes,ano;
    private TextView etifecha;
    private EditText ediNombreEnfermedad,editipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_editar);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        comboEnfermedad=(Spinner)findViewById(R.id.comboActualizarEnfermedad) ;
        ediNombreEnfermedad=(EditText)findViewById(R.id.ediNombreEnfermedadAct);
        editipo=(EditText)findViewById(R.id.ediTipoEnfermedada);
        etifecha=(TextView)findViewById(R.id.ediFechaEnfermedada);

        helper=new BDMedicamentosControl (this);
        consultarUsuarios2();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaInformacionEnfermedad);
        comboEnfermedad.setAdapter(adaptador);
        comboEnfermedad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    idEnfermedad=listaEnfermedad.get(i-1).getIdEnfermedad();
                    ediNombreEnfermedad.setText(listaEnfermedad.get(i-1).getNombre());
                    editipo.setText(listaEnfermedad.get(i-1).getTipo());
                    etifecha.setText(listaEnfermedad.get(i-1).getFecha());

                }
                else{
                    ediNombreEnfermedad.setText("");
                    editipo.setText("");
                    etifecha.setText("");


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void consultarUsuarios2() {
        try {
            SQLiteDatabase db=DBH.getReadableDatabase();
            listaEnfermedad= new ArrayList<Enfermedad>();

            Cursor cursor = db.rawQuery(
                    "select idEnfermedad,idMedico,idUsuario,nombreEnfermedad,fecha,tipo from enfermedad ", null);

            while (cursor.moveToNext()){
                Enfermedad enfermedad = new Enfermedad();
                enfermedad.setIdEnfermedad(cursor.getString(0));
                enfermedad.setIdMedico(cursor.getString(1));
                enfermedad.setIdUsuario(cursor.getString(2));
                enfermedad.setNombre(cursor.getString(3));
                enfermedad.setFecha(cursor.getString(4));
                enfermedad.setTipo(cursor.getString(5));
                listaEnfermedad.add(enfermedad);
            }
            obtenerLista();
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
    private void obtenerLista() {
        listaInformacionEnfermedad = new ArrayList<String>();
        listaInformacionEnfermedad.add("Seleccione");
        for (int i=0; i<listaEnfermedad.size();i++){
            listaInformacionEnfermedad.add(
                           "Enfermedad :"+listaEnfermedad.get(i).getNombre()+"\n"+
                            "Tipo :"+listaEnfermedad.get(i).getTipo()+"\n"+
                            "Fecha:"+listaEnfermedad.get(i).getFecha()+"\n"+
                            "-------------------------------------------");
        }
    }

    public void fecha_infermedada(View view) {
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

    public void Act_enfermedad(View view) {

        try {
            NombreEnf=ediNombreEnfermedad.getText().toString();
            fecha=etifecha.getText().toString();
            tipo=editipo.getText().toString();

            String regInsertados;
            Enfermedad enfermedad =new Enfermedad();

            enfermedad.setIdEnfermedad(idEnfermedad);
            enfermedad.setNombre(NombreEnf);
            enfermedad.setFecha(fecha);
            enfermedad.setTipo(tipo);

            helper.abrir();
            regInsertados=helper.ActualizarEnfermedad(enfermedad);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();

        }

    }

}