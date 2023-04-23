package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import router.RouteNames;
import router.Router;

import java.io.IOException;

public class NavBarStaffController {


    @FXML
    private void userSkill(ActionEvent event)throws IOException {
        Router.changeRoute(RouteNames.USER_SKILL, event);
    }

    @FXML
    private void userDetails(ActionEvent event)throws IOException {
        Router.changeRoute(RouteNames.USER_DETAILS, event);
    }


    @FXML
    private void handleQuitAction(ActionEvent event) {
        Platform.exit();
    }
}




