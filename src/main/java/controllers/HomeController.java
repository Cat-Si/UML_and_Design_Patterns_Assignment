package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import router.RouteNames;
import router.Router;

import java.io.IOException;


public class HomeController {


    @FXML
    private void clickManager(ActionEvent event)throws IOException {
        Router.changeRoute(RouteNames.MANAGER_MAIN, event);
    }

    @FXML
    private void clickStaff(ActionEvent event)throws IOException {
        Router.changeRoute(RouteNames.STAFF_MAIN, event);
    }

}
