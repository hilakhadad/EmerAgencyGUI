package Views;

import Controller.Controller;
import Objects.Category;
import Objects.Event;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Date;

public class CreateCategoryController {

    public TextArea txt_categoryName;
    public TextArea txt_categoryDescription;
    private Controller controller;

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
            if (!createCategory(txt_categoryName.getText(), txt_categoryDescription.getText())) {
                alert = new Alert(Alert.AlertType.ERROR, "the category you tried to add already exists");
                alert.show();
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION, "category added successfully");
                alert.show();
                ((Stage)txt_categoryDescription.getScene().getWindow()).close();
            }
        }
    }

    private boolean createCategory(String cat_name, String cat_desc){
        return controller.createCategory(cat_name,cat_desc);
    }
}
