package pe.edu.upeu.conceptos_poo.pomodoro.servicio;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class PomodoroManager {
    private static final int WORK_MIN   = 1;
    private static final int BREAK_MIN  = 1;
    private static final int TOTAL_CYCLES = 4;

    private int cyclesCompleted = 0;
    private boolean isWorkPhase = true;
    private Timeline timeline;

    private Runnable onWorkEnd;
    private Runnable onBreakEnd;
    private Runnable onCycleComplete;

    public void setOnWorkEnd(Runnable r)        { onWorkEnd = r; }
    public void setOnBreakEnd(Runnable r)       { onBreakEnd = r; }
    public void setOnCycleComplete(Runnable r)  { onCycleComplete = r; }

    /** Inicia automáticamente trabajo→descanso→… */
    public void start() {
        if (cyclesCompleted >= TOTAL_CYCLES) {
            playSound("complete.mp3");

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pomodoro Finalizado");
                alert.setHeaderText(null);
                alert.setContentText("🎉 ¡Has completado los 4 ciclos Pomodoro!");
                alert.show();
            });

            if (onCycleComplete != null) {
                Platform.runLater(onCycleComplete);
            }
            return;
        }

        int minutes = isWorkPhase ? WORK_MIN : BREAK_MIN;

        timeline = new Timeline(new KeyFrame(Duration.minutes(minutes), e -> {
            String sound = isWorkPhase ? "work_end.mp3" : "break_end.mp3";
            playSound(sound);

            if (isWorkPhase) {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Fin del Trabajo");
                    alert.setHeaderText(null);
                    alert.setContentText("⏰ ¡25 minutos completados!\nTómate un descanso de 5 minutos.");
                    alert.show();
                });

                if (onWorkEnd != null) {
                    Platform.runLater(onWorkEnd);
                }
            } else {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Fin del Descanso");
                    alert.setHeaderText(null);
                    alert.setContentText("☕ ¡Descanso terminado!\nEs hora de volver al trabajo.");
                    alert.show();
                });

                if (onBreakEnd != null) {
                    Platform.runLater(onBreakEnd);
                }

                cyclesCompleted++;
            }

            isWorkPhase = !isWorkPhase;
            start(); // inicia la siguiente fase automáticamente
        }));

        timeline.setCycleCount(1);
        timeline.play();
    }

    public void stop() {
        if (timeline != null) timeline.stop();
    }

    /** Reproduce un MP3 desde /resources/sounds */
    private void playSound(String fileName) {
        String path = getClass()
                .getResource("/sounds/" + fileName)
                .toExternalForm();
        MediaPlayer player = new MediaPlayer(new Media(path));
        player.play();
    }
}