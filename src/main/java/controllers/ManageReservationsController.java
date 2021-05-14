package controllers;

import domain.Reservation;
import domain.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.Service;

import java.util.List;

import static controllers.MessageAlert.showMessage;

public class ManageReservationsController {

    Service service;
    Stage stage;
    Stage previousStage;

    @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation, String> show;
    @FXML
    private TableColumn<Reservation, String> client;
    @FXML
    private TableColumn<Reservation, String> seats;

    ObservableList<Reservation> reservationsTableModel = FXCollections.observableArrayList();

    public void setService(Service service, Stage stage, Stage newStage) {
        this.service = service;
        this.stage = stage;
        this.previousStage = newStage;
        initReservationsTableModel();

    }

    @FXML
    public void initialize() {
        initializeReservationsTable();
    }

    private void initializeReservationsTable() {
        show.setCellValueFactory(new PropertyValueFactory<>("ShowId"));
        client.setCellValueFactory(new PropertyValueFactory<>("ClientName"));
        seats.setCellValueFactory(new PropertyValueFactory<>("Seats"));
        table.setItems(reservationsTableModel);
    }

    private void initReservationsTableModel() {
        List<Reservation> shows = (List<Reservation>) service.getAllReservations();
        reservationsTableModel.setAll(shows);
    }

    @FXML
    public void deleteReservation(){
        Reservation selectedReservation = table.getSelectionModel().getSelectedItem();
        if (selectedReservation != null) {
            //Reservation found = service.getReservation(selectedReservation.getId());
            service.deleteReservation(selectedReservation.getId());
        }
        else {
            showMessage(
                    stage, Alert.AlertType.INFORMATION, "eroare1", "err"
            );
        }
        initReservationsTableModel();
    }
}
