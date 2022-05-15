package sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class UsuarioEliminarActivity extends AppCompatActivity {

    EditText editCorreoEli;
    BDMedicamentosControl controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_eliminar);
        controlhelper=new BDMedicamentosControl (this);
        editCorreoEli=(EditText)findViewById(R.id.ediUEliCorreo);
    }

    public void Eliminar_Usuario(View view) {
        String regEliminadas;
        Usuario usuario =new Usuario();
        usuario.setCorreo(editCorreoEli.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminarUsuario(usuario);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();

    }
}