package Views;

import Controller.Controller;
import Objects.Complaint;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AFAWindowController {
    Controller controller;

    public void setController(Controller c){
        controller=c;
    }

    public void hendleShowComplaints(){
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("ShowComplaintsWindow.fxml").openStream());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Complaints");
            stage.setResizable(false);
            ShowComplaintsController sceneController = loader.getController();
            sceneController.setController(controller);
            sceneController.showResults(fillTheComplaints());
            stage.showAndWait();
        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    private ObservableList<Complaint> fillTheComplaints(){
        ObservableList l = controller.searchAllComplaints();
        if(l== null || l.size()==0){
            Alert e = new Alert(Alert.AlertType.ERROR,"no results for this destination");
            e.show();
        }
        return l;
    }
}
