package sv.edu.ues.fia.eisi.controlmedicamentos.Clases;

public class Establecimiento {
    private String idEstablecimiento,idUsuario,nombre,direccion,telefono;

    public Establecimiento() {
    }

    public Establecimiento(String idEstablecimiento, String idUsuario, String nombre, String direccion, String telefono) {
        this.idEstablecimiento = idEstablecimiento;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(String idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
