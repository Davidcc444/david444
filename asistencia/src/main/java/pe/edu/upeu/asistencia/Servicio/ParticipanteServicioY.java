package pe.edu.upeu.asistencia.Servicio;

import pe.edu.upeu.asistencia.Modelo.Participantes;
import java.util.List;

public interface ParticipanteServicioY {
    void save(Participantes participantes); //C
    List<Participantes> fingAll(); //R
    void update(Participantes participantes, int index); //U
    void delete(int idex); //D
    Participantes findById(int index);//Buscar
}
