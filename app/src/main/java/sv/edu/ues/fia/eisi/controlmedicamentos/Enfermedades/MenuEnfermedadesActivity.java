package sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.Medicamento.MedicamentosAgregarActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medicamento.MenuMedicamentoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medico.MedicoInsertarActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medico.MenuMedicoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MenuEnfermedadesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_enfermedades);
    }

    public void Agregar_enfermedad(View view) {
        Intent intent = new Intent(MenuEnfermedadesActivity.this, EnfermedadesAgregarActivity.class);
        startActivity(intent);
    }

    public void Consultar_enfermedad(View view) {
        Intent intent = new Intent(MenuEnfermedadesActivity.this, EnfermedadesConsultarActivity.class);
        startActivity(intent);
    }

    public void MenuMedicamento(View view) {
        Intent intent = new Intent(MenuEnfermedadesActivity.this, MenuMedicamentoActivity.class);
        startActivity(intent);
    }

    public void EliminarEnfermedadMenu(View view) {
        Intent intent = new Intent(MenuEnfermedadesActivity.this, EnfermedadesEliminarActivity.class);
        startActivity(intent);
    }
}