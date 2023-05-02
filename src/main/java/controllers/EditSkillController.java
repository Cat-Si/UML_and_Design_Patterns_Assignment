package controllers;

import controllers.interfaces.DomainObjectToEdit;
import domain.Category;
import domain.Skill;
import general.AlertMessage;
import globals.Ioc_Container;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import router.RouteNames;
import router.Router;
import useCases.skills.EditSkill;
import useCases.skills.GetAllSkills;


import java.io.IOException;

public class EditSkillController implements DomainObjectToEdit {

    private Skill selectedSkill;
    @FXML
    private TextField skillName;
    @FXML
    private ListView<Skill> skillLst;

    private final EditSkill editSkill = new EditSkill(Ioc_Container.getSkillRepository());

    @FXML
    private void handleSubmitSkill(ActionEvent event) throws IOException {
        String name = skillName.getText();
        try {
            editSkill.requestList.add(selectedSkill.getId());
            editSkill.requestList.add(name);
            editSkill.execute();
        }catch (IllegalArgumentException e){
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
        Router.changeRoute(RouteNames.SHOW_SKILL, event);
        }


    @Override
    public void passItemToEdit(Object skillToEdit) {
        selectedSkill = (Skill) skillToEdit;
        skillName.setText(selectedSkill.getSkillName());
    }
}

