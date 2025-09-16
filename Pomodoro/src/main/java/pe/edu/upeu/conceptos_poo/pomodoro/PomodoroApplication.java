package pe.edu.upeu.conceptos_poo.pomodoro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PomodoroApplication extends Application {
    private ConfigurableApplicationContext configurableApplicationContext;
    private Parent parent;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(PomodoroApplication.class);
        builder.application().setWebApplicationType(WebApplicationType.NONE);
        configurableApplicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main_tarea.fxml"));
        fxmlLoader.setControllerFactory(configurableApplicationContext::getBean);
        parent= fxmlLoader.load();
    }
    @Override
    public void start(Stage stage) throws Exception {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();
        stage.setScene(new Scene(parent,bounds.getWidth(), bounds.getHeight()-80));
        stage.setTitle("Spring Java-FX");
        stage.show();

    }
}
