package general;

import javafx.scene.control.Alert;

public class AlertMessage {
    public static void showMessage(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType, content);
        alert.showAndWait();
    }
}
