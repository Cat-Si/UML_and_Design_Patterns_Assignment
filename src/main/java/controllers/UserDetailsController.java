package controllers;


import domain.Manager;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import router.RouteNames;
import domain.StaffUser;
import general.AlertMessage;
import globals.Ioc_Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import router.Router;
import useCases.staff.EditStaff;
import useCases.staff.GetAllStaff;

import java.io.IOException;
import java.util.List;

public class UserDetailsController {


    private StaffUser selectedUser;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<SystemRole> systemRoleLst;

    @FXML
    private ComboBox<JobRole> jobRoleLst;
    @FXML
    private ComboBox<Manager> manager;

    @FXML
    private ListView<StaffUser> usersLst;



    private final EditStaff editStaff = new EditStaff(Ioc_Container.getStaffUserRepository());

    private final GetAllStaff getAllStaff = new GetAllStaff(Ioc_Container.getStaffUserRepository());
    public void initialize() {
        showJobRole();
        showAllStaff();



    }



    public void passItemToEdit(MouseEvent mouse) {//Occurs after load - via Router call
        selectedUser = usersLst.getSelectionModel().getSelectedItem();
        System.out.println(selectedUser);
        firstName.setText(selectedUser.getFirstName());
        lastName.setText(selectedUser.getSurname());
        username.setText(selectedUser.getUsername());
        password.setText(selectedUser.getPassword());
        systemRoleLst.getSelectionModel().select(selectedUser.getSystemRole());
        jobRoleLst.getSelectionModel().select(selectedUser.getStaffRole());
        manager.getSelectionModel().select(selectedUser.getCurrentManager());

    }

    @FXML
    private void handleEditDetails(ActionEvent event) throws IOException {
        String forname = firstName.getText();
        String surname = lastName.getText();
        String user = username.getText();
        String pass = password.getText();
       SystemRole selectedSystemRole = systemRoleLst.getSelectionModel().getSelectedItem();
        JobRole selectedJobRole = jobRoleLst.getSelectionModel().getSelectedItem();
        Manager selectedManager = manager.getSelectionModel().getSelectedItem();
        try {
            editStaff.add(selectedUser.getId());
            editStaff.add(forname);
            editStaff.add(surname);
            editStaff.add(user);
            editStaff.add(pass);
            editStaff.add(selectedSystemRole);
            editStaff.add(selectedJobRole);
            editStaff.add(selectedManager);
            editStaff.execute();
        }catch (IllegalArgumentException e){
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }

        Router.changeRoute(RouteNames.USER_DETAILS, event);
    }

    private void showJobRole() {
        ObservableList<JobRole> items = FXCollections.observableArrayList(JobRole.values());
        jobRoleLst.setItems(items);
    }

/*
    private void loggedInUser() {
        StaffUser loggedInUser = Ioc_Container.getStaffUserRepository().getAll().get(0);

    }
*/

    private void showAllStaff() {
        List<StaffUser> allStaff = getAllStaff.execute();
        StaffUser firstUser = allStaff.get(0);
        ObservableList<StaffUser> items = FXCollections.observableArrayList(firstUser);
        usersLst.setItems(items);
    }


}
