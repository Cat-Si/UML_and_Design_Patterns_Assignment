package controllers;

import controllers.utility.RetrieveSkillsAssignedToStaff;
import domain.*;
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
import useCases.staff.EditStaff;
import useCases.staff.GetAllManagers;
import useCases.staff.GetAllStaff;
import useCases.staffSkill.FindSkillsAssignedToStaff;

import java.io.IOException;

public class EditStaffController  {

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
    private ComboBox<StaffUser.JobRole> jobRoleLst;
    @FXML
    private ComboBox<User.SystemRole> systemRoleLst;
    @FXML
    private ComboBox<Manager> manager;

    @FXML
    private ComboBox<StaffUser> usersLst;

    @FXML
    private  ListView<Skill> staffSkillLst;

    private final FindSkillsAssignedToStaff findSkillsAssignedToStaff = new FindSkillsAssignedToStaff(Ioc_Container.getUserSkillRepository());
    private final GetAllManagers getAllManagers = new GetAllManagers(Ioc_Container.getManagerRepository());

    private final EditStaff editStaff = new EditStaff(Ioc_Container.getStaffUserRepository());
    private final GetAllStaff getAllStaff = new GetAllStaff(Ioc_Container.getStaffUserRepository());

    public void initialize() {
        showAllStaff();
        showJobRole();
        showSystemRole();
        showSkillsAssignedToStaff();
        showManager();
        usersLst.setPromptText("Please Select a Staff User");
    }



    public void passItemToEdit(ActionEvent event) {//Occurs after load - via Router call
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
    private void handleEditStaff(MouseEvent mouse) throws IOException {
        String forname = firstName.getText();
        String surname = lastName.getText();
        String user = username.getText();
        String pass = password.getText();
        User.SystemRole selectedSystemRole = systemRoleLst.getSelectionModel().getSelectedItem();
        StaffUser.JobRole selectedJobRole = jobRoleLst.getSelectionModel().getSelectedItem();
        Manager selectedManager = manager.getSelectionModel().getSelectedItem();
        try {
            editStaff.requestList.add(selectedUser.getId());
            editStaff.requestList.add(forname);
            editStaff.requestList.add(surname);
            editStaff.requestList.add(user);
            editStaff.requestList.add(pass);
            editStaff.requestList.add(selectedSystemRole);
            editStaff.requestList.add(selectedJobRole);
            editStaff.requestList.add(selectedManager);
            editStaff.execute();
        }catch (IllegalArgumentException e){
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }

    }

    private void showJobRole() {
        ObservableList<StaffUser.JobRole> items = FXCollections.observableArrayList(StaffUser.JobRole.values());
        jobRoleLst.setItems(items);
    }

    private void showSystemRole() {
        ObservableList<User.SystemRole> items = FXCollections.observableArrayList(User.SystemRole.values());
        systemRoleLst.setItems(items);
    }

    private void showAllStaff() {
        ObservableList<StaffUser> items = FXCollections.observableArrayList(getAllStaff.execute());
        usersLst.setItems(items);
        usersLst.setPromptText("Please select a staff User");
    }

    private void showManager() {
        ObservableList<Manager> items = FXCollections.observableArrayList(getAllManagers.execute());
        manager.setItems(items);

    }

    private void showSkillsAssignedToStaff() {
        RetrieveSkillsAssignedToStaff.retrieveSkillsAssignedToStaff(findSkillsAssignedToStaff, usersLst.getSelectionModel().getSelectedItem(), staffSkillLst);
        System.out.println(findSkillsAssignedToStaff);

    }

}