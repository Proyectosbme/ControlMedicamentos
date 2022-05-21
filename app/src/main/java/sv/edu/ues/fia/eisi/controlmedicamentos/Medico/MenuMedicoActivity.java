package sv.edu.ues.fia.eisi.controlmedicamentos.Medico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico.ContactoAgregarActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico.MenuContactoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades.MenuEnfermedadesActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medicamento.MenuMedicamentoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.MenuPrincipalActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;
import sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios.MenuUsuarioActivity;

public class MenuMedicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_medico);
    }

    public void oncAgregarMedico(View view) {
        Intent intent = new Intent(MenuMedicoActivity.this, MedicoInsertarActivity.class);
        startActivity(intent);
    }

    public void oncConsultarMedico(View view) {
        Intent intent = new Intent(MenuMedicoActivity.this, MedicoConsultarActivity.class);
        startActivity(intent);
    }

    public void Actualizar_Medico(View view) {
        Intent intent = new Intent(MenuMedicoActivity.this, MedicoActualizarActivity.class);
        startActivity(intent);
    }

    public void Eliminar_Medico(View view) {
        Intent intent = new Intent(MenuMedicoActivity.this, MedicoEliminarActivity.class);
        startActivity(intent);
    }

    public void Contacto_medico(View view) {
        Intent intent = new Intent(MenuMedicoActivity.this, MenuContactoActivity.class);
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
                intent= new Intent(MenuMedicoActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuMedicamento:
                intent = new Intent(MenuMedicoActivity.this, MenuMedicamentoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuUsuario:
                intent = new Intent(MenuMedicoActivity.this, MenuUsuarioActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuEnfermedad:
                intent = new Intent(MenuMedicoActivity.this, MenuEnfermedadesActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuMedico:
                intent = new Intent(MenuMedicoActivity.this, MenuMedicoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuContacto:
                intent = new Intent(MenuMedicoActivity.this, MenuContactoActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}