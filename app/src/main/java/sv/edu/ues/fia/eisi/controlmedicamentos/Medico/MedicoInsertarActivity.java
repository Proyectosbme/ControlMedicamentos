package sv.edu.ues.fia.eisi.controlmedicamentos.Medico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.ContactoMedico.MenuContactoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Enfermedades.MenuEnfermedadesActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.Medicamento.MenuMedicamentoActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.MenuPrincipalActivity;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;
import sv.edu.ues.fia.eisi.controlmedicamentos.Usuarios.MenuUsuarioActivity;

public class MedicoInsertarActivity extends AppCompatActivity {
    private EditText ediNombreM,EdiEspecialidadM;
    private String NombreM,EspecialidadM,usuarioid;
    private Spinner comboUsuario;
    private ArrayList<String> listaPersonas;
    private ArrayList<Usuario> PersonasList;
    private BDMedicamentosControl.DatabaseHelper conn;
    private BDMedicamentosControl helper;
    private ArrayAdapter<CharSequence> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_insertar);
        helper=new BDMedicamentosControl(this);
        comboUsuario=(Spinner) findViewById(R.id.comboUsuario);
        ediNombreM=(EditText)findViewById(R.id.ediMNombre);
        EdiEspecialidadM=(EditText)findViewById(R.id.ediMEspecialidad);

        conn = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        consultarListaPersonas();
        adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaPersonas);
        comboUsuario.setAdapter(adaptador);
        comboUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){
                    usuarioid=PersonasList.get(i-1).getIdUsuario();
                }
                else{
                    ediNombreM.setText("");
                    EdiEspecialidadM.setText("");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Usuario persona=null;
        PersonasList=new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("select idusuario,nombre,apellido from usuario ", null);

        while (cursor.moveToNext()){
            Usuario usuario1 = new Usuario();
            usuario1.setIdUsuario(cursor.getString(0));
            usuario1.setNombre(cursor.getString(1));
            usuario1.setApellido(cursor.getString(2));
            PersonasList.add(usuario1);
        }
        obtenerLista();

    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i=0; i<PersonasList.size();i++){
            listaPersonas.add("Nombre :"+PersonasList.get(i).getNombre()+" "+PersonasList.get(i).getApellido()+"\n");
        }
    }
    public void Registrar_Medico(View view) {
        try {

            if (ediNombreM.getText().toString().equals("") || EdiEspecialidadM.getText().toString().equals("") ){
                Toast.makeText(this, "Ingrese nombre y especialidad", Toast.LENGTH_SHORT).show();

            }else{


        NombreM= ediNombreM.getText().toString();
        EspecialidadM=EdiEspecialidadM.getText().toString();

        String regInsertados;
        Medico medicos  =new Medico();

        medicos.setIdUsuariom(usuarioid);
        medicos.setNombre(NombreM);
        medicos.setEspecialidad(EspecialidadM);

            helper.abrir();
            regInsertados=helper.insertarMedico(medicos);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

            }
        }

        catch(Exception e){
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
                intent= new Intent(MedicoInsertarActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuMedicamento:
                intent = new Intent(MedicoInsertarActivity.this, MenuMedicamentoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuUsuario:
                intent = new Intent(MedicoInsertarActivity.this, MenuUsuarioActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuEnfermedad:
                intent = new Intent(MedicoInsertarActivity.this, MenuEnfermedadesActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuMedico:
                intent = new Intent(MedicoInsertarActivity.this, MenuMedicoActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuContacto:
                intent = new Intent(MedicoInsertarActivity.this, MenuContactoActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}