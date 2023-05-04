package controllers;

import Exceptions.EntryAlreadyExistsException;
import controllers.utility.RetrieveSkillsAssignedToStaff;
import domain.*;
import general.AlertMessage;
import globals.Ioc_Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import useCases.skills.GetAllSkills;
import useCases.staff.GetAllStaff;
import useCases.staffSkill.AddSkillToStaff;
import useCases.staffSkill.FindSkillsAssignedToStaff;
import useCases.staffSkill.RemoveSkillAssignedToStaff;

public class AddSkillToUserController {

    @FXML
    private ListView<StaffUser> staffLst;

    @FXML
    private ComboBox<Skill> skillLst;

    @FXML
    private ListView<UserSkill> staffSkillLst;
    @FXML
    private UserSkill selectedSkill;
    @FXML
    private DatePicker expiry;
    @FXML
    private ListView<SkillLevel> skillLevelLst;

    @FXML
    private TextField skillName;



    private final GetAllStaff getAllStaff = new GetAllStaff(Ioc_Container.getStaffUserRepository());

    private final GetAllSkills getAllSkills = new GetAllSkills(Ioc_Container.getSkillRepository());

    private final AddSkillToStaff addSkillToStaff = new AddSkillToStaff(Ioc_Container.getUserSkillRepository());

    private final FindSkillsAssignedToStaff findSkillsAssignedToStaff = new FindSkillsAssignedToStaff(Ioc_Container.getUserSkillRepository());

    private final RemoveSkillAssignedToStaff removeSkillAssignedToStaff = new RemoveSkillAssignedToStaff(Ioc_Container.getUserSkillRepository());

    public void initialize() {
        showSkillAssignedToStaff();
        showStrengthOfSkill();
    }

    public void passItemToEdit(ActionEvent event) {//Occurs after load - via Router call
        selectedSkill = staffSkillLst.getSelectionModel().getSelectedItem();
        System.out.println(selectedSkill);
        expiry.setValue(selectedSkill.getExpiry());
        skillLevelLst.getSelectionModel().getSelectedItem(selectedSkill.getStrengthOfSkills);


    }

    @Override
    public void passItemToEdit(Object skillToEdit) {
        selectedSkill = (UserSkill) skillToEdit;
        skillName.setText(selectedSkill.getMySkill());
        skillLevelLst.getSelectionModel().select((selectedSkill.get()));
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

    private void showStrengthOfSkill() {
        ObservableList<SkillLevel> items = FXCollections.observableArrayList(SkillLevel.values());
        skillLevelLst.setItems(items);
    }
    private void showAllSkill() {
        ObservableList<Skill> items = FXCollections.observableArrayList(getAllSkills.execute());
        skillLst.setItems(items);
        skillLst.getSelectionModel().select(0);
    }

    private void showSkillAssignedToStaff() {
        RetrieveSkillsAssignedToStaff.retrieveSkillsAssignedToStaff(findSkillsAssignedToStaff, staffLst.getSelectionModel().getSelectedItem(), SkillLst);
    }

}
