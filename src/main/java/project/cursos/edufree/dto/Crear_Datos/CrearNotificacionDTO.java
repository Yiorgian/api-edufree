package project.cursos.edufree.dto.Crear_Datos;

import jakarta.validation.constraints.NotBlank;

public class CrearNotificacionDTO {

    @NotBlank(message = "El mensaje es obligatorio")
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
