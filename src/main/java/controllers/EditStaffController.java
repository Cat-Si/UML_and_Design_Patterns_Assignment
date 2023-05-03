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
    private  ListView<User.SystemRole> systemRoleLst;
    @FXML
    private ListView<StaffUser> staffLst;

    private final GetAllStaff getAllStaff = new GetAllStaff(Ioc_Container.getStaffUserRepository());

    private final EditStaff editStaff = new EditStaff(Ioc_Container.getStaffUserRepository());

    public void initialize() {
        showAllStaff();
        showJobRole();
        showSystemRole();
    }

    private void showAllStaff() {
        ObservableList<StaffUser> items = FXCollections.observableArrayList(getAllStaff.execute());
        staffLst.setItems(items);
        staffLst.getSelectionModel().selectFirst();
    }

    private void showJobRole() {
        ObservableList<StaffUser.JobRole> items = FXCollections.observableArrayList(StaffUser.JobRole.values());
        jobRoleLst.setItems(items);
        jobRoleLst.getSelectionModel().select(StaffUser.getStaffRole());
    }

    private void showSystemRole() {
        ObservableList<User.SystemRole> items = FXCollections.observableArrayList(User.SystemRole.values());
        systemRoleLst.setItems(items);
        systemRoleLst.getSelectionModel().select(User.getSystemRole());
    }
}
