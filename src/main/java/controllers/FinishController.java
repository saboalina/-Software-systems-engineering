package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.Random;

import static controllers.MessageAlert.showMessage;

public class FinishController {

    Service service;
    Stage stage;
    Stage previousStage;
    String name;
    String surname;
    String hour;
    String seats;
    Integer showId;
    Integer price;
    Integer cardNumber;
    String showName;

    @FXML
    private Label showlbl;
    @FXML
    private Label hourlbl;
    @FXML
    private Label clientlbl;
    @FXML
    private Label seatslbl;
    @FXML
    private Label totallbl;

    public void setService(Service service, Stage stage, Stage previousStage, String name, String surname, String hour,
                           String seats, Integer showId, Integer price, Integer cardNumber, String showName) {
        this.service = service;
        this.stage = stage;
        this.previousStage = previousStage;
        this.name = name;
        this.surname = surname;
        this.hour = hour;
        this.seats = seats;
        this.showId = showId;
        this.price = price;
        this.cardNumber = cardNumber;
        this.showName=showName;

        loadText();
    }


    public void loadText(){
        showlbl.setText(showName);
        hourlbl.setText(hour);
        clientlbl.setText(name+" "+surname);
        seatslbl.setText(seats);
        String[] all_seats=seats.split(" ");
        Integer noSeats= all_seats.length;
        Integer total = noSeats*price;
        totallbl.setText(total+"");
    }
    @FXML
    public void addReservation() {
        String[] all_seats=seats.split(" ");
        Integer noSeats= all_seats.length;
        Integer total = noSeats*price;
        service.addReservation(name, surname, hour,seats, showId, total, cardNumber);
        showMessage( stage, Alert.AlertType.INFORMATION, "Message", "Your reservation is complete!" );
    }

    @FXML
    public void cancelButton() {

        previousStage.close();
    }
}

