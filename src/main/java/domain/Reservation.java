package domain;

public class Reservation {
    private Integer id;
    private String clientName;
    private String hour;
    private String seats;
    private Integer showId;
    private Integer price;
    private Integer cardNumber;

    public Reservation(String clientName, String hour, String seats, Integer showId, Integer price, Integer cardNumber) {
        this.clientName = clientName;
        this.hour = hour;
        this.seats = seats;
        this.showId = showId;
        this.price=price;
        this.cardNumber = cardNumber;
    }

    public Reservation(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public Integer getShowId() {
        return showId;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }
}
