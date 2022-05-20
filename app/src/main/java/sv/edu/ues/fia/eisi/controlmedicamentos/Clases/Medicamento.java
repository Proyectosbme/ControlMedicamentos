package sv.edu.ues.fia.eisi.controlmedicamentos.Clases;

public class Medicamento {
    String idMedicamento,idMedico,idEnfermedad,idUsuario,nombreEnf,tipo;

    public Medicamento() {
    }

    public Medicamento(String idMedicamento, String idMedico, String idEnfermedad, String idUsuario, String nombreEnf, String tipo) {
        this.idMedicamento = idMedicamento;
        this.idMedico = idMedico;
        this.idEnfermedad = idEnfermedad;
        this.idUsuario = idUsuario;
        this.nombreEnf = nombreEnf;
        this.tipo = tipo;
    }

    public String getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(String idMedicamento) {
        this.idMedicamento = idMedicamento;
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

    public String getNombreEnf() {
        return nombreEnf;
    }

    public void setNombreEnf(String nombreEnf) {
        this.nombreEnf = nombreEnf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
