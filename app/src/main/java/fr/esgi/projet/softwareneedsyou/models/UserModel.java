package fr.esgi.projet.softwareneedsyou.models;

public class UserModel {
    private int id;
    private String lastname;
    private String firstname;
    private String username;
    private String email;
    private String token;

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public void setToken(String tkn) {
        this.token = tkn;
    }
}