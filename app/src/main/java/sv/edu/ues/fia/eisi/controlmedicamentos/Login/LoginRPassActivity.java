package sv.edu.ues.fia.eisi.controlmedicamentos.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.MainActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.MenuPrincipalActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class LoginRPassActivity extends AppCompatActivity {


    BDMedicamentosControl  bdMedicamentosControl;
    EditText ediCorreoUPASS;
    String nombre,correo,contra;
    TextView txtNombre,txtCorreo,txtContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_rpass);
        bdMedicamentosControl = new BDMedicamentosControl(this);

        ediCorreoUPASS = (EditText) findViewById(R.id.ediUcoreorecu);
        txtNombre=(TextView) findViewById(R.id.txtNombre2);
        txtCorreo=(TextView) findViewById(R.id.txtCorreo2);
        txtContraseña=(TextView) findViewById(R.id.txtContraseña2);
    }

    public void Recuperar_Contraseña(View view) {
        try {
            bdMedicamentosControl.abrir();
            Usuario usuario = bdMedicamentosControl.consultarUsuario(ediCorreoUPASS.getText().toString());
            bdMedicamentosControl.cerrar();

            if (usuario!=null){
                txtNombre.setText("Nombre :"+usuario.getNombre());
                txtCorreo.setText("Correo :"+usuario.getCorreo());
                txtContraseña.setText("Contraseña :"+usuario.getContraseña());

            }else{
                Toast.makeText(this,"No se encontro el correo"+ediCorreoUPASS , Toast.LENGTH_SHORT).show();

            }

        }catch(Exception e){
            Toast.makeText(this,"Error/campo esta vacio" , Toast.LENGTH_SHORT).show();
        }

    }
}