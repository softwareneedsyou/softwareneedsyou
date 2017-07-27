package fr.esgi.projet.softwareneedsyou.models;

public class UserModel {
    private int id;
    private String lastname;
    private String firstname;
    private String username;
    private String email;
    private String token;

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

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
}