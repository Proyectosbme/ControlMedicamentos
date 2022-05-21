package sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico.MenuContactoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades.MenuEnfermedadesActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.MainActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medicamento.MenuMedicamentoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medico.MenuMedicoActivity;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuopciones, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.medicamento:

                return true;
            case R.id.menuPrincipal:
                intent= new Intent(MenuUsuarioActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuMedicamento:
                intent = new Intent(MenuUsuarioActivity.this, MenuMedicamentoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuUsuario:
                intent = new Intent(MenuUsuarioActivity.this, MenuUsuarioActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuEnfermedad:
                intent = new Intent(MenuUsuarioActivity.this, MenuEnfermedadesActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuMedico:
                intent = new Intent(MenuUsuarioActivity.this, MenuMedicoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuContacto:
                intent = new Intent(MenuUsuarioActivity.this, MenuContactoActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}