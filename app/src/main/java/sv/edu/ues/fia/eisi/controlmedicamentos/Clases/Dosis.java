package sv.edu.ues.fia.eisi.controlmedicamentos.Clases;

public class Dosis {
    String idDosis, idMedico,idEnfermedad,idUsuario,idMedicamento,secuencia,unidad,fechaInicio,fechaFin;

    public Dosis() {
    }

    public Dosis(String idDosis,String idMedico, String idEnfermedad, String idUsuario, String idMedicamento, String secuencia, String unidad, String fechaInicio, String fechaFin) {
        this.idDosis = idDosis;
        this.idMedico=idMedico;
        this.idEnfermedad = idEnfermedad;
        this.idUsuario = idUsuario;
        this.idMedicamento = idMedicamento;
        this.secuencia = secuencia;
        this.unidad = unidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;

    }

    public String getIdDosis() {
        return idDosis;
    }

    public void setIdDosis(String idDosis) {
        this.idDosis = idDosis;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(String idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(String idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
