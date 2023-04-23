import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
        root.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED); //Remove min, max and close buttons
        Scene scene = new Scene(root, 620, 440);

        stage.setScene(scene);
        stage.show();
    }
}

