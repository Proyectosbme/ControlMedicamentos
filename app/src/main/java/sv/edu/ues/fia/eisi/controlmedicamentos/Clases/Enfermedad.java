package sv.edu.ues.fia.eisi.controlmedicamentos.Clases;

public class Enfermedad {
    String idEnfermedad,idMedico,idUsuario,nombre,fecha,tipo;

    public Enfermedad() {
    }

    public Enfermedad(String idEnfermedad, String idMedico, String idUsuario, String nombre, String fecha, String tipo) {
        this.idEnfermedad = idEnfermedad;
        this.idMedico = idMedico;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public String getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(String idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
