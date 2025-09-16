package pe.edu.upeu.conceptos_poo.pomodoro.modelo;

import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.conceptos_poo.pomodoro.enums.EstadoTarea;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
    private StringProperty nombre;
    private StringProperty descripcion;
    private StringProperty duracion; // en minutos
    private EstadoTarea estadoTarea;
}
