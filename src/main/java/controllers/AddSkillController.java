package controllers;

import Exceptions.EntryAlreadyExistsException;
import controllers.interfaces.DomainObjectToEdit;
import domain.Category;
import general.AlertMessage;
import globals.Ioc_Container;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import useCases.skills.AddNewSkill;
import useCases.skills.GetAllCategories;
import java.util.List;
import java.util.Optional;




public class AddSkillController  {
    @FXML
    private TextField skillName;

    @FXML
    private ComboBox<Category> category;


    private final AddNewSkill addNewSkill = new AddNewSkill(Ioc_Container.getSkillRepository());
    private final GetAllCategories getAllCategories = new GetAllCategories(Ioc_Container.getCategoryRepository());

    public void initialize() {
        Platform.runLater(() -> skillName.requestFocus());
        showAllCatagories();
    }


    @FXML
    private void handleSubmitSkill() {
        String name = skillName.getText();


        try {
            addNewSkill.requestList.add(name);
            addNewSkill.requestList.add(category.getSelectionModel().getSelectedItem());
            addNewSkill.execute();
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
        ObservableList<Category> items = FXCollections.observableArrayList(getAllCategories.execute());
        category.setItems(items);
        category.getSelectionModel().select(0);
    }
}
