package eu.telecomnancy.codingweek.utils;

public class User {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private int announces;
    private int planning;
    private int eval;

    // Default constructor (needed for JSON deserialization)
    public User() {
    }

    // Constructor with parameters
    public User(String userName, String password, String firstName, String lastName, String email,
                String address, String city, int announces, int planning, int eval) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.announces = announces;
        this.planning = planning;
        this.eval = eval;
    }

    // Getter methods

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getAnnounces() {
        return announces;
    }

    public int getPlanning() {
        return planning;
    }

    public int getEval() {
        return eval;
    }
}
