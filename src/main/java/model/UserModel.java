package model;

public class UserModel {
    private String login;

    public UserModel(){

    }

    public UserModel(String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
