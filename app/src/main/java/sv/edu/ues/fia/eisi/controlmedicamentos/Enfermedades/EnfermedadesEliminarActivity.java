package sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Enfermedad;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class EnfermedadesEliminarActivity extends AppCompatActivity {
    private ArrayList<Enfermedad> listaEnfermedad;
    private ArrayList<String> listaInformacionEnfermedad;
    private BDMedicamentosControl.DatabaseHelper DBH;
    private BDMedicamentosControl controlhelper;
    private Spinner comboEnfermedad;
    private String idEnfermedad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades_eliminar);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        comboEnfermedad=(Spinner)findViewById(R.id.comboEliminarEnfermedad) ;
        controlhelper=new BDMedicamentosControl (this);
        consultarUsuarios2();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaInformacionEnfermedad);
        comboEnfermedad.setAdapter(adaptador);
        comboEnfermedad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    idEnfermedad=listaEnfermedad.get(i-1).getIdEnfermedad();
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
            listaEnfermedad= new ArrayList<Enfermedad>();
            Cursor cursor = db.rawQuery(
                    "select idEnfermedad,idUsuario,nombreEnfermedad,fecha,tipo from enfermedad ", null);

            while (cursor.moveToNext()){
                Enfermedad enfermedad = new Enfermedad();
                enfermedad.setIdEnfermedad(cursor.getString(0));
                enfermedad.setIdUsuario(cursor.getString(1));
                enfermedad.setNombre(cursor.getString(2));
                enfermedad.setFecha(cursor.getString(3));
                enfermedad.setTipo(cursor.getString(4));
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
                    "Fecha :"+listaEnfermedad.get(i).getFecha()+"\n"+
                    "tipo :"+listaEnfermedad.get(i).getTipo()+"\n"+
                    "-------------------------------------------");
        }
    }

    public void EliminarEnfermedad(View view) {
        try {
            if (listaEnfermedad.size()!=0){
            String regEliminadas;
            Enfermedad enfermedad=new Enfermedad();
            enfermedad.setIdEnfermedad(idEnfermedad);
            controlhelper.abrir();
            regEliminadas=controlhelper.eliminarEnfermedad(enfermedad);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"no hay registro que eliminar" , Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }

    }
}