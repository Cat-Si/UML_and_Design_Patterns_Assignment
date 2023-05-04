package controllers;


import controllers.utility.RetrieveSkillsAssignedToStaff;
import domain.Manager;
import domain.Skill;
import domain.StaffUser;
import domain.User;
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
import repositories.interfaces.BaseStaffUserRepository;
import useCases.staff.EditStaff;
import useCases.staff.GetAllManagers;
import useCases.staff.GetAllStaff;
import useCases.staff.LoggedInUser;
import useCases.staffSkill.FindSkillsAssignedToStaff;

import java.io.IOException;

public class UserDetailsController {

   private StaffUser loggedInUser = Ioc_Container.getStaffUserRepository().getAll().get(0);;
    private StaffUser selectedUser;

    @FXML
    private ComboBox loggedUser;
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
    private ComboBox<StaffUser> usersLst;



    private final EditStaff editStaff = new EditStaff(Ioc_Container.getStaffUserRepository());


    public void initialize() {
        showJobRole();
        loggedInUser();


    }



    public void passItemToEdit(ActionEvent event) {//Occurs after load - via Router call
        selectedUser = loggedInUser;
        System.out.println(selectedUser);
        loggedUser.getSelectionModel().select(selectedUser);
        firstName.setText(selectedUser.getFirstName());
        lastName.setText(selectedUser.getSurname());
        username.setText(selectedUser.getUsername());
        password.setText(selectedUser.getPassword());

        jobRoleLst.getSelectionModel().select(selectedUser.getStaffRole());


    }

    @FXML
    private void handleEditDetails() throws IOException {
        String forname = firstName.getText();
        String surname = lastName.getText();
        String user = username.getText();
        String pass = password.getText();

        StaffUser.JobRole selectedJobRole = jobRoleLst.getSelectionModel().getSelectedItem();

        try {
            editStaff.requestList.add(loggedInUser);
            editStaff.requestList.add(loggedInUser.getId());
            editStaff.requestList.add(forname);
            editStaff.requestList.add(surname);
            editStaff.requestList.add(user);
            editStaff.requestList.add(pass);
            editStaff.requestList.add(selectedJobRole);
            editStaff.execute();
        }catch (IllegalArgumentException e){
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }

    }

    private void showJobRole() {
        ObservableList<StaffUser.JobRole> items = FXCollections.observableArrayList(StaffUser.JobRole.values());
        jobRoleLst.setItems(items);
    }

    private void loggedInUser() {
        StaffUser loggedInUser = Ioc_Container.getStaffUserRepository().getAll().get(0);

    }



}
