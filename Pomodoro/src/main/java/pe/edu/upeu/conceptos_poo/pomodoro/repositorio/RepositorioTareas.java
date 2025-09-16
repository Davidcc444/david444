package pe.edu.upeu.conceptos_poo.pomodoro.repositorio;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pe.edu.upeu.conceptos_poo.pomodoro.enums.EstadoTarea;
import pe.edu.upeu.conceptos_poo.pomodoro.modelo.Tarea;

import java.util.ArrayList;
import java.util.List;

public abstract class RepositorioTareas {
    public List<Tarea> tareas = new ArrayList<>();
    public List<Tarea> findAll(){
        tareas.add(new Tarea(new SimpleStringProperty("Calculo I"),
                new SimpleStringProperty("Funcion Inversa"),
                new SimpleStringProperty("15 min"), EstadoTarea.PENDIENTE));
        return tareas;

    }


}
