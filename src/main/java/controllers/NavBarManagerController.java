package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import router.RouteNames;
import router.Router;
import java.io.IOException;

public class NavBarManagerController {

    @FXML
    private void editSkill (ActionEvent event)throws IOException {
        Router.changeRoute(RouteNames.EDIT_SKILL, event);
    }

    @FXML
    private void showSkill (ActionEvent event) throws IOException {
        Router.changeRoute(RouteNames.SHOW_SKILL, event);
    }
    @FXML
    private void addSkill(ActionEvent event)throws IOException {
        Router.changeRoute(RouteNames.ADD_SKILL, event);
    }

    @FXML
    private void editStaff(ActionEvent event)throws IOException {
        Router.changeRoute(RouteNames.EDIT_STAFF, event);
    }

    @FXML
    private void addStaff(ActionEvent event)throws IOException {
        Router.changeRoute(RouteNames.ADD_STAFF, event);
    }


    @FXML
    private void handleQuitAction(ActionEvent event) {
        Platform.exit();
    }
}
