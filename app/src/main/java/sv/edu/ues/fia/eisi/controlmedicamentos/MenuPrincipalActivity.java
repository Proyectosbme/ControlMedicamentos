package sv.edu.ues.fia.eisi.controlmedicamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios.MenuUsuarioActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void oncUsuarioa(View view) {
        Intent intent = new Intent(MenuPrincipalActivity.this, MenuUsuarioActivity.class);
        startActivity(intent);
    }
}