package Views;

import Controller.Controller;
import Objects.Users.Admin;
import Objects.Users.CenterAdmin;
import Objects.Users.RegularUser;
import Objects.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class View{

    Controller controller;

    public void setController(Controller c){
        controller=c;
    }

    public void handleLogInAsAFA(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("AFAWindow.fxml").openStream());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Hello Armed Force Admin");
            stage.setResizable(false);
            AFAWindowController sceneController = loader.getController();
            sceneController.setController(controller);
            controller.setLoggedUser(controller.getPoliceAdmin());
            stage.showAndWait();

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void hendelLogInAsAFU(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("AFUWindow.fxml").openStream());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Hello Armed Force User");
            stage.setResizable(false);
            AFUWindowController sceneController = loader.getController();
            controller.setLoggedUser(new RegularUser("hila","12345","Police",7,"Active","h@h.com"));
            sceneController.setController(controller);
            stage.showAndWait();

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }

    public void hendelLogInAsCA(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(this.getClass().getClassLoader().getResource("CAWindow.fxml").openStream());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Hello Center Admin");
            stage.setResizable(false);
            CAWindowController sceneController = loader.getController();
            sceneController.setController(controller);
            controller.setLoggedUser(controller.getCenterAdmin());
            stage.showAndWait();

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }
}
