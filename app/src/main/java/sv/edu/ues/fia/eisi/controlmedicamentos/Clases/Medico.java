package sv.edu.ues.fia.eisi.controlmedicamentos.Clases;

public class Medico {
    String nombre,especialidad;
    int idMedico,idUsuario;

    public Medico() {
    }
    public Medico(String nombre, String especialidad, Integer idMedico, Integer idUsuario) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.idMedico = idMedico;
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}


