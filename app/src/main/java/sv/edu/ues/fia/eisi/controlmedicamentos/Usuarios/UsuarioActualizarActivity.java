package sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class UsuarioActualizarActivity extends AppCompatActivity {

    private BDMedicamentosControl helper;
    private EditText ediNombre,ediApellido,ediEdad,ediGenero,ediCorreo,edinewcorreo;
    private String nombre,apellido,genero,correo,newcorreo;
    private Integer edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_actualizar);
        helper = new BDMedicamentosControl(this);
        ediNombre = (EditText) findViewById(R.id.ediUANombre);
        ediApellido=(EditText) findViewById(R.id.ediUAApellido);
        ediEdad=(EditText) findViewById(R.id.ediUAEdad);
        ediGenero = (EditText) findViewById(R.id.ediUAgenero);
        ediCorreo=(EditText) findViewById(R.id.ediUACorreoAntiguo);
        edinewcorreo=(EditText) findViewById(R.id.ediUACoreonuevo);
    }

    public void Actualizar_Usuario(View view) {
        nombre = ediNombre.getText().toString();
        apellido = ediApellido.getText().toString();
        edad=(Integer) Integer.valueOf(ediEdad.getText().toString());
        genero=ediGenero.getText().toString();
        correo=ediCorreo.getText().toString();
        newcorreo=edinewcorreo.getText().toString();

        String regInsertados;

        Usuario usuario =new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEdad(edad);
        usuario.setGenero(genero);
        usuario.setCorreo(newcorreo);

        Usuario usuario2 =new Usuario();
        usuario2.setCorreo(correo);

        helper.abrir();
        regInsertados=helper.actualizar(usuario,usuario2);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}