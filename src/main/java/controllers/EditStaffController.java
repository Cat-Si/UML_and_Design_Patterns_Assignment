package controllers;

import controllers.interfaces.DomainObjectToEdit;
import domain.Category;
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
import router.RouteNames;
import router.Router;
import useCases.staff.EditStaff;
import useCases.staff.GetAllStaff;
import useCases.staffSkill.FindSkillsAssignedToStaff;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    private ComboBox<StaffUser.JobRole> jobRoleLst;
    @FXML
    private ComboBox<User.SystemRole> systemRoleLst;

    @FXML
    private ComboBox<StaffUser> usersLst;

    @FXML ListView<Skill> staffSkillLst;

    private final FindSkillsAssignedToStaff findSkillsAssignedToStaff = new FindSkillsAssignedToStaff(Ioc_Container.getUserSkillRepository());
    private final EditStaff editStaff = new EditStaff(Ioc_Container.getStaffUserRepository());
    private final GetAllStaff getAllStaff = new GetAllStaff(Ioc_Container.getStaffUserRepository());

    public void initialize() {
        showAllStaff();
        showJobRole();
        showSystemRole();
        showSkillsAssignedToStaff();
    }


    @Override
    public void passItemToEdit(Object itemToEdit) {//Occurs after load - via Router call
        selectedUser = (StaffUser) itemToEdit;
        firstName.setText(selectedUser.getFirstName());
        lastName.setText(selectedUser.getSurname());
        username.setText(selectedUser.getUsername());
        password.setText(selectedUser.getPassword());
        systemRoleLst.getSelectionModel().select(selectedUser.getSystemRole());
        jobRoleLst.getSelectionModel().select(selectedUser.getStaffRole());
    }

    @FXML
    private void handleEditStaff(ActionEvent event) throws IOException {
        String forname = firstName.getText();
        String surname = lastName.getText();
        String user = username.getText();
        String pass = password.getText();
        User.SystemRole selectedSystemRole = systemRoleLst.getSelectionModel().getSelectedItem();
        StaffUser.JobRole selectedJobRole = jobRoleLst.getSelectionModel().getSelectedItem();
        try {
            editStaff.requestList.add(selectedUser.getId());
            editStaff.requestList.add(forname);
            editStaff.requestList.add(surname);
            editStaff.requestList.add(user);
            editStaff.requestList.add(pass);
            editStaff.requestList.add(selectedSystemRole);
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

    private void showSystemRole() {
        ObservableList<User.SystemRole> items = FXCollections.observableArrayList(User.SystemRole.values());
        systemRoleLst.setItems(items);
    }

    private void showAllStaff() {
        ObservableList<StaffUser> items = FXCollections.observableArrayList(getAllStaff.execute());
        usersLst.setItems(items);
        usersLst.getSelectionModel().selectFirst();
    }

    private void showSkillsAssignedToStaff() {
        findSkillsAssignedToStaff.requestList.add(usersLst.getSelectionModel().getSelectedItem());
        Optional<List<Skill>> assignedSkills = findSkillsAssignedToStaff.execute();

        if (assignedSkills.isPresent()) {
            ObservableList<Skill> items = FXCollections.observableArrayList(assignedSkills.get());
            staffSkillLst.setItems(items);
        } else {
            staffSkillLst.getItems().clear();
        }
    }
}