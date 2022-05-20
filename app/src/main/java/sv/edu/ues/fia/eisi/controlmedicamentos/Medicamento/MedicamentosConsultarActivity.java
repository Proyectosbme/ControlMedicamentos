package sv.edu.ues.fia.eisi.controlmedicamentos.Medicamento;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto.BDMedicamentosControl;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Enfermedad;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medicamento;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;
import sv.edu.ues.fia.eisi.controlmedicamentos.R;

public class MedicamentosConsultarActivity extends AppCompatActivity {

    private Spinner comoenfermedad;
    private ListView listviewMedicamentos;
    private ArrayList<String> listaInformacionEnfermedad;
    private ArrayList<Enfermedad> listaEnfermedad;
    private ArrayList<String> listaInformacionUsuario;
    private ArrayList<Usuario> listaUsuario;
    private ArrayList<Medicamento> listaMedicamento;
    private ArrayList<String> listaInformacionMedicamento;
    private String idUsuario,idMedico,idEnfermedad;
    private Integer conEnfer=1;
    private BDMedicamentosControl.DatabaseHelper DBH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos_consultar);
        DBH = new BDMedicamentosControl.DatabaseHelper(getApplicationContext());
        comoenfermedad=(Spinner) findViewById(R.id.comboconsultaMedicamento);
        listviewMedicamentos = (ListView) findViewById(R.id.lsVConsultarMedicamento);

        consultarListaPersonas();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaInformacionEnfermedad);
        comoenfermedad.setAdapter(adaptador);
        comoenfermedad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0){

                    idUsuario=listaEnfermedad.get(i-1).getIdUsuario();
                    idMedico=listaEnfermedad.get(i-1).getIdMedico();
                    idEnfermedad=listaEnfermedad.get(i-1).getIdEnfermedad();
                    conEnfer=i-1;
                }
                else{
                    idMedico="";
                    idUsuario="";
                    idEnfermedad="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=DBH.getReadableDatabase();
        listaEnfermedad=new ArrayList<Enfermedad>();
        Cursor cursor=db.rawQuery("select * from enfermedad", null);

        while (cursor.moveToNext()){
           Enfermedad enfermedad = new Enfermedad();
           enfermedad.setIdEnfermedad(cursor.getString(0));
           enfermedad.setIdMedico(cursor.getString(1));
            enfermedad.setIdUsuario(cursor.getString(2));
            enfermedad.setNombre(cursor.getString(3));
            enfermedad.setFecha(cursor.getString(4));
            enfermedad.setTipo(cursor.getString(5));
            listaEnfermedad.add(enfermedad);
        }
        obtenerLista2();

    }

    private void obtenerLista2() {
        listaInformacionEnfermedad = new ArrayList<String>();
        listaInformacionEnfermedad.add("Seleccione");
        for (int i=0; i<listaEnfermedad.size();i++)
        {
            listaInformacionEnfermedad.add("Nombre :"+listaEnfermedad.get(i).getNombre()+" "+listaEnfermedad.get(i).getTipo()+" "+listaEnfermedad.get(i).getFecha()+"\n");
        }
    }
    public void consultarUsuario() {
        try {
            if (idUsuario!=""){
            SQLiteDatabase db=DBH.getReadableDatabase();
            listaUsuario= new ArrayList<Usuario>();
            String[] id1 = {idUsuario};
            Cursor cursor = db.rawQuery("select idUsuario,nombre from usuario "+
                            "where idUsuario =?",id1, null);

            while (cursor.moveToNext()){
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(cursor.getString(0));
                usuario.setNombre(cursor.getString(1));
                listaUsuario.add(usuario);
            }
            }else{
                Toast.makeText(this,"Seleccione una enfermedad USUARIO" , Toast.LENGTH_SHORT).show();

            }

        }catch(Exception e){
            Toast.makeText(this,"Error/algun campo esta vacio en usuario" , Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarMedicamentos() {
                   if (idEnfermedad!=""){


                SQLiteDatabase db=DBH.getReadableDatabase();
                listaMedicamento= new ArrayList<Medicamento>();
                String[] id1 = {idEnfermedad};
                          Cursor cursor = db.rawQuery(
                        "select idMedicamento,idEnfermedad,nombre,tipo from medicamento "+
                                "where idEnfermedad =?",id1, null);

                while (cursor.moveToNext()){
                    Medicamento medicamento=new Medicamento();
                    medicamento.setIdMedicamento(cursor.getString(0));
                    medicamento.setIdEnfermedad(cursor.getString(1));
                    medicamento.setNombreEnf(cursor.getString(2));
                    medicamento.setTipo(cursor.getString(3));
                    listaMedicamento.add(medicamento);
                }
            }else{

            }

                   obtenerListaMedicamento();


    }
    private void obtenerListaMedicamento() {
        consultarUsuario();
        listaInformacionMedicamento = new ArrayList<String>();
        for (int i=0; i<listaMedicamento.size();i++){
            listaInformacionMedicamento.add(
                    "Nombre Usuario :"+listaUsuario.get(0).getNombre()+"\n"+
                    "Nombre Enfermedad :"+listaEnfermedad.get(0).getNombre()+"\n"+
                    "Nombre medicamento:"+listaMedicamento.get(i).getNombreEnf()+"\n"+
                    "tipo :"+listaMedicamento.get(i).getTipo());
        }
    }

    public void consultar_medicamentos(View view) {

        try{
            consultarMedicamentos();

            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaInformacionMedicamento);
            if (listaInformacionMedicamento.size()!=0){
                listviewMedicamentos.setAdapter(adapter);
                Toast.makeText(this,idUsuario , Toast.LENGTH_SHORT).show();
                listaUsuario.clear();
                listaMedicamento.clear();


            }else{
                Toast.makeText(this,"No hay medicamento para la enfermedad " , Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            Toast.makeText(this,"selecciones una enfermedad" , Toast.LENGTH_SHORT).show();
        }
    }
}

