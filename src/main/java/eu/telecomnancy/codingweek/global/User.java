package eu.telecomnancy.codingweek.global;

import eu.telecomnancy.codingweek.utils.DataNoteUtils;

import java.io.IOException;
import java.util.ArrayList;

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
    private String transactionsReferent;
    private String transactionsClient;
    private int planning;
    private String eval;
    private int solde;
    private boolean isAdmin;
    private String idConversations;

    // Default constructor (needed for JSON deserialization)
    public User() {
    }

    // Constructor with parameters
    public User(String userName, String password, String firstName, String lastName, String email,
                String address, String city, String annonces, String transactionsReferent, String transactionsClient, int planning, String eval, int solde, boolean isAdmin, String idConversations) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.annonces = annonces;
        this.transactionsReferent = transactionsReferent;
        this.transactionsClient = transactionsClient;
        this.planning = planning;
        this.eval = eval;
        this.solde = solde;
        this.isAdmin = isAdmin;
        this.idConversations = idConversations;
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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public String getAnnonces() {
        return annonces;
    }

    public String getTransactionsReferent() {
        return transactionsReferent;
    }

    public String getTransactionsClient() {
        return transactionsClient;
    }

    public int getPlanning() {
        return planning;
    }

    public String getEval() {
        return eval;
    }

    public int getMoyenne() throws IOException {
        int moyenne = 0;
        int nb = 0;
        ArrayList<Note> notes = DataNoteUtils.getInstance().getNotesByUser(this);
        for (Note note : notes) {
            moyenne += note.getNote();
            nb++;
        }
        if (nb == 0) {
            return 0;
        }
        return moyenne / nb;
    }

    public int getSolde() {
        return solde;
    }

    public String getIdConversations() {
        return idConversations;
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

    public void setTransactionsReferent(String transactionsReferent) {
        this.transactionsReferent = transactionsReferent;
    }

    public void setTransactionsClient(String transactionsClient) {
        this.transactionsClient = transactionsClient;
    }

    public void setPlanning(int planning) {
        this.planning = planning;
    }

    public void setEval(String eval) {
        this.eval = eval;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setIdConversations(String idConversations) {
        this.idConversations = idConversations;
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
                ", transactionsReferent=" + transactionsReferent +
                ", transactionsClient=" + transactionsClient +
                ", planning=" + planning +
                ", eval=" + eval +
                ", solde=" + solde +
                ", isAdmin=" + isAdmin +
                ", idConversations=" + idConversations +
                '}';
    }
}
