package pe.edu.upeu.conceptos_poo.pomodoro.servicio;

import pe.edu.upeu.conceptos_poo.pomodoro.modelo.Tarea;

import java.util.List;



public interface PomodoroServicio {
    void save(Tarea tarea); //C Agregar un nuevo participante

    List<Tarea> findAll(); // R muestra todos los participantes que estan registrados

    void update(Tarea tarea, int index); //U - actualiza los datos del participante en la posición index.

    void delete(int index); //D  elimina el participante en la posición index.

    Tarea findById(int index);//Buscar con una posición index

}
