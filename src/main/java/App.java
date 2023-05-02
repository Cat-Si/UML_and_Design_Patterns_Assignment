import general.ScreenHelp;
import globals.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        new InitialiseSetUp();
        try {
            stage.hide();
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/views/Home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            root.getStylesheets()
                    .add(getClass()
                            .getResource("/css/style.css")
                            .toExternalForm());
            stage.setResizable(false);
            stage.setTitle("Staff Skill Application");
            stage.initStyle(StageStyle.UNDECORATED); //Remove min, max and close buttons
            stage.setScene(scene);
            stage.show();
            ScreenHelp.centreScreen(stage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //This is my original start by tried the edited version incase that would run
   /* public void start(Stage stage) *//*throws Exception *//*{
        new InitialiseSetUp();
        Parent root = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
        root.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED); //Remove min, max and close buttons
        Scene scene = new Scene(root, 620, 440);

        stage.setScene(scene);
        stage.show();
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}

