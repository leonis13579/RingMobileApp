package ru.realityfamily.ringapp.Models;

public class AuthData {

    private static AuthData INSTANCE = new AuthData();

    public static AuthData getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new AuthData();
        }

        return INSTANCE;
    }

    public enum PersonType {
        Client,
        Driver
    }

    String login = "";
    String password = "";
    PersonType type;
    String token = "";


    private AuthData() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        this.password = "";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
