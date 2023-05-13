package controllers;


import domain.*;
import domain.enumerators.JobRole;
import domain.enumerators.SystemRole;
import general.AlertMessage;
import globals.Ioc_Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import useCases.skills.GetAllSkills;
import useCases.staff.EditStaff;
import useCases.staff.GetAllManagers;
import useCases.staff.GetAllStaff;
import useCases.staffSkill.FindSkillsAssignedToStaff;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    private ComboBox<JobRole> jobRoleLst;
    @FXML
    private ComboBox<SystemRole> systemRoleLst;
    @FXML
    private ComboBox<Manager> manager;

    @FXML
    private ComboBox<StaffUser> usersLst;

    @FXML
    private  ListView<Skill> staffSkillLst;

    @FXML
    private ListView<StaffUser> staffLst;

    @FXML
    private ListView<Skill> skillLst;


    private final GetAllStaff getAllStaff = new GetAllStaff(Ioc_Container.getStaffUserRepository());
    private final GetAllSkills getAllSkills = new GetAllSkills(Ioc_Container.getSkillRepository());
    private final GetAllManagers getAllManagers = new GetAllManagers(Ioc_Container.getManagerRepository());
    private final EditStaff editStaff = new EditStaff(Ioc_Container.getStaffUserRepository());
    private final FindSkillsAssignedToStaff findSkillsAssignedToStaff = new FindSkillsAssignedToStaff(Ioc_Container.getUserSkillRepository());





    public void initialize() {
        showAllStaff();
        showJobRole();
        showSystemRole();
        showManager();
        showAllSkill();
        showSkillAssignedToStaff();
        usersLst.setPromptText("Please Select a Staff User");
    }



    public void passItemToEdit() {//Occurs after load - via Router call
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
    private void handleEditStaff() throws IOException {
        String forname = firstName.getText();
        String surname = lastName.getText();
        String user = username.getText();
        String pass = password.getText();
        SystemRole selectedSystemRole = systemRoleLst.getSelectionModel().getSelectedItem();
        JobRole selectedJobRole = jobRoleLst.getSelectionModel().getSelectedItem();
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
        ObservableList<JobRole> items = FXCollections.observableArrayList(JobRole.values());
        jobRoleLst.setItems(items);
    }

    private void showSystemRole() {
        ObservableList<SystemRole> items = FXCollections.observableArrayList(SystemRole.values());
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


    private void showAllSkill() {
        ObservableList<Skill> items = FXCollections.observableArrayList(getAllSkills.execute());
        skillLst.setItems(items);
        skillLst.getSelectionModel().select(0);
    }

    @FXML
    private void showSkillAssignedToStaff() {
        findSkillsAssignedToStaff.requestList.add(staffLst.getSelectionModel().getSelectedItem());
        Optional<List<Skill>> staffSkill = findSkillsAssignedToStaff.execute();

        if (staffSkill.isPresent()) {
            ObservableList<Skill> items = FXCollections.observableArrayList(staffSkill.get());
            staffSkillLst.setItems(items);
        } else {
            staffSkillLst.getItems().clear();
        }
    }

}
