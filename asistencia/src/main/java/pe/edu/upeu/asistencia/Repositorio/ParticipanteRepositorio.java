package pe.edu.upeu.asistencia.Repositorio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pe.edu.upeu.asistencia.Modelo.Participantes;

import java.util.ArrayList;
import java.util.List;


public class ParticipanteRepositorio {
    public List<Participantes> listaParticipantes =new ArrayList<>();
    public List<Participantes>findAll(){
        listaParticipantes.add(new Participantes(new SimpleStringProperty("Juan"),
                        new SimpleBooleanProperty(true)

                )
        );

        return listaParticipantes;
    }
}
