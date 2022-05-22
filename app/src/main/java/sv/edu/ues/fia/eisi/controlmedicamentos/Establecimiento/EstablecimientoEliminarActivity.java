package sv.edu.ues.fia.eisi.controlmedicamentos.Establecimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Establecimiento;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medicamento;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class EstablecimientoEliminarActivity extends AppCompatActivity {

    private ArrayList<String> listaInformacionEstablecimiento;
    private ArrayList<Establecimiento> listaEstablecimiento;
    private BDMedicamentosControl.DatabaseHelper DBH;
    private String idEstablecimiento;
    private Spinner comboEstablecimiento;
    private BDMedicamentosControl helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento_eliminar);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        helper=new BDMedicamentosControl(this);
        //____________________________________________________________
        comboEstablecimiento=(Spinner) findViewById(R.id.comboestabelimi);
        consultarEstablecimiento();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaInformacionEstablecimiento);
        comboEstablecimiento.setAdapter(adaptador);
        comboEstablecimiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    idEstablecimiento=listaEstablecimiento.get(i-1).getIdEstablecimiento();

                }
                else{
                    idEstablecimiento="";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void consultarEstablecimiento() {
        try {
            SQLiteDatabase db=DBH.getReadableDatabase();
            listaEstablecimiento= new ArrayList<Establecimiento>();
            Cursor cursor = db.rawQuery(
                    "select idEstablecimiento,idUsuario,nombre,direccion,telefono from establecimiento ", null);

            while (cursor.moveToNext()){
                Establecimiento establecimiento = new Establecimiento();
                establecimiento.setIdEstablecimiento(getString(0));
                establecimiento.setIdUsuario(cursor.getString(1));
                establecimiento.setNombre(cursor.getString(2));
                establecimiento.setDireccion(cursor.getString(3));
                establecimiento.setTelefono(cursor.getString(4));
                listaEstablecimiento.add(establecimiento);
            }
            obtenerLista();
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
    private void obtenerLista() {

            listaInformacionEstablecimiento = new ArrayList<String>();
            for (int i=0; i<listaEstablecimiento.size();i++){
                listaInformacionEstablecimiento.add(
                        "nombre :"+listaEstablecimiento.get(i).getNombre()+"\n"+
                                "Direccion :"+listaEstablecimiento.get(i).getDireccion()+"\n"+
                                "Telefono :"+listaEstablecimiento.get(i).getTelefono()+"\n"+
                                "---------------------------------------------------------");
            }

    }
    public void Eliminarestablecimiento(View view) {
        try {
            if (listaEstablecimiento.size()!=0){
                String regEliminadas;
                Establecimiento establecimiento = new Establecimiento();
                establecimiento.setIdEstablecimiento(idEstablecimiento);
                helper.abrir();
                regEliminadas=helper.eliminarEstablecimiento(establecimiento);
                helper.cerrar();
                Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"no hay registro que eliminar" , Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
    }
}