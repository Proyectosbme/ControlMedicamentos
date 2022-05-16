package sv.edu.ues.fia.eisi.controlmedicamentos.BDProyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Medico;
import sv.edu.ues.fia.eisi.controlmedicamentos.Clases.Usuario;

public class BDMedicamentosControl {

        private static final String[]camposUsuarioconsulta=new String []{"nombre","correo","contraseña"};

        private static final String[]camposUsuario = new String []{"idUsuario","nombre","apellido","edad","genero","contraseña","correo"};
        private static final String[]camposMedico = new String [] {"idMedico","idUsuario","nombre","especialidad"};

        private static final String[]camposEstablecimiento = new String [] {"idEstablecimiento","nombre","direccion","telefono","idUsuario"};
        private static final String[]camposCitaMedica = new String [] {"idCitaMedica","idMedico","titulo","telefono","idUsuario"};
        private static final String[]camposContacto = new String [] {"idContacto","titulo","direccion","telefono","idMedico"};


    private final Context context;
        private DatabaseHelper DBHelper;
        private SQLiteDatabase db;
        public BDMedicamentosControl(Context ctx) {this.context = ctx; DBHelper = new DatabaseHelper(context); }

        public static class DatabaseHelper extends SQLiteOpenHelper {
            private static final String BASE_DATOS = "ControlMedicamentos2.s3db";
            private static final int VERSION = 1;
            public DatabaseHelper(Context context) {
                super(context, BASE_DATOS, null, VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                try{
                    db.execSQL("CREATE TABLE usuario(idUsuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,nombre VARCHAR(25),apellido VARCHAR(25),edad Integer,genero VARCHAR(25),contraseña VARCHAR(15),correo VARCHAR(100));");
                    db.execSQL("CREATE TABLE medico(idMedico INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,idUsuario INTEGER,nombre VARCHAR(25),especialidad VARCHAR(25));");
                    db.execSQL("CREATE TABLE medicoContacto(idContacto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,idMedico INTEGER,direccion VARCHAR(75),Telefono VARCHAR(25));");

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

    public String InicioUsuario(Usuario usuario){
        if(verificarIntegridad(usuario, 3)){
           return "autorizado";
        }else{
            return "denegado";
        }
    }
//--------------Inicio de usuario-------------------------------------------------
//--------------Inicio de usuario-------------------------------------------------
//--------------Inicio de usuario-------------------------------------------------

    public String insertar(Usuario usuario){
            String regInsertados="Registro Insertado Nº= ";
            long contador=0;
            //,,,,,,
            if(verificarIntegridad(usuario,1)){
                regInsertados= "Error al Insertar el registro, Correo Duplicado. Verificar inserción";
            }
            else{
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

            }
            return regInsertados;
        }

    public String actualizar(Usuario usuario,Usuario usuario2){
        if(verificarIntegridad(usuario2, 2)){
            String[] id = {usuario2.getCorreo()};
            ContentValues cond = new ContentValues();
            cond.put("nombre",usuario.getNombre());
            cond.put("apellido",usuario.getApellido());
            cond.put("edad",usuario.getEdad());
            cond.put("genero",usuario.getGenero());
            cond.put("correo",usuario.getCorreo());
            db.update("usuario", cond, "correo = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }
    public Usuario consultarUsuario(String correo){

        String[] id = {correo};
        Cursor cursor = db.query("usuario", camposUsuarioconsulta, "correo = ?"
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

    public String eliminarUsuario(Usuario usuario){
        String regAfectados="filas afectadas= ";
        int contador=0;


        if (verificarIntegridad(usuario,4)){
            contador+=db.delete("medico", "idUsuario='"+usuario.getIdUsuario()+"'", null);

        }
        contador+=db.delete("usuario", "idUsuario='"+usuario.getIdUsuario()+"'", null);

        if (contador>=1){
            regAfectados+=contador+"/nSe elimino el registro "+usuario.getCorreo();
        }
        else{
            regAfectados="No se elimino ningun registro";
        }

        return regAfectados;
    }

    //--------------fin de usuario-------------------------------------------------
    //--------------fin de usuario-------------------------------------------------
    //--------------fin de usuario-------------------------------------------------


    //--------------Inicio de Medico-------------------------------------------------
    //--------------Inicio de Medico-------------------------------------------------
    //--------------Inicio de Medico-------------------------------------------------
    public String insertarMedico(Medico medico){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        ContentValues conn = new ContentValues();

        conn.put("idMedico", medico.getIdMedico());
        conn.put("idUsuario", medico.getIdUsuariom());
        conn.put("nombre", medico.getNombre());
        conn.put("especialidad", medico.getEspecialidad());

        contador=db.insert("medico", null, conn);

        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }


    public String eliminarMedico(Medico medico){
        String regAfectados="filas afectadas= ";
        int contador=0;
        contador+=db.delete("medico", "idMedico='"+medico.getIdMedico()+"'", null);
        if (contador>=1){
            regAfectados+=contador+"/nSe elimino el registro "+medico.getIdMedico();
        }
        else{
            regAfectados="No se elimino ningun registro";
        }

        return regAfectados;
    }
    public String actualizarMedico(Medico medico,Medico medico2){
        if(verificarIntegridad(medico2, 5)){
            String[] id = {medico2.getIdUsuariom()};
            ContentValues cond = new ContentValues();
            cond.put("idUsuario",medico.getIdUsuariom());
            cond.put("nombre",medico.getNombre());
            cond.put("especialidad",medico.getEspecialidad());
            db.update("medico", cond, "idUsuario = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }
    }
    //--------------Fin de Medico-------------------------------------------------
    //--------------Fin de Medico-------------------------------------------------
    //--------------Fin de Medico-------------------------------------------------




    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){

            case 1://verificar que al insertar usuario no exista el correo
            {
                Usuario usuario = (Usuario) dato;
                String[] id1 = {usuario.getCorreo()};
                //abrir();
                Cursor cursor1 = db.query("usuario", null, "correo = ?",
                        id1, null,null, null);
                 if(cursor1.moveToFirst()){
                //Se encontraron datos
                    return true;
                }
                return false;

        }


        case 2:
        {
//verificar que al modificar nota exista carnet del alumno, el    codigo de materia y el ciclo
            Usuario usuario1 = (Usuario) dato;
            String[] ids = {usuario1.getCorreo()};
            abrir();
            Cursor c = db.query("usuario", null, "correo = ?", ids,
                    null, null, null);
            if(c.moveToFirst()){
//Se encontraron datos
                return true;
            }
            return false;
        }
            case 3:
            {
//verificar que al modificar nota exista carnet del alumno, el    codigo de materia y el ciclo
                Usuario usuario1 = (Usuario) dato;
                String[] ids = {usuario1.getCorreo(),usuario1.getContraseña()};
                abrir();
                Cursor c = db.query("usuario", null, "correo = ? AND contraseña = ?", ids,
                        null, null, null);
                if(c.moveToFirst()){
            //Se encontraron datos
                    return true;
                }
                return false;
            }
            case 4:
            {

                Usuario usuario = (Usuario) dato;
                Cursor c=db.query(true, "medico", new String[] {
                                "idUsuario" }, "idUsuario='"+usuario.getIdUsuario()+"'",null,
                        null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }

            case 5:
            {
//verificar que al modificar nota exista carnet del alumno, el    codigo de materia y el ciclo
                Medico medico1 = (Medico) dato;
                String[] ids = {medico1.getIdUsuariom()};
                abrir();
                Cursor c = db.query("medico", null, "idUsuario = ?", ids,
                        null, null, null);
                if(c.moveToFirst()){
//Se encontraron datos
                    return true;
                }
                return false;
            }

        default:
        return false;
    }

    }
}

