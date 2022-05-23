package sv.edu.ues.fia.eisi.controlmedicamentos.Dosis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades.EnfermedadesEditarActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades.MenuEnfermedadesActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MenuDosisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dosis);
    }

    public void oncAgregarDosis(View view) {
        Intent intent = new Intent(MenuDosisActivity.this, DosisAgregarActivity.class);
        startActivity(intent);
    }

    public void oncConsultarDosis(View view) {
    }

    public void Eliminar_Dosis(View view) {
    }
}