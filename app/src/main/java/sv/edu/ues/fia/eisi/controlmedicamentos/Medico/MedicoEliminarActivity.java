package sv.edu.ues.fia.eisi.controlmedicamentos.Medico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MedicoEliminarActivity extends AppCompatActivity {
    private EditText editIdMedicoEli;
    private BDMedicamentosControl controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_eliminar);
        controlhelper=new BDMedicamentosControl (this);
        editIdMedicoEli=(EditText)findViewById(R.id.ediMEliCorreo);
    }

    public void Eliminar_Medico(View view) {
        String regEliminadas;
        Medico medico =new Medico();
        medico.setIdMedico(editIdMedicoEli.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarMedico(medico);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}