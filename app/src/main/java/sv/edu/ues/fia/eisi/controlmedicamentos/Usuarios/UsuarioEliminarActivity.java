package sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Contacto;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class UsuarioEliminarActivity extends AppCompatActivity {

     private EditText editIdUEli;
    private BDMedicamentosControl controlhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_eliminar);
        controlhelper=new BDMedicamentosControl (this);
        editIdUEli=(EditText)findViewById(R.id.ediUEliCorreo);
    }

    public void Eliminar_Usuario(View view) {
        try {
            String regEliminadas;
            Usuario usuario =new Usuario();
            usuario.setIdUsuario(editIdUEli.getText().toString());

            controlhelper.abrir();
            regEliminadas=controlhelper.eliminarUsuario(usuario);
            controlhelper.cerrar();
            Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();

        }
        catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }


    }
}