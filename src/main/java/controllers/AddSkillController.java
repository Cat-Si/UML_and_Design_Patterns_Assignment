package controllers;

import Exceptions.EntryAlreadyExistsException;
import controllers.facade.AddSkillUseCaseFacade;
import controllers.facade.EditStaffUseCaseFacade;
import domain.Category;
import general.AlertMessage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import useCases.category.categoryFactory.CategoryFactory;
import useCases.category.categoryFactory.UseCaseQuery;
import useCases.skills.skillFactory.SkillFactory;
import useCases.UseCaseCommand;


public class AddSkillController  {
    @FXML
    private TextField skillName;

    @FXML
    private ComboBox<Category> category;

    private final AddSkillUseCaseFacade addSkillUseCaseFacade = new AddSkillUseCaseFacade();
//    private final UseCaseCommand addNewSkill = SkillFactory.createCommand(SkillFactory.CommandType.add);
//    private final UseCaseQuery getAllCategories = CategoryFactory.createQuery(CategoryFactory.CommandType.view);

    public void initialize() {
        Platform.runLater(() -> skillName.requestFocus());
        showAllCatagories();
    }


    @FXML
    private void handleSubmitSkill() {
        String name = skillName.getText();

        try {
            addSkillUseCaseFacade.addNewSkill(name, category.getSelectionModel().getSelectedItem() );
////            addNewSkill.add(name);
////            addNewSkill.add(category.getSelectionModel().getSelectedItem());
//            addNewSkill.execute();
            clearForm();
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, "Skill Added");
        } catch (EntryAlreadyExistsException e) {
            AlertMessage.showMessage(Alert.AlertType.INFORMATION, e.getMessage());
        } catch (IllegalArgumentException e) {
            AlertMessage.showMessage(Alert.AlertType.ERROR, e.getMessage());
        }
    skillName.requestFocus();
    }
    private void clearForm(){
        skillName.clear();
        skillName.requestFocus();
    }

    private void showAllCatagories() {
        ObservableList<Category> items = FXCollections.observableArrayList(addSkillUseCaseFacade.getAllCategories());
        category.setItems(items);
        category.getSelectionModel().select(0);
    }
}
