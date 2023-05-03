package controllers;

import controllers.interfaces.DomainObjectToEdit;
import domain.Skill;
import domain.StaffUser;
import domain.User;
import globals.Ioc_Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import useCases.staff.EditStaff;
import useCases.staff.GetAllStaff;

public class EditStaffController implements DomainObjectToEdit {

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
    private ListView<StaffUser.JobRole> jobRoleLst;
    @FXML
    private ListView<User.SystemRole> systemRoleLst;


    private final EditStaff editStaff = new EditStaff(Ioc_Container.getStaffUserRepository());

    public void initialize() {
        showJobRole();
        showSystemRole();
    }


    private void showJobRole() {
        ObservableList<StaffUser.JobRole> items = FXCollections.observableArrayList(StaffUser.JobRole.values());
        jobRoleLst.setItems(items);
    }

    private void showSystemRole() {
        ObservableList<User.SystemRole> items = FXCollections.observableArrayList(User.SystemRole.values());
        systemRoleLst.setItems(items);
    }

    @Override
    public void passItemToEdit(Object itemToEdit) {//Occurs after load - via Router call
        selectedUser = (StaffUser) itemToEdit;
        firstName.setText(selectedUser.getFirstName());//same for other fields
        lastName.setText(selectedUser.getSurname());
        username.setText(selectedUser.getUsername());
        password.setText(selectedUser.getPassword());
        systemRoleLst.getSelectionModel().select(selectedUser.getSystemRole());
        jobRoleLst.getSelectionModel().select(selectedUser.getStaffRole());
    }
}