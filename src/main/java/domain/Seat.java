package domain;

public class Seat {
    private Integer number;
    private Integer row;
    private Integer lodge;
    private Integer price;
    private String state;

    public Seat(Integer number,Integer row, Integer lodge, Integer price, String state) {
        this.number = number;
        this.row = row;
        this.lodge = lodge;
        this.price = price;
        this.state = state;
    }

    public Seat(){}

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getLodge() {
        return lodge;
    }

    public void setLodge(Integer lodge) {
        this.lodge = lodge;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
