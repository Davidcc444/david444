package pe.edu.upeu.conceptos_poo.pomodoro.controler;


import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.conceptos_poo.pomodoro.enums.EstadoTarea;
import pe.edu.upeu.conceptos_poo.pomodoro.modelo.Tarea;
import pe.edu.upeu.conceptos_poo.pomodoro.servicio.PomodoroManager;
import pe.edu.upeu.conceptos_poo.pomodoro.servicio.PomodoroServicio;
import pe.edu.upeu.conceptos_poo.pomodoro.util.SoundUtil;

@Controller
public class TareaController {
    @FXML
    private TextField txtNombres, txtDescripcion,txtDuracion;

    @FXML
    private ComboBox<EstadoTarea> cbxTipoParticipante;

    @FXML
    private TableView<Tarea> tableView;
    ObservableList<Tarea> listaTareas;
    @FXML
    private TableColumn<Tarea, String> nombreColum, descripcionColum, duracionColum, estadoTareaColum;
    private TableColumn<Tarea, Void> opcColum;
    PomodoroManager pomodoroManager = new PomodoroManager();

    @Autowired
    PomodoroServicio ps;
    int indexE=-1;

    private PomodoroManager pomodoroManager1 = new PomodoroManager();


    @FXML
    public void initialize(){
        cbxTipoParticipante.getItems().setAll(EstadoTarea.values());
        definirColumnas();
        listarParticipantes();
        configurarPomodoro();
        pomodoroManager1.start();

    }


    public void limpiarFormulario(){
        txtNombres.setText("");
        txtDescripcion.setText("");
        txtDuracion.setText("");
        cbxTipoParticipante.setValue(null);
    }


    @FXML
    public void registrarParticipante(){
        Tarea p = new Tarea();
        p.setNombre(new SimpleStringProperty(txtNombres.getText()));
        p.setDescripcion(new SimpleStringProperty(txtDescripcion.getText()));
        p.setDuracion(new SimpleStringProperty(txtDuracion.getText()));
        p.setEstadoTarea(cbxTipoParticipante.getSelectionModel().getSelectedItem());
        if(indexE==-1){
            ps.save(p);
        }else{
            ps.update(p,  indexE);
            indexE=-1;
        }
        limpiarFormulario();
        listarParticipantes();
    }


    public void definirColumnas(){
        nombreColum=new TableColumn("Nombre");
        descripcionColum=new TableColumn("Descripcion");
        duracionColum=new TableColumn("Duracion");
        estadoTareaColum=new TableColumn("Estado de la Tarea");
        opcColum=new TableColumn("Opciones");
        opcColum.setPrefWidth(300);
        tableView.getColumns().addAll(nombreColum, descripcionColum, duracionColum, estadoTareaColum, opcColum);
    }


    public void agregarAccionBotones(){
        Callback<TableColumn<Tarea, Void> , TableCell<Tarea, Void> > cellFactory =
                param->new  TableCell<>(){
                    private final Button editarBtn = new Button("Editar");
                    private final Button eliminarBtn = new Button("Eliminar");
                    private final Button cancelarBtn = new Button("Cancelar");
                    private final Button terminarBtn = new Button("Terminar");

                    {
                        editarBtn.setOnAction(event -> {
                            Tarea p=getTableView().getItems().get(getIndex());
                            editarDatos(p, getIndex());
                        });
                        eliminarBtn.setOnAction(event -> {
                            eliminarParticipante(getIndex());
                        });
                        cancelarBtn.setOnAction(event -> {
                            cambiarEstadoTarea(getIndex(), EstadoTarea.CANCELADA);
                        });

                        terminarBtn.setOnAction(event -> {
                            cambiarEstadoTarea(getIndex(), EstadoTarea.COMPLETADA);
                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty){
                        super.updateItem(item, empty);
                        if(empty){
                            setGraphic(null);
                        }else {
                            HBox hbox = new HBox(editarBtn, eliminarBtn, cancelarBtn, terminarBtn);
                            hbox.setSpacing(5);
                            setGraphic(hbox);
                        }
                    }
                };
        opcColum.setCellFactory(cellFactory);
    }
    public void cambiarEstadoTarea(int index, EstadoTarea nuevoEstado) {
        Tarea tarea = listaTareas.get(index);
        tarea.setEstadoTarea(nuevoEstado);
        ps.update(tarea, index);
        if (nuevoEstado == EstadoTarea.COMPLETADA) {
            Platform.runLater(() -> {
                SoundUtil.play("complete.mp3");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tarea Completada");
                alert.setHeaderText(null);
                alert.setContentText("🎉 ¡Has completado la tarea: " + tarea.getNombre().getValue() + "!");
                alert.initOwner(txtNombres.getScene().getWindow());
                alert.show();
            });
        }



        listarParticipantes();
    }

    public void listarParticipantes(){
        nombreColum.setCellValueFactory(cellData->cellData.getValue().getNombre());
        descripcionColum.setCellValueFactory(cellData->cellData.getValue().getDescripcion());
        duracionColum.setCellValueFactory(cellData->cellData.getValue().getDuracion());
        estadoTareaColum.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getEstadoTarea().name()));
        agregarAccionBotones();

        listaTareas=FXCollections.observableArrayList(ps.findAll());
        tableView.setItems(listaTareas);
    }
    public void eliminarParticipante(int index){
        ps.delete(index);
        listarParticipantes();
    }

    public void editarDatos(Tarea p, int index){
        txtNombres.setText(p.getNombre().getValue());
        txtDescripcion.setText(p.getDescripcion().getValue());
        txtDuracion.setText(p.getDuracion().getValue());
        cbxTipoParticipante.setValue(p.getEstadoTarea());
        indexE=index;
    }
    private void configurarPomodoro() {
        pomodoroManager.setOnWorkEnd(() ->
                Platform.runLater(() ->
                        new Alert(Alert.AlertType.INFORMATION,
                                "🎯 ¡25 minutos de trabajo completados!\nHora de descansar.")
                                .show()
                )
        );

        pomodoroManager.setOnBreakEnd(() ->
                Platform.runLater(() ->
                        new Alert(Alert.AlertType.INFORMATION,
                                "☕ ¡Descanso finalizado!\nVuelve al trabajo.")
                                .show()
                )
        );

        pomodoroManager.setOnCycleComplete(() ->
                Platform.runLater(() ->
                        new Alert(Alert.AlertType.INFORMATION,
                                "✅ ¡Pomodoro completo!\nHas cerrado 4 ciclos.")
                                .show()
                )
        );
    }


}
