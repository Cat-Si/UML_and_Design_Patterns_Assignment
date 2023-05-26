package controllers;

import domain.Skill;
import general.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import router.RouteNames;
import router.Router;
import useCases.skills.skillFactory.SkillFactory;
import useCases.skills.skillFactory.UseCaseQuery;

import java.io.IOException;


public class ShowSkillController {

    private final UseCaseQuery getAllSkills = SkillFactory.createQuery(SkillFactory.CommandType.view);


    @FXML
    private ComboBox<Skill> skillList;

    public void initialize() {
        showAllSkills();
    }

    @FXML
    private void handleEditSkill(ActionEvent event) throws IOException {
        final int SKILL_NOT_FOUND = -1;
        if (skillList.getSelectionModel().getSelectedIndex() != SKILL_NOT_FOUND) {
            Router.changeRouteToEdit(RouteNames.EDIT_SKILL, event,
                    skillList.getSelectionModel().getSelectedItem());
        } else {
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Please select a Skill to edit");
        }
    }

    private void showAllSkills() {
        ObservableList<Skill> items = FXCollections.observableArrayList(getAllSkills.execute());
        skillList.setItems(items);
        skillList.getSelectionModel().selectFirst();
    }
}
