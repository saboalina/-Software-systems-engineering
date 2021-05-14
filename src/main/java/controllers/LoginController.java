package controllers;

import domain.Manager;
import domain.Spectator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class LoginController {

    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtParola;

    Service service;
    Stage stage;

    public void setService(Service service, Stage stage) {
        this.service = service;
        this.stage = stage;
    }

    public void loadMainStage(String file, String username, String password) throws IOException {

        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(file));
        AnchorPane layout = loader.load();
        newStage.setScene(new Scene(layout));
//        newStage.setTitle("MeetLy");
        newStage.show();

        if (file.equals("/views/MainSpectatorPage.fxml")){
            Spectator spectator = service.getSpectator(username, password);
            MainSpectatorController mainSpectatorController = loader.getController();
            mainSpectatorController.setService(service, stage, newStage, spectator);
        }
        else if (file.equals("/views/MainManagerPage.fxml")){
            Manager manager = service.getManager(username, password);
            MainManagerController mainManagerController = loader.getController();
            mainManagerController.setService(service, stage, newStage, manager);
        }


    }

    @FXML
    public void signIn() throws IOException {
        if (txtUsername.getText().length()==0 || txtParola.getText().length()==0){
            lblStatus.setText("Sign in failed");
            lblStatus.setTextFill(Color.web("#ba170b"));
        }
        try{
            if (service.getSpectator(txtUsername.getText(), txtParola.getText())!=null) {
                loadMainStage("/views/MainSpectatorPage.fxml", txtUsername.getText(), txtParola.getText());
                stage.close();
            }
        }
        catch (Exception e){
            lblStatus.setText("Sign in failed");
            lblStatus.setTextFill(Color.web("#ba170b"));
        }

        try{
            if (service.getManager(txtUsername.getText(), txtParola.getText())!=null) {
                loadMainStage("/views/MainManagerPage.fxml", txtUsername.getText(), txtParola.getText());
                stage.close();
            }
        }
        catch (Exception e){
            lblStatus.setText("Sign in failed");
            lblStatus.setTextFill(Color.web("#ba170b"));
        }
    }
}
