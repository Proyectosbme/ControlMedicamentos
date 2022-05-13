package sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;

public class BDMedicamentosControl {

        private static final String[]camposUsuarior=new String []{"nombre","correo","contraseña"};
        private static final String[]camposUsuario = new String []{"idUsuario","nombre","apellido","edad","genero","contraseña","correo"};
        private static final String[]camposEstablecimiento = new String [] {"idEstablecimiento","nombre","direccion","telefono","idUsuario"};
        private static final String[]camposMedico = new String [] {"idMedico","idUsuario","idContacto","Nombre"};
        private static final String[]camposCitaMedica = new String [] {"idCitaMedica","idMedico","titulo","telefono","idUsuario"};
        private static final String[]camposContacto = new String [] {"idContacto","titulo","direccion","telefono","idUsuario"};


    private final Context context;
        private DatabaseHelper DBHelper;
        private SQLiteDatabase db;
        public BDMedicamentosControl(Context ctx) {this.context = ctx; DBHelper = new DatabaseHelper(context); }

        public static class DatabaseHelper extends SQLiteOpenHelper {
            private static final String BASE_DATOS = "parcial02.s3db";
            private static final int VERSION = 1;
            public DatabaseHelper(Context context) {
                super(context, BASE_DATOS, null, VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                try{
                    db.execSQL("CREATE TABLE usuario" +"(idUsuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +"nombre VARCHAR(25)," +"apellido VARCHAR(25),"+"edad Integer,"+"genero VARCHAR(25),"+"contraseña VARCHAR(15),"+"correo VARCHAR(100));");

                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
            }
        }
        public void abrir() throws SQLException{
            db = DBHelper.getWritableDatabase();
            return;
        }
        public void cerrar(){
            DBHelper.close();
        }

        public String insertar(Usuario usuario){
            String regInsertados="Registro Insertado Nº= ";
            long contador=0;
            //,,,,,,
            ContentValues cond = new ContentValues();
            cond.put("idUsuario",usuario.getIdUsuario());
            cond.put("nombre",usuario.getNombre());
            cond.put("apellido",usuario.getApellido());
            cond.put("edad",usuario.getEdad());
            cond.put("genero",usuario.getGenero());
            cond.put("contraseña",usuario.getContraseña());
            cond.put("correo",usuario.getCorreo());

            contador=db.insert("usuario", null, cond);
            if(contador==-1 || contador==0)
            {
                regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            }
            else {
                regInsertados=regInsertados+contador;
            }
            return regInsertados;
        }
    public Usuario consultarUsuario(String correo){

        String[] id = {correo};
        Cursor cursor = db.query("usuario", camposUsuarior, "correo = ?"
                , id, null, null, null);
        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setNombre(cursor.getString(0));
            usuario.setCorreo(cursor.getString(1));
            usuario.setContraseña(cursor.getString(2));
            return usuario;
        }else{
            return null;
        }
    }


    }

