package sv.edu.ues.fia.eisi.controlmedicamentos.Dosis;

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
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Dosis;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Enfermedad;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medicamento;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class DosisAgregarActivity extends AppCompatActivity {
    private  int dia,mes,ano;
    private EditText edicada,ediTipo;
    private TextView etifechainicio,etifechafin;
    private ArrayList<Medicamento> listaMedicamento;
    private ArrayList<String> listaInformacionMedicamento;
    private BDMedicamentosControl.DatabaseHelper DBH;
    private BDMedicamentosControl helper;
    private Spinner comboMedicamento;
    private String idMedicamento,idUsuario,idMedico,idEnfermedad,tipo,cada,fechaInicio,fechaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosis_agregar);
        etifechainicio=(TextView) findViewById(R.id.txtFechaInicio);
        etifechafin=(TextView) findViewById(R.id.edifechaFin);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        comboMedicamento=(Spinner)findViewById(R.id.comboagregarDosis) ;
        ediTipo=(EditText)findViewById(R.id.ediTipoDosis) ;
        edicada=(EditText)findViewById(R.id.edirepertir) ;

        helper=new BDMedicamentosControl (this);
        consultarMedicamentos();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaInformacionMedicamento);
        comboMedicamento.setAdapter(adaptador);
        comboMedicamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    idUsuario=listaMedicamento.get(i-1).getIdEnfermedad();
                    idEnfermedad=listaMedicamento.get(i-1).getIdEnfermedad();
                    idMedicamento=listaMedicamento.get(i-1).getIdMedicamento();
                    idMedico=listaMedicamento.get(i-1).getIdEnfermedad();
                }
                else{

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void consultarMedicamentos() {
        try {
            SQLiteDatabase db=DBH.getReadableDatabase();
            listaMedicamento= new ArrayList<Medicamento>();
            Cursor cursor = db.rawQuery(
                    /*
        conn.put("idMedicamento",medicamento.getIdMedicamento());
        conn.put("idMedico", medicamento.getIdMedico());
        conn.put("idEnfermedad", medicamento.getIdEnfermedad());
        conn.put("idUsuario", medicamento.getIdUsuario());
        conn.put("nombre", medicamento.getNombreEnf());
        conn.put("tipo",medicamento.getTipo());*/
                    "select idMedicamento,idMedico,idEnfermedad,idUsuario,nombre,tipo from medicamento ", null);

            while (cursor.moveToNext()){
                Medicamento medicamento= new Medicamento();
                medicamento.setIdMedicamento(cursor.getString(0));
                medicamento.setIdMedico(cursor.getString(1));
                medicamento.setIdEnfermedad(cursor.getString(2));
                medicamento.setIdUsuario(cursor.getString(3));
                medicamento.setNombreEnf(cursor.getString(4));
                medicamento.setTipo(cursor.getString(5));
                listaMedicamento.add(medicamento);
            }
            obtenerLista();
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
    private void obtenerLista() {
        listaInformacionMedicamento = new ArrayList<String>();
        listaInformacionMedicamento.add("Seleccione");
        for (int i=0; i<listaMedicamento.size();i++){
            listaInformacionMedicamento.add(
                           "Medicamento :"+listaMedicamento.get(i).getNombreEnf()+"\n"+
                            "tipo :"+listaMedicamento.get(i).getTipo()+"\n"+
                            "-------------------------------------------");
        }
    }


    //_______________________________________________________________________________________-
    public void fecha_inicio(View view) {
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                etifechainicio.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
            }
        }
                ,dia,mes,ano);
        datePickerDialog.show();
    }
    public void fecha_fin(View view) {
        final Calendar c= Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                etifechafin.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
            }
        }
                ,dia,mes,ano);
        datePickerDialog.show();
    }
    public void agregarDOSIS(View view) {
        try {
            tipo=ediTipo.getText().toString();
            cada=edicada.getText().toString();
            fechaInicio=etifechainicio.getText().toString();
            fechaFin=etifechafin.getText().toString();
/*
this.idDosis = idDosis;
        this.idMedico=idMedico;
        this.idEnfermedad = idEnfermedad;
        this.idUsuario = idUsuario;
        this.idMedicamento = idMedicamento;
        this.secuencia = secuencia;
        this.unidad = unidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;*/

            String regInsertados;
            Dosis dosis = new Dosis();

            dosis.setIdMedico(idMedico);
            dosis.setIdEnfermedad(idEnfermedad);
            dosis.setIdUsuario(idUsuario);
            dosis.setIdMedicamento(idMedicamento);
            dosis.setSecuencia(tipo);
            dosis.setUnidad(cada);
            dosis.setFechaInicio(fechaInicio);
            dosis.setFechaFin(fechaFin);

            helper.abrir();
            regInsertados=helper.insertarDosis(dosis);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();

        }
    }
}