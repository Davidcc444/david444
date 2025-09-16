package pe.edu.upeu.conceptos_poo.pomodoro.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public enum EstadoTarea {
    PENDIENTE("Pendiente"),
    EN_PROGRESO("En Progreso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada");
    private String descripcion;
    @Override
    public String toString() { return descripcion;}

}
