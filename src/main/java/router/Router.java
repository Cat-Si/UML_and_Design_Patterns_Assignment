package router;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Router {
    private static final String CSS_FILE = "/css/style.css";

    public static void changeRoute(RouteNames route, ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Router.class.getResource(route.getLocation()));
        Parent root  = fxmlLoader.load();
        Scene scene = new Scene(root);
        root.getStylesheets().add(Router.class.getResource(CSS_FILE).toExternalForm());

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
