package controllers;

import Exceptions.EntryAlreadyExistsException;
import controllers.interfaces.DomainObjectToEdit;
import controllers.utility.RetrieveSkillsAssignedToStaff;
import domain.Skill;
import TrashFiles.SkillLevel;
import domain.StaffUser;
import domain.UserSkill;
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
import useCases.staff.GetAllStaff;
import useCases.staffSkill.AddSkillToStaff;
import useCases.staffSkill.FindSkillsAssignedToStaff;
import useCases.staffSkill.RemoveSkillAssignedToStaff;

public class AddSkillToUserController implements DomainObjectToEdit {

    private UserSkill selectedSkill;

    @FXML
    private ListView<StaffUser> staffLst;

    @FXML
    private ListView<Skill> skillLst;

    @FXML
    private ListView<UserSkill> staffSkillLst;
    @FXML
    private TextField skillName;

    @FXML
    ComboBox<UserSkill.SkillLevel> strengthOfSkill;

    private final GetAllStaff getAllStaff = new GetAllStaff(Ioc_Container.getStaffUserRepository());

    private final GetAllSkills getAllSkills = new GetAllSkills(Ioc_Container.getSkillRepository());

    private final AddSkillToStaff addSkillToStaff = new AddSkillToStaff(Ioc_Container.getUserSkillRepository());

    private final FindSkillsAssignedToStaff findSkillsAssignedToStaff = new FindSkillsAssignedToStaff(Ioc_Container.getUserSkillRepository());

    private final RemoveSkillAssignedToStaff removeSkillAssignedToStaff = new RemoveSkillAssignedToStaff(Ioc_Container.getUserSkillRepository());

    public void initialize() {
        showSkillAssignedToStaff();
    }

    @Override
    public void passItemToEdit(Object skillToEdit) {
        selectedSkill = (UserSkill) skillToEdit;
        skillName.setText(selectedSkill.getSkillName());
        strengthOfSkill.getSelectionModel().select(selectedSkill.getStrengthOfSkills());
    }

    @FXML
    private void handleAssignSkill() {
    addSkillToStaff.requestList.add(staffLst.getSelectionModel().getSelectedItem());
        addSkillToStaff.requestList.add(skillLst.getSelectionModel().getSelectedItem());
        try {
            addSkillToStaff.execute();
            showSkillAssignedToStaff();
        } catch (EntryAlreadyExistsException e) {
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void handleRemoveSkill() {
        removeSkillAssignedToStaff.requestList.add(skillLst.getSelectionModel().getSelectedItem());
        removeSkillAssignedToStaff.requestList.add(staffSkillLst.getSelectionModel().getSelectedItem());
        try {
            removeSkillAssignedToStaff.execute();
            showSkillAssignedToStaff();
        } catch (IllegalArgumentException e) {
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
    }










    private void showAllStaff() {
        ObservableList<StaffUser> items = FXCollections.observableArrayList(getAllStaff.execute());
        staffLst.setItems(items);
        staffLst.getSelectionModel().select(0);
    }
    private void showAllSkill() {
        ObservableList<Skill> items = FXCollections.observableArrayList(getAllSkills.execute());
        skillLst.setItems(items);
        skillLst.getSelectionModel().select(0);
    }

    private void showSkillAssignedToStaff() {
        RetrieveSkillsAssignedToStaff.retrieveSkillsAssignedToStaff(findSkillsAssignedToStaff, staffLst.getSelectionModel().getSelectedItem(), staffSkillLst);
    }

}
