package controllers;

import domain.Manager;
import domain.Spectator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.Random;

public class MainSpectatorController {

    Service service;
    Stage stage;
    Stage previousStage;
    Spectator spectator;
    String showName;

    @FXML
    private TextField name;
    @FXML
    private TextField show;


    public void setService(Service service, Stage stage, Stage previousStage, Spectator spectator) {

        this.service = service;
        this.stage = stage;
        this.previousStage = previousStage;
        this.spectator=spectator;

        name.setText(spectator.getName());
        Random rand = new Random();
        Integer no = rand.nextInt(10);
        this.showName = service.getShow(no+1).getName();
        show.setText(showName);
    }

    @FXML
    public void buyBack() throws IOException {
        loadStage(70);
    }

    @FXML
    public void buyMiddle() throws IOException {
        loadStage(80);
    }

    @FXML
    public void buyFront() throws IOException {
        loadStage(100);
    }

    public void loadStage(Integer price) throws IOException {

        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/ReservationPage.fxml"));
        AnchorPane layout = loader.load();
        newStage.setScene(new Scene(layout));
        newStage.show();

        ReservationController reservationController = loader.getController();
        reservationController.setService(service, stage, newStage, price, showName);

    }
}
