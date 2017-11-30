package model;

public class Game {

    private int id;
    private String name;
    private String url;
    private Float total_rating;
    private Cover cover;


    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Float getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(Float total_rating) {
        this.total_rating = total_rating;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
