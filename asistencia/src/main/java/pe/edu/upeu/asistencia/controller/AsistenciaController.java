package pe.edu.upeu.asistencia.controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.asistencia.Modelo.Participantes;
import pe.edu.upeu.asistencia.Servicio.ParticipanteServicioY;

@Controller
public class AsistenciaController {
    @Autowired
    ParticipanteServicioY participanteServicioY;

    @FXML private Label idMsg;
    @FXML TextField txtDat;
    //@FXMLButton btnEnviar;
    @FXML
    void enviar(){
        System.out.println("Enviando Asistencia");
        idMsg.setText(txtDat.getText());
    }
    @FXML
    void regEstudiante(){
        Participantes participantes =new Participantes();
        participantes.setNombre(new SimpleStringProperty(txtDat.getText()));
        participantes.setEstado(new SimpleBooleanProperty(true));

        participanteServicioY.save(participantes);

    }
    void ListarEstudiantes(){
        for (Participantes e: participanteServicioY.fingAll()){
            System.out.println(e.getNombre());
        }
    }

}