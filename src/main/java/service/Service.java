package service;

import domain.Manager;
import domain.Reservation;
import domain.Show;
import domain.Spectator;
import repository.*;

public class Service {
     private SpectatorRepository spectatorRepository;
     private ManagerRepository managerRepository;
     private ReservationRepository reservationRepository;
     private ShowRepository showRepository;
     private SeatRepository seatRepository;

    public Service(SpectatorRepository spectatorRepository, ManagerRepository managerRepository,
                   ReservationRepository reservationRepository, ShowRepository showRepository,
                   SeatRepository seatRepository) {
        this.spectatorRepository = spectatorRepository;
        this.managerRepository = managerRepository;
        this.reservationRepository = reservationRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
    }

    public Spectator getSpectator(String username, String password){
        Spectator spectator = spectatorRepository.findOne(username);
        if (spectator.getPassword().equals(password))
            return spectator;
        else
            return null;
    }

    public Manager getManager(String username, String password) {
        Manager manager = managerRepository.findOne(username);
        if (manager.getPassword().equals(password))
            return manager;
        else
            return null;
    }

    public void addReservation(String name, String surname, String hour, String seats, Integer showId, Integer price, Integer cardNumber){
        String clientName=name+" "+surname;
        Reservation reservation = new Reservation(clientName, hour, seats, showId, price, cardNumber);

        Iterable<Reservation> all = reservationRepository.findAll();
        int counter = 0;
        for (Object i : all) {
            counter++;
        }
        reservation.setId(counter+1);
        reservationRepository.save(reservation);
    }

    public Show getShow(Integer id){
        return showRepository.findOne(id);
    }

    public Object getAllShows() {
        return showRepository.findAll();
    }

    public Object getAllReservations() {
        return reservationRepository.findAll();
    }

    public Show getShowByName(String name){
        Iterable<Show> all_shows= showRepository.findAll();
        for (Show i : all_shows) {
            if(i.getName().equals(name))
                return i;
        }
        return null;
    }

    public void deleteShow(Integer id) {
        showRepository.delete(id);
    }

    public void addShow(String name1, String date1) {
        Show show = new Show(name1, date1);

        Iterable<Show> all = showRepository.findAll();
        int counter = 0;
        for (Object i : all) {
            counter++;
        }
        show.setId(counter+1);
        showRepository.save(show);
    }

    public Reservation getReservation(Integer id) {
        return reservationRepository.findOne(id);
    }

    public void deleteReservation(Integer id) {
        reservationRepository.delete(id);
    }
}
