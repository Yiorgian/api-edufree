package project.cursos.edufree.dto.Crear_Datos;

public class CrearNotificacionDTO {
    private String mensaje;
    private Integer usuarioId;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
