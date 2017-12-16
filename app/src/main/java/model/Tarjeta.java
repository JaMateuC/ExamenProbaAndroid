package model;

/**
 * Created by root on 16/12/17.
 */

public class Tarjeta {

    private String login;
    private String image;

    public Tarjeta(String title, String image) {
        this.login = title;
        this.image = image;
    }

    public String getLogin() {
        return login;
    }

    public String getImage() {
        return image;
    }

}
