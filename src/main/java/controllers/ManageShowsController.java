package controllers;

import domain.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static controllers.MessageAlert.showMessage;

public class ManageShowsController {

    Service service;
    Stage stage;
    Stage previousStage;

    @FXML
    private TableView<Show> table;
    @FXML
    private TableColumn<Show, String> nameColumn;
    @FXML
    private TableColumn<Show, String> dateColumn;
    @FXML
    private TextField name;
    @FXML
    private DatePicker date;

    ObservableList<Show> showsTableModel = FXCollections.observableArrayList();


    public void setService(Service service, Stage stage, Stage newStage) {
        this.service = service;
        this.stage = stage;
        this.previousStage = newStage;
        initShowsTableModel();

    }

    @FXML
    public void initialize() {
        initializeShowsTable();
    }



    private void initializeShowsTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        table.setItems(showsTableModel);
    }

    private void initShowsTableModel() {
        List<Show> shows = (List<Show>) service.getAllShows();
        showsTableModel.setAll(shows);
    }

    @FXML
    public void deleteShow(){
        Show selectedShow = table.getSelectionModel().getSelectedItem();
        if (selectedShow != null) {
            Show found = service.getShow(selectedShow.getId());
            service.deleteShow(found.getId());
        }
        else {
            showMessage(
                    stage, Alert.AlertType.INFORMATION, "eroare1", "err"
            );
        }
        initShowsTableModel();
    }

    @FXML
    public void addShow() {
        String name1 = name.getText();
        String date1=date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyy"));
        service.addShow(name1, date1);
        initShowsTableModel();

    }
}
