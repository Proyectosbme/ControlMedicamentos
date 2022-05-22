package sv.edu.ues.fia.eisi.controlmedicamentos.Establecimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.Medico.MenuMedicoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.MenuPrincipalActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MenuEstablecimientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_establecimiento);
    }

    public void Consultar_medicamento(View view) {
        Intent intent = new Intent(MenuEstablecimientoActivity.this, EstablecimientoConsultarActivity.class);
        startActivity(intent);
    }

    public void Agregar_Establecimeinto(View view) {
        Intent intent = new Intent(MenuEstablecimientoActivity.this, EstablecimientoAgregarActivity.class);
        startActivity(intent);
    }

    public void Eliminar_establecimiento(View view) {
        Intent intent = new Intent(MenuEstablecimientoActivity.this, EstablecimientoEliminarActivity.class);
        startActivity(intent);
    }
}