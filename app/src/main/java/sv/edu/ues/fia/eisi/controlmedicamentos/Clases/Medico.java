package sv.edu.ues.fia.eisi.controlmedicamentos.Clases;

public class Medico {
    String idUsuariom,idMedico,nombre,especialidad;

    public Medico() {
    }

    public Medico(String idUsuariom, String idMedico, String nombre, String especialidad) {
        this.idUsuariom = idUsuariom;
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getIdUsuariom() {
        return idUsuariom;
    }

    public void setIdUsuariom(String idUsuariom) {
        this.idUsuariom = idUsuariom;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
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
}


