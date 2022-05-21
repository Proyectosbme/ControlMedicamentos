package sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico.MenuContactoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades.MenuEnfermedadesActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medicamento.MenuMedicamentoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medico.MenuMedicoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.MenuPrincipalActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class UsuarioConsultarActivity extends AppCompatActivity {
    private ListView listviewpersonas;
    private ArrayList<String> listaInformacion;
    private ArrayList<Usuario> listaUsuario;
    private BDMedicamentosControl.DatabaseHelper DBH;
    private EditText edinombreU;
    String nombreb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_consultar);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        listviewpersonas = (ListView) findViewById(R.id.lsVConsultarU);
        edinombreU = (EditText) findViewById(R.id.ediUNombre2);

    }
    public void consultarUsuarios() {

        SQLiteDatabase db=DBH.getReadableDatabase();
        listaUsuario= new ArrayList<Usuario>();
        nombreb= edinombreU.getText().toString();
        String[] id1 = {nombreb};
        Cursor cursor = db.rawQuery(
                "select idUsuario,nombre,apellido,edad,genero from usuario "+
                    "where nombre =?",id1, null);

        while (cursor.moveToNext()){

            Usuario usuario1 = new Usuario();
            usuario1.setIdUsuario(cursor.getString(0));
            usuario1.setNombre(cursor.getString(1));
            usuario1.setApellido(cursor.getString(2));
            usuario1.setEdad(cursor.getInt(3));
            usuario1.setGenero(cursor.getString(4));
            listaUsuario.add(usuario1);
        }
        obtenerLista();
            }
    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i=0; i<listaUsuario.size();i++){
            listaInformacion.add(
                    "Id :"+listaUsuario.get(i).getIdUsuario()+"\n"+
                    "Nombre :"+listaUsuario.get(i).getNombre()+"\n"+
                    "Apellido :"+listaUsuario.get(i).getApellido()+"\n"+
                    "Edad :"+listaUsuario.get(i).getEdad()+"\n"+
                    "Genero :"+listaUsuario.get(i).getGenero());
        }
    }
    public void Consultar(View view) {

        try{
            consultarUsuarios();
          ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaInformacion);
          if (listaInformacion.size()!=0){
            listviewpersonas.setAdapter(adapter);

            }else{
                Toast.makeText(this,"No hay usuarios con el nombre "+nombreb , Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio" , Toast.LENGTH_SHORT).show();
        }
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
                intent= new Intent(UsuarioConsultarActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuMedicamento:
                intent = new Intent(UsuarioConsultarActivity.this, MenuMedicamentoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuUsuario:
                intent = new Intent(UsuarioConsultarActivity.this, MenuUsuarioActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuEnfermedad:
                intent = new Intent(UsuarioConsultarActivity.this, MenuEnfermedadesActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuMedico:
                intent = new Intent(UsuarioConsultarActivity.this, MenuMedicoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuContacto:
                intent = new Intent(UsuarioConsultarActivity.this, MenuContactoActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}