package pe.edu.upeu.asistencia.Modelo;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Participantes {
    private StringProperty nombre;
    private BooleanProperty estado;

}
