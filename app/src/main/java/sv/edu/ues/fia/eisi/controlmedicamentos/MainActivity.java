package sv.edu.ues.fia.eisi.controlmedicamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.Login.LoginRPassActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios.UsuarioConsultarActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios.UsuarioRegistrarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Iniciar_sesion(View view) {

        Intent intent = new Intent(MainActivity.this, MenuPrincipalActivity.class);
        startActivity(intent);
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