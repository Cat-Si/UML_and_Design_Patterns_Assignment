package router;

import controllers.interfaces.DomainObjectToEdit;
import general.ScreenHelp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Router {
    private static final String CSS_FILE = "/css/style.css";
    private static FXMLLoader fxmlLoader;
    private static Stage stage;


    public static void changeRoute(RouteNames route, ActionEvent actionEvent) throws IOException {
         fxmlLoader = new FXMLLoader(Router.class.getResource(route.getLocation()));
        Parent root  = fxmlLoader.load();
        Scene scene = new Scene(root);
        root.getStylesheets().add(Router.class.getResource(CSS_FILE).toExternalForm());

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);

        ScreenHelp.centreScreen(stage);
    }
    public static void changeRouteToEdit(RouteNames route, ActionEvent actionEvent, Object itemToEdit) throws IOException {
        changeRoute(route, actionEvent);

        DomainObjectToEdit controller = fxmlLoader.getController();
        controller.passItemToEdit(itemToEdit);
    }
}
