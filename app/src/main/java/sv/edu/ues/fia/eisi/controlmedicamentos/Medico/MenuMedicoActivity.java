package sv.edu.ues.fia.eisi.controlmedicamentos.Medico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico.ContactoAgregarActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico.MenuContactoActivity;
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
}