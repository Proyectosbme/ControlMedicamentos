package sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.MainActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.MenuPrincipalActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MenuUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
    }

    public void oncAgregarUsuarioa(View view) {
        Intent intent = new Intent(MenuUsuarioActivity.this, UsuarioRegistrarActivity.class);
        startActivity(intent);
    }

    public void oncConsultarUsuario(View view) {
        Intent intent = new Intent(MenuUsuarioActivity.this, UsuarioConsultarActivity.class);
        startActivity(intent);
    }

    public void Eliminar_usuario(View view) {
        Intent intent = new Intent(MenuUsuarioActivity.this, UsuarioEliminarActivity.class);
        startActivity(intent);
    }

    public void Actualizar_Usuario(View view) {
        Intent intent = new Intent(MenuUsuarioActivity.this, UsuarioActualizarActivity.class);
        startActivity(intent);
    }
}