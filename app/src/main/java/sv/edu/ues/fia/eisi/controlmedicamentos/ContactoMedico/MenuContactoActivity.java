package sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.Medico.MedicoEliminarActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medico.MenuMedicoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MenuContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_contacto);
    }
        public void contacot_Actualizar_Medico(View view) {
            Intent intent = new Intent(MenuContactoActivity.this, ContactoActualizarActivity.class);
            startActivity(intent);
    }

    public void contactoAgregarMedico(View view) {
        Intent intent = new Intent(MenuContactoActivity.this, ContactoAgregarActivity.class);
        startActivity(intent);
    }

    public void contactoConsultarMedico(View view) {
        Intent intent = new Intent(MenuContactoActivity.this, ContactoConsultarActivity.class);
        startActivity(intent);
    }
}