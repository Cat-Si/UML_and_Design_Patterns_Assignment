package controllers;

import Exceptions.EntryAlreadyExistsException;
import domain.Skill;
import domain.UserSkill;
import domain.enumerators.SkillLevel;
import domain.StaffUser;
import general.AlertMessage;
import globals.Ioc_Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import useCases.skills.GetAllSkills;
import useCases.staff.GetAllStaff;
import useCases.staffSkill.AddSkillToStaff;
import useCases.staffSkill.FindSkillsAssignedToStaff;
import useCases.staffSkill.RemoveSkillAssignedToStaff;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AddSkillToUserController {

    @FXML
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
    private DatePicker expiry;

    @FXML
    private TextField notes;

    @FXML
    private ListView<SkillLevel> strengthOfSkill;

    @FXML
    private ComboBox<SkillLevel> strengthCombo;

    private final GetAllStaff getAllStaff = new GetAllStaff(Ioc_Container.getStaffUserRepository());

    private final GetAllSkills getAllSkills = new GetAllSkills(Ioc_Container.getSkillRepository());

    private final AddSkillToStaff addSkillToStaff = new AddSkillToStaff(Ioc_Container.getUserSkillRepository());

    private final FindSkillsAssignedToStaff findSkillsAssignedToStaff = new FindSkillsAssignedToStaff(Ioc_Container.getUserSkillRepository());

    private final RemoveSkillAssignedToStaff removeSkillAssignedToStaff = new RemoveSkillAssignedToStaff(Ioc_Container.getUserSkillRepository());

    public void initialize() {
        showAllSkill();
        showAllStaff();
        showSkillAssignedToStaff();
        strengthCombo.getItems().setAll(Arrays.asList(SkillLevel.values()));
        staffSkillLst.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserSkill>() {
            @Override
            public void changed(ObservableValue<? extends UserSkill> observable, UserSkill oldValue, UserSkill newValue) {
                selectedSkill = newValue;
                skillName.setText(selectedSkill.getSkill().toString());
                strengthCombo.setValue(selectedSkill.getStrengthOfSkill());
                notes.setText(selectedSkill.getNotes().toString());
                //expiry.setValue(selectedSkill.getExpiry());// other text fields here
            }
        });
    }

    @FXML
    private void handleAddSkill() {
        addSkillToStaff.requestList.add(staffLst.getSelectionModel().getSelectedItem());
        addSkillToStaff.requestList.add(staffLst.getSelectionModel().getSelectedItem());

        try {
            addSkillToStaff.execute();
            showSkillAssignedToStaff();
        } catch (EntryAlreadyExistsException e) {
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    @FXML
    private void handleRemoveSkill() {
        removeSkillAssignedToStaff.requestList.add(staffLst.getSelectionModel().getSelectedItem());
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

    @FXML
    private void showSkillAssignedToStaff() {
        if (staffLst.getSelectionModel().getSelectedIndex()!=-1) {
            findSkillsAssignedToStaff.requestList.add(staffLst.getSelectionModel().getSelectedItem());
            Optional<List<UserSkill>> staffSkill = findSkillsAssignedToStaff.execute();
            ObservableList<UserSkill> items = FXCollections.observableArrayList(staffSkill.get());
            staffSkillLst.setItems(items);
        } else {
            staffSkillLst.getItems().clear();
        }

    }

}

