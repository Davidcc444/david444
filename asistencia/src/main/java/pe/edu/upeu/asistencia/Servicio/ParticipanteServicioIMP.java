package pe.edu.upeu.asistencia.Servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.asistencia.Modelo.Participantes;
import pe.edu.upeu.asistencia.Repositorio.ParticipanteRepositorio;

import java.util.List;
@Service
public class ParticipanteServicioIMP extends ParticipanteRepositorio implements ParticipanteServicioY {
    @Override
    public void save(Participantes participantes) {
        listaParticipantes.add(participantes);
    }

    @Override
    public List<Participantes> fingAll() {
        return listaParticipantes;
    }

    @Override
    public void update(Participantes participantes, int index) {
        listaParticipantes.set(index, participantes);
    }

    @Override
    public void delete(int idex) {
        listaParticipantes.remove(idex);
    }

    @Override
    public Participantes findById(int index) {
        return listaParticipantes.get(index);
    }
}
