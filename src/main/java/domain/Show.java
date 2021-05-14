package domain;

public class Show {
    private Integer id;
    private String name;
    private String date;

    public Show(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Show(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
