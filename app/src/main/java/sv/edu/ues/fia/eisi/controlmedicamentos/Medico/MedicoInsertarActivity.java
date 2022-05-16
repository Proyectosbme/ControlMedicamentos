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

public class MedicoInsertarActivity extends AppCompatActivity {
    private EditText ediNombreM,EdiEspecialidadM,ediMusurio;
    private String NombreM,EspecialidadM;
    private Integer idUsuario;
    private BDMedicamentosControl helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_insertar);
        ediNombreM=(EditText)findViewById(R.id.ediMNombre);
        EdiEspecialidadM=(EditText)findViewById(R.id.ediMEspecialidad);
        ediMusurio=(EditText) findViewById(R.id.ediMusurio);
        }

    public void Registrar_Medico(View view) {
        NombreM= ediNombreM.getText().toString();
        EspecialidadM=EdiEspecialidadM.getText().toString();
        idUsuario=(Integer)Integer.valueOf(ediMusurio.getText().toString());
        String regInsertados;
        Medico medicos  =new Medico();

        medicos.setIdMedico(idUsuario);
        medicos.setNombre(NombreM);
        medicos.setEspecialidad(EspecialidadM);

        try{
            helper.abrir();
        regInsertados=helper.insertarMedico(medicos);
            helper.cerrar();

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }

    catch(Exception e){
        Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
    }


    }
}