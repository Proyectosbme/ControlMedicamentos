package sv.edu.ues.fia.eisi.controlmedicamentos.Medicamento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades.EnfermedadesAgregarActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades.MenuEnfermedadesActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MenuMedicamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_medicamento);
    }

    public void Agregar_medicamento(View view) {
        Intent intent = new Intent(MenuMedicamentoActivity.this, MedicamentosAgregarActivity.class);
        startActivity(intent);
    }

    public void Consultar_medicamento(View view) {
        Intent intent = new Intent(MenuMedicamentoActivity.this, MedicamentosConsultarActivity.class);
        startActivity(intent);
    }

    public void Actualizar_Medicamento(View view) {
        Intent intent = new Intent(MenuMedicamentoActivity.this, MedicamentosEditarActivity.class);
        startActivity(intent);
    }

    public void Elimnar_Medicamento(View view) {
        Intent intent = new Intent(MenuMedicamentoActivity.this, MedicamentosEliminarActivity.class);
        startActivity(intent);
    }
}