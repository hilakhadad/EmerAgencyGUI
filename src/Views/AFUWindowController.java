package Views;

import Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AFUWindowController {
    Controller controller;

    public void setController(Controller c){
        controller=c;
    }


    public void handelUpdateEvent(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("UpdateEventWindow.fxml").openStream());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Update Event");
            stage.setResizable(false);
            UpdateEventAController sceneController = loader.getController();
            sceneController.setController(controller);
            sceneController.setChoiceBoxItems();
            stage.showAndWait();

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void handelFilingComplaint(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("FilingComplaintWindow.fxml").openStream());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Filing Complaint");
            stage.setResizable(false);
            FilingComplaintController sceneController = loader.getController();
            sceneController.setController(controller);
            sceneController.setChoiceBoxItems();
            stage.showAndWait();

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }
}
