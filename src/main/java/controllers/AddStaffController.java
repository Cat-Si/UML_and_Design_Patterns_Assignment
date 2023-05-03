package controllers;

import Exceptions.EntryAlreadyExistsException;
import domain.StaffUser;
import domain.User;
import general.AlertMessage;
import globals.Ioc_Container;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import useCases.staff.AddNewStaff;


public class AddStaffController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ListView<StaffUser.JobRole> jobRoleLst;
    @FXML
    private  ListView<User.SystemRole> systemRoleLst;

    private final AddNewStaff addNewStaff = new AddNewStaff(Ioc_Container.getStaffUserRepository());

    public void initialize() {
        Platform.runLater(() -> firstName.requestFocus());
        showJobRole();
        showSystemRole();
    }

    @FXML
    private void handleAddStaff() {
        String forname = firstName.getText();
        String surname = lastName.getText();
        String user = username.getText();
        String pass = password.getText();
        StaffUser.JobRole selectedJobRole = jobRoleLst.getSelectionModel().getSelectedItem();
        User.SystemRole selectedSystemRole = systemRoleLst.getSelectionModel().getSelectedItem();
        try {
            addNewStaff.requestList.add(forname);
            addNewStaff.requestList.add(surname);
            addNewStaff.requestList.add(user);
            addNewStaff.requestList.add(pass);
            addNewStaff.requestList.add(selectedJobRole);
            addNewStaff.requestList.add(selectedSystemRole);
            addNewStaff.execute();
            clearForm();
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Staff member added");
        }  catch (EntryAlreadyExistsException e){
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, e.getMessage());
        }
        catch (IllegalArgumentException e){
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
        firstName.requestFocus();
    }

    private void clearForm() {
        firstName.clear();
        lastName.clear();
        username.clear();
        password.clear();
    }

    private void showJobRole() {
        ObservableList<StaffUser.JobRole> items = FXCollections.observableArrayList(StaffUser.JobRole.values());
        jobRoleLst.setItems(items);
        jobRoleLst.getSelectionModel().selectFirst();
    }

    private void showSystemRole() {
        ObservableList<User.SystemRole> items = FXCollections.observableArrayList(User.SystemRole.values());
        systemRoleLst.setItems(items);
        systemRoleLst.getSelectionModel().selectFirst();
    }

}
