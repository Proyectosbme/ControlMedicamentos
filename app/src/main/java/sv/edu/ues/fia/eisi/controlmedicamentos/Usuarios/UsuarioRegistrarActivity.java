package sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class UsuarioRegistrarActivity extends AppCompatActivity {

    private BDMedicamentosControl helper;
    private EditText ediNombre,ediApellido,ediEdad,ediGenero,ediContraseña,ediContraseña2,ediCorreo;
    private String nombre,apellido,genero,contraseña,contraseña2,correo;
    private Integer edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_registrar);
        helper = new BDMedicamentosControl(this);
        ediNombre = (EditText) findViewById(R.id.ediUNombre);
        ediApellido=(EditText) findViewById(R.id.ediUApellido);
        ediEdad=(EditText) findViewById(R.id.ediUEdad);
        ediGenero = (EditText) findViewById(R.id.ediUgenero);
        ediContraseña=(EditText) findViewById(R.id.ediUContraseña);
        ediContraseña2=(EditText) findViewById(R.id.ediUContraseña2);
        ediCorreo=(EditText) findViewById(R.id.ediUCorreo);
    }

    public void Registrar_Usuario(View view) {
        try {
        nombre = ediNombre.getText().toString();
        apellido = ediApellido.getText().toString();
        edad=(Integer) Integer.valueOf(ediEdad.getText().toString());
        genero=ediGenero.getText().toString();
        contraseña=ediContraseña.getText().toString();
        contraseña2=ediContraseña2.getText().toString();
        correo=ediCorreo.getText().toString();
        if (contraseña.equals(contraseña2)){
         String regInsertados;
        Usuario usuario =new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEdad(edad);
        usuario.setGenero(genero);
        usuario.setContraseña(contraseña);
        usuario.setCorreo(correo);
        helper.abrir();
        regInsertados=helper.insertar(usuario);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }
        else    {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();

        }
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }

    }
}