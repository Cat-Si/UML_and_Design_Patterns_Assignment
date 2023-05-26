package controllers;

import Exceptions.EntryAlreadyExistsException;
import domain.Manager;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import general.AlertMessage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import useCases.UseCaseCommand;
import useCases.manager.ManagerFactory;
import useCases.manager.UseCaseQuery;
import useCases.staff.staffFactory.StaffFactory;


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
    private  ComboBox<SystemRole> systemRoleLst;
    @FXML
    private ComboBox<JobRole> jobRoleLst;
    @FXML
    private ComboBox<Manager> manager;

    private final UseCaseCommand addNewStaff = StaffFactory.createCommand(StaffFactory.CommandType.add);
    private final UseCaseQuery getAllManagers = ManagerFactory.createQuery(ManagerFactory.CommandType.view);
    public void initialize() {
        Platform.runLater(() -> firstName.requestFocus());
        showJobRole();
        showSystemRole();
        showManager();
    }

    @FXML
    private void handleAddStaff() {
        String forname = firstName.getText();
        String surname = lastName.getText();
        String user = username.getText();
        String pass = password.getText();
        SystemRole selectedSystemRole = systemRoleLst.getSelectionModel().getSelectedItem();
        JobRole selectedJobRole = jobRoleLst.getSelectionModel().getSelectedItem();
        Manager selectedManager = manager.getSelectionModel().getSelectedItem();
        try {
            addNewStaff.add(forname);
            addNewStaff.add(surname);
            addNewStaff.add(user);
            addNewStaff.add(pass);
            addNewStaff.add(selectedSystemRole);
            addNewStaff.add(selectedJobRole);
            addNewStaff.add(selectedManager);
            addNewStaff.execute();
            clearForm();
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Staff member added");
        }
        catch (EntryAlreadyExistsException e){
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
        ObservableList<JobRole> items = FXCollections.observableArrayList(JobRole.values());
        jobRoleLst.setItems(items);
        jobRoleLst.setPromptText("Please Select");

    }

    private void showManager() {
        ObservableList<Manager> items = FXCollections.observableArrayList(getAllManagers.execute());
        manager.setItems(items);
        manager.setPromptText("Please Select");

    }

    private void showSystemRole() {
        ObservableList<SystemRole> items = FXCollections.observableArrayList(SystemRole.values());
        systemRoleLst.setItems(items);
        systemRoleLst.setPromptText("Please Select");

    }

}
