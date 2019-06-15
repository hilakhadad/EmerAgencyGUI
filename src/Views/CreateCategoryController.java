package Views;

import Controller.Controller;
import Objects.Category;
import Objects.Event;
import Objects.User;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Date;

public class CreateCategoryController {

    public TextArea txt_categoryName;
    public TextArea txt_categoryDescription;
    Controller controller;

    public void setController(Controller c){
        controller=c;
    }

    public void addCategory() {
        Alert alert;
        if (txt_categoryName.getText().equals("")) {
            alert = new Alert(Alert.AlertType.ERROR, "you must fill the category name");
            alert.show();
        } if (txt_categoryDescription.getText().equals("")) {
            alert = new Alert(Alert.AlertType.ERROR, "you must fill the category description");
            alert.show();
        } else {
            Category newCategory = new Category(txt_categoryName.getText(), txt_categoryDescription.getText());
            if (!createCategory(newCategory)) {
                alert = new Alert(Alert.AlertType.ERROR, "the category you try to add is already exist");
                alert.show();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION, "category added successfully");
                alert.show();
                ((Stage)txt_categoryDescription.getScene().getWindow()).close();
            }
        }
    }

    private boolean createCategory(Category newCategory){
        return controller.createCategory(newCategory);
    }
}