package controllers.utility;

import domain.Skill;
import domain.StaffUser;
import domain.UserSkill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import useCases.staffSkill.FindSkillsAssignedToStaff;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RetrieveSkillsAssignedToStaff {
    public static void retrieveSkillsAssignedToStaff(FindSkillsAssignedToStaff findSkillsAssignedToStaff, StaffUser staffLst, ListView<UserSkill> staffSkillLst) {
        findSkillsAssignedToStaff.requestList.add(staffLst);
        Optional<List<Skill>> staffSkill = findSkillsAssignedToStaff.execute();

        if (staffSkill.isPresent()) {
            ObservableList<UserSkill> items = FXCollections.observableArrayList();
            staffSkillLst.setItems(items);
        } else {
            staffSkillLst.getItems().clear();
        }
    }
}
