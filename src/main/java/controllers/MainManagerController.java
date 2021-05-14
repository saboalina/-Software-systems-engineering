package controllers;

import domain.Manager;
import domain.Spectator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.Random;

public class MainManagerController {

    Service service;
    Stage stage;
    Stage previousStage;
    Manager manager;

    @FXML
    private TextField name;
    @FXML
    private TextField show;

    public void setService(Service service, Stage stage, Stage previousStage, Manager manager) {

        this.service = service;
        this.stage = stage;
        this.previousStage = previousStage;
        this.manager=manager;

        name.setText(manager.getName());
        Random rand = new Random();
        Integer no = rand.nextInt(10);
        show.setText(service.getShow(no+1).getName());
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
    public void loadShowPage() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/ManageShowsPage.fxml"));
        AnchorPane layout = loader.load();
        newStage.setScene(new Scene(layout));
        newStage.show();

        ManageShowsController manageShowsController = loader.getController();
        manageShowsController.setService(service, stage, newStage);
    }

    @FXML
    public void loadReservationPage() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/ManageReservationsPage.fxml"));
        AnchorPane layout = loader.load();
        newStage.setScene(new Scene(layout));
        newStage.show();

        ManageReservationsController manageReservationsController = loader.getController();
        manageReservationsController.setService(service, stage, newStage);


    }
}
