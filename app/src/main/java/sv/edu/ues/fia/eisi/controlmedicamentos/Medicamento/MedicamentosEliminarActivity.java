package sv.edu.ues.fia.eisi.controlmedicamentos.Medicamento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Enfermedad;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medicamento;
import sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico.MenuContactoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades.MenuEnfermedadesActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medico.MedicoEliminarActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medico.MenuMedicoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.MenuPrincipalActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;
import sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios.MenuUsuarioActivity;

public class MedicamentosEliminarActivity extends AppCompatActivity {

    private ArrayList<Medicamento> listaMedicamentos;
    private ArrayList<String> listaInformacionMedicamento;
    private BDMedicamentosControl.DatabaseHelper DBH;
    private BDMedicamentosControl controlhelper;
    private Spinner comboMedicamento;
    private String idMedicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos_eliminar);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        comboMedicamento=(Spinner)findViewById(R.id.comboEliminarMedicamento) ;
        controlhelper=new BDMedicamentosControl (this);
        consultarUsuarios2();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaInformacionMedicamento);
        comboMedicamento.setAdapter(adaptador);
        comboMedicamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    idMedicamento=listaMedicamentos.get(i-1).getIdMedicamento();
                }
                else{

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
            listaMedicamentos= new ArrayList<Medicamento>();
            /*this.idMedicamento = idMedicamento;
        this.idMedico = idMedico;
        this.idEnfermedad = idEnfermedad;
        this.idUsuario = idUsuario;
        this.nombreEnf = nombreEnf;
        this.tipo = tipo;*/
            Cursor cursor = db.rawQuery(
                    "select idMedicamento,nombre,tipo from medicamento ", null);

            while (cursor.moveToNext()){
                Medicamento medicamento= new Medicamento();
                medicamento.setIdMedicamento(cursor.getString(0));
                medicamento.setNombreEnf(cursor.getString(1));
                medicamento.setTipo(cursor.getString(2));
                listaMedicamentos.add(medicamento);
            }
            obtenerLista();
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
        private void obtenerLista() {
            listaInformacionMedicamento = new ArrayList<String>();
            listaInformacionMedicamento.add("Seleccione");
            for (int i=0; i<listaMedicamentos.size();i++){
                listaInformacionMedicamento.add(
                        "Enfermedad :"+listaMedicamentos.get(i).getNombreEnf()+"\n"+
                                "Tipo :"+listaMedicamentos.get(i).getTipo()+"\n"+
                                "-------------------------------------------");
            }
        }
    public void Eliminarmedic(View view) {
        try {
            if (listaMedicamentos.size()!=0){
                String regEliminadas;
                Medicamento medicamento = new Medicamento();
                medicamento.setIdMedicamento(idMedicamento);
                controlhelper.abrir();
                regEliminadas=controlhelper.eliminarMedicamento(medicamento);
                controlhelper.cerrar();
                Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"no hay registro que eliminar" , Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuopciones, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.medicamento:

                return true;
            case R.id.menuPrincipal:
                intent= new Intent(MedicamentosEliminarActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuMedicamento:
                intent = new Intent(MedicamentosEliminarActivity.this, MenuMedicamentoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuUsuario:
                intent = new Intent(MedicamentosEliminarActivity.this, MenuUsuarioActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuEnfermedad:
                intent = new Intent(MedicamentosEliminarActivity.this, MenuEnfermedadesActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuMedico:
                intent = new Intent(MedicamentosEliminarActivity.this, MenuMedicoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuContacto:
                intent = new Intent(MedicamentosEliminarActivity.this, MenuContactoActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }