package sv.edu.ues.fia.eisi.controlmedicamentos.Medico;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MedicoConsultarActivity extends AppCompatActivity {
    ListView listviewpersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Medico> listaUsuario;
    BDMedicamentosControl.DatabaseHelper DBH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_consultar);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        listviewpersonas = (ListView) findViewById(R.id.lsVConsultarM);
        consultarUsuarios();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaInformacion);
        listviewpersonas.setAdapter(adapter);
    }

    public void consultarUsuarios() {
        SQLiteDatabase db=DBH.getReadableDatabase();
        listaUsuario= new ArrayList<Medico>();
        Cursor cursor = db.rawQuery(
                "select * from medico", null);
        while (cursor.moveToNext()){
            Medico medico = new Medico();
            medico.setIdMedico(cursor.getString(0));
            medico.setIdUsuariom(cursor.getString(1));
            medico.setNombre(cursor.getString(2));
            medico.setEspecialidad(cursor.getString(3));
            listaUsuario.add(medico);
        }
        obtenerLista();
    }
    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i=0; i<listaUsuario.size();i++){
            listaInformacion.add(
                    "Id :"+listaUsuario.get(i).getIdMedico()+"\n"+
                            "idUsuario :"+listaUsuario.get(i).getIdUsuariom()+"\n"+
                            "Nombre :"+listaUsuario.get(i).getNombre()+"\n"+
                            "Especialidad :"+listaUsuario.get(i).getEspecialidad());
        }
    }
}