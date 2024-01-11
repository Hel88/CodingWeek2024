package eu.telecomnancy.codingweek.utils;

public class User {

    // Fields
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String annonces;
    private int planning;
    private int eval;

    // Default constructor (needed for JSON deserialization)
    public User() {
    }

    // Constructor with parameters
    public User(String userName, String password, String firstName, String lastName, String email,
                String address, String city, String annonces, int planning, int eval) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.annonces = annonces;
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

    public String getAnnonces() {
        System.out.println(annonces);
        return annonces;
    }

    public int getPlanning() {
        return planning;
    }

    public int getEval() {
        return eval;
    }


    // Setter methods
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAnnonces(String annonces) {
        this.annonces = annonces;
    }

    public void setPlanning(int planning) {
        this.planning = planning;
    }

    public void setEval(int eval) {
        this.eval = eval;
    }

    // toString method for better representation
    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", announces=" + annonces +
                ", planning=" + planning +
                ", eval=" + eval +
                '}';
    }
}
