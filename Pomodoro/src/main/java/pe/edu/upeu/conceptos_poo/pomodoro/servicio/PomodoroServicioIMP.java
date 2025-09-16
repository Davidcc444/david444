package pe.edu.upeu.conceptos_poo.pomodoro.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.conceptos_poo.pomodoro.modelo.Tarea;
import pe.edu.upeu.conceptos_poo.pomodoro.repositorio.RepositorioTareas;

import java.util.List;
@Service
public class PomodoroServicioIMP extends RepositorioTareas implements PomodoroServicio {
    @Override
    public void save(Tarea tarea) {
        tareas.add(tarea);

    }
    @Override
    public List<Tarea>findAll(){
        if (tareas.isEmpty()){
            return super.findAll();
        }
        return tareas;
    }

    @Override
    public void update(Tarea tarea, int index) {
        tareas.set(index,tarea);
    }

    @Override
    public void delete(int index) {
        tareas.remove(index);
    }

    @Override
    public Tarea findById(int index) {
        return tareas.get(index);
    }
}
