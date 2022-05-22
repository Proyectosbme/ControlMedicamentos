package sv.edu.ues.fia.eisi.controlmedicamentos.Clases;

public class CitaMedica {
    String idCita,idUsuario,idEstablecimiento,fecha,descripcion;

    public CitaMedica() {
    }

    public CitaMedica(String idCita, String idUsuario, String idEstablecimiento, String fecha, String descripcion) {
        this.idCita = idCita;
        this.idUsuario = idUsuario;
        this.idEstablecimiento = idEstablecimiento;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(String idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
