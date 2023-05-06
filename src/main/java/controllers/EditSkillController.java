package controllers;

import controllers.interfaces.DomainObjectToEdit;
import domain.Category;
import domain.Skill;
import general.AlertMessage;
import globals.Ioc_Container;
import javafx.application.Platform;
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
import useCases.skills.EditSkill;
import useCases.skills.GetAllCategories;


import java.io.IOException;

public class EditSkillController implements DomainObjectToEdit {

    private Skill selectedSkill;
    @FXML
    private ComboBox<Category> categoryLst;

    @FXML
    private TextField skillName;

    private final EditSkill editSkill = new EditSkill(Ioc_Container.getSkillRepository());

    private final GetAllCategories getAllCategories = new GetAllCategories(Ioc_Container.getCategoryRepository());
    public void initialize() {
        Platform.runLater(() -> skillName.requestFocus());
        showAllCategories();
    }
    @Override
    public void passItemToEdit(Object skillToEdit) {
        selectedSkill = (Skill) skillToEdit;
        skillName.setText(selectedSkill.getSkillName());
        categoryLst.getSelectionModel().select((selectedSkill.getCategory()));
    }

    @FXML
    private void handleSubmitSkill(ActionEvent event) throws IOException {
        String name = skillName.getText();
       Category category = categoryLst.getSelectionModel().getSelectedItem();
        try {
            editSkill.requestList.add(category);
            editSkill.requestList.add(selectedSkill.getId());
            editSkill.requestList.add(name);
            editSkill.execute();
        }catch (IllegalArgumentException e){
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
        Router.changeRoute(RouteNames.SHOW_SKILL, event);
        }

    private void showAllCategories() {
        ObservableList<Category> items = FXCollections.observableArrayList(getAllCategories.execute());
        categoryLst.setItems(items);
    }

}

