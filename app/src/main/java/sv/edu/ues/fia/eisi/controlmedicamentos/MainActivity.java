package sv.edu.ues.fia.eisi.controlmedicamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.Login.LoginRPassActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios.UsuarioConsultarActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios.UsuarioRegistrarActivity;

public class MainActivity extends AppCompatActivity {

    EditText editCorreoInicio,ediContraInicio;
    String correoInicio,contraInicio;
    BDMedicamentosControl controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controlhelper=new BDMedicamentosControl (this);
        editCorreoInicio=(EditText)findViewById(R.id.ediCorreoInicio);
        ediContraInicio=(EditText)findViewById(R.id.edipasswordInicio);
    }

    public void Iniciar_sesion(View view) {
       correoInicio = editCorreoInicio.getText().toString();
        contraInicio = ediContraInicio.getText().toString();

        String regInsertados;

        Usuario usuario =new Usuario();
        usuario.setCorreo(correoInicio);
        usuario.setContraseña(contraInicio);

        controlhelper.abrir();
        regInsertados=controlhelper.InicioUsuario(usuario);
        controlhelper.cerrar();

        if (regInsertados =="autorizado"){
            Intent intent = new Intent(MainActivity.this, MenuPrincipalActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Erro usuario y contraseña no coinciden", Toast.LENGTH_SHORT).show();

        }

    }

    public void Contraseña(View view) {
        Intent intent = new Intent(MainActivity.this, LoginRPassActivity.class);
        startActivity(intent);
    }

    public void Registrarce(View view) {
        Intent intent = new Intent(MainActivity.this, UsuarioRegistrarActivity.class);
        startActivity(intent);
    }
}