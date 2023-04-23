package controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import router.RouteNames;
import router.Router;

import java.io.IOException;

public class NavBarController {
    @FXML
    private void handleViewHome(ActionEvent event)throws IOException {
        Router.changeRoute(RouteNames.HOME, event);
    }



    @FXML
    private void handleQuitAction(ActionEvent event) {
        Platform.exit();
    }
}