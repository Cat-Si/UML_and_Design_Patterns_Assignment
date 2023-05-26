package controllers;

import Exceptions.EntryAlreadyExistsException;
import domain.Manager;
import domain.Skill;
import domain.UserSkill;
import domain.enumerators.JobRole;
import domain.enumerators.SkillLevel;
import domain.StaffUser;
import domain.enumerators.SystemRole;
import general.AlertMessage;
import globals.Ioc_Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import useCases.UseCaseCommand;
import useCases.skills.GetAllSkills;
import useCases.staff.GetAllStaff;
import useCases.staff.staffFactory.StaffFactory;
import useCases.staff.staffFactory.UseCaseQuery;
import useCases.staffSkill.AddSkillToStaff;
import useCases.staffSkill.EditUserSkill;
import useCases.staffSkill.FindSkillsAssignedToStaff;
import useCases.staffSkill.RemoveSkillAssignedToStaff;
import useCases.staffSkill.staffSkillFactory.StaffSkillFactory;
import useCases.staffSkill.staffSkillFactory.UseCaseCommandFind;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AddSkillToUserController {

    @FXML
    private UserSkill selectedSkill;


//    @FXML
//    private ListView<StaffUser> staffLst;
//
//    @FXML
//    private ListView<Skill> skillLst;

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

    private StaffUser selectedStaff;


    private final UseCaseQuery getAllStaff = StaffFactory.createQuery(StaffFactory.CommandType.view);

    private final UseCaseCommandFind findSkillsAssignedToStaff = StaffSkillFactory.createCommandFind(StaffSkillFactory.CommandType.view);

    private final RemoveSkillAssignedToStaff removeSkillAssignedToStaff = new RemoveSkillAssignedToStaff(Ioc_Container.getUserSkillRepository());


    public void initialize() {

        showStaffSkills();


        strengthCombo.getItems().setAll(Arrays.asList(SkillLevel.values()));
        staffSkillLst.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserSkill>() {
            @Override
            public void changed(ObservableValue<? extends UserSkill> observable, UserSkill oldValue, UserSkill newValue) {
                selectedSkill = newValue;
                System.out.println(selectedSkill);
                skillName.setText(selectedSkill.getSkill().getSkillName().toString());
                notes.setText(selectedSkill.getNotes());
                strengthCombo.getSelectionModel().select(selectedSkill.getStrengthOfSkill());
                expiry.setValue(selectedSkill.getExpiry().get());
            }
        });
    }


    private void showStaffSkills()  {
        StaffUser selectedStaff = getAllStaff.execute().get(0);
        findSkillsAssignedToStaff.add(selectedStaff);
        Optional<List<UserSkill>> staffSkill = findSkillsAssignedToStaff.execute();
        ObservableList<UserSkill> items = FXCollections.observableArrayList(staffSkill.get());
        staffSkillLst.setItems(items);
    }

  /*  @Override
    public void passItemToEdit(Object skillToEdit) {
        selectedSkill = (UserSkill) skillToEdit;
        skillName.setText(selectedSkill.getSkill().toString());
        categoryLst.getSelectionModel().select((selectedSkill.getCategory()));
    }*/
/*    @FXML
    private void handleEditUserSkill() throws IOException {
        StaffUser staff = selectedStaff;
        Skill skill = staffSkillLst.getSelectionModel().getSelectedItem().getSkill();
        SkillLevel skillStrength = strengthCombo.getSelectionModel().getSelectedItem();
        LocalDate skillExpiry = expiry.getValue();
        String skillNotes = notes.getText();
        try {
            editUserSkill.requestList.add(selectedSkill.getId());
            editUserSkill.requestList.add(staff);
            editUserSkill.requestList.add(selectedSkill.getSkill());
            editUserSkill.requestList.add(skillStrength);
            editUserSkill.requestList.add(skillExpiry);
            editUserSkill.requestList.add(skillNotes);
            editUserSkill.execute();
            showStaffSkills();
        }catch (IllegalArgumentException e){
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }

    }*/

   /* @FXML
    private void handleAddSkill() {
        addSkillToStaff.requestList.add(selectedStaff);
        addSkillToStaff.requestList.add(staffSkillLst.getSelectionModel().getSelectedItem());

        try {
            addSkillToStaff.execute();
            showStaffSkills();
        } catch (EntryAlreadyExistsException e) {
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
    }*/

    @FXML
    private void handleRemoveSkill() {
        removeSkillAssignedToStaff.add(selectedStaff);
        removeSkillAssignedToStaff.add(selectedSkill);

        try {
            removeSkillAssignedToStaff.execute();
            showStaffSkills();
        } catch (IllegalArgumentException e) {
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }




    }
}




