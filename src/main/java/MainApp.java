import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.*;
import service.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.properties"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        SpectatorRepository spectatorRepository=new HBMRepoSpectator(props);
        ManagerRepository managerRepository = new HBMRepoManager(props);
        ReservationRepository reservationRepository = new HBMRepoReservation(props);
        ShowRepository showRepository = new HBMRepoShow(props);
        SeatRepository seatRepository = new HBMRepoSeat(props);

        Service service =new Service(spectatorRepository, managerRepository, reservationRepository, showRepository, seatRepository);

        initView(primaryStage, service);
        primaryStage.show();
    }


    private void initView(Stage primaryStage, Service service) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/LogInPage.fxml"));
        AnchorPane layout = loader.load();
        primaryStage.setScene(new Scene(layout));
        LoginController loginController = loader.getController();
        loginController.setService(service, primaryStage);
    }
}
