package Views;

import Objects.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class View {

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
            stage.showAndWait();

        } catch (IOException e) {
            e.getCause();
            e.printStackTrace();
        }
    }
}
