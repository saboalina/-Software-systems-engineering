package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.Service;
import java.util.Random;

import java.io.IOException;

public class ReservationController {

    Service service;
    Stage stage;
    Stage previousStage;
    Integer price;
    String showName;

    @FXML
    private ChoiceBox hours;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField seats;
    @FXML
    private TextField cardNumber;

    public void setService(Service service, Stage stage, Stage previousStage, Integer price,  String showName) {
        this.service = service;
        this.stage = stage;
        this.previousStage = previousStage;
        this.price=price;
        this.showName= showName;

        hours.setItems(FXCollections.observableArrayList( "12:00", "15:00", "18:00"));
    }

    @FXML
    public void addReservation() throws IOException {
        String name1 = name.getText();
        String surname1= surname.getText();
        String hour1 = hours.getValue().toString();
        String seats1=seats.getText();
        Integer showId=service.getShowByName(showName).getId();
        Integer cardNumber1= Integer.valueOf(cardNumber.getText());
        loadStage(name1, surname1, hour1,seats1, showId, price, cardNumber1);
    }

    public void loadStage(String name, String surname, String hour, String seats, Integer showId, Integer price , Integer cardNumber) throws IOException {

        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/FinishReservation.fxml"));
        AnchorPane layout = loader.load();
        newStage.setScene(new Scene(layout));
        newStage.show();

        FinishController finishController = loader.getController();
        finishController.setService(service, stage, newStage, name, surname, hour, seats, showId, price, cardNumber, showName);

    }
}
