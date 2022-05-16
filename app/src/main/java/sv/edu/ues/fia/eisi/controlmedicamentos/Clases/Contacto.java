package sv.edu.ues.fia.eisi.controlmedicamentos.Clases;

public class Contacto {
    private String idContacto ,idMedico,direccion,Telefono;

    public Contacto() {
    }

    public Contacto(String idContacto, String idMedico, String direccion, String telefono) {
        this.idContacto = idContacto;
        this.idMedico = idMedico;
        this.direccion = direccion;
        Telefono = telefono;
    }

    public String getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(String idContacto) {
        this.idContacto = idContacto;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
