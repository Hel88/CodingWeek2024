package eu.telecomnancy.codingweek.utils;

import eu.telecomnancy.codingweek.global.User;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DataUserTest {

    @BeforeEach
    @AfterEach
    public void cleanUp() {
        try {
            JSONObject json = new JSONObject();
            String fileUserPath = DataUsersUtils.getInstance().getFilePath();
            String fileAnnoncePath = DataAnnoncesUtils.getInstance().getFilePath();
            String CalendarPath = DataCalendarUtils.getInstance().getFilePath();
            String fileConversationPath = DataConversationsUtils.getInstance().getFilePath();
            String fileEntriesPath = DataEntryUtils.getInstance().getFilePath();
            String fileMessagePath = DataMessagesUtils.getInstance().getFilePath();
            String fileNotePath = DataNoteUtils.getInstance().getFilePath();
            String fileReportPath = DataReportUtils.getInstance().getFilePath();
            String fileTransactionPath = DataTransactionUtils.getInstance().getFilePath();
            try (FileWriter file = new FileWriter(fileUserPath)) {
                file.write(json.toString());
                file.flush();
            }
            try (FileWriter file = new FileWriter(fileAnnoncePath)) {
                file.write(json.toString());
                file.flush();
            }
            try (FileWriter file = new FileWriter(CalendarPath)) {
                file.write(json.toString());
                file.flush();
            }
            try (FileWriter file = new FileWriter(fileConversationPath)) {
                file.write(json.toString());
                file.flush();
            }
            try (FileWriter file = new FileWriter(fileEntriesPath)) {
                file.write(json.toString());
                file.flush();
            }
            try (FileWriter file = new FileWriter(fileMessagePath)) {
                file.write(json.toString());
                file.flush();
            }
            try (FileWriter file = new FileWriter(fileNotePath)) {
                file.write(json.toString());
                file.flush();
            }
            try (FileWriter file = new FileWriter(fileReportPath)) {
                file.write(json.toString());
                file.flush();
            }
            try (FileWriter file = new FileWriter(fileTransactionPath)) {
                file.write(json.toString());
                file.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddUser() {
        // Ajout d'un utilisateur, vérification du nombre d'utilisateurs
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();

            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            assertEquals(1, dataUsersUtils.getData().length(), "Le nombre d'utilisateurs n'est pas correct.");
            assertTrue(dataUsersUtils.getData().has("testuser"), "L'utilisateur testuser n'a pas été ajouté.");

            JSONObject testUser = dataUsersUtils.getData().getJSONObject("testuser");
            assertEquals(DataUsersUtils.hashPassword(""), testUser.getString("password"), "Le mot de passe n'est pas correct.");
            assertEquals("null", testUser.getString("email"), "L'email n'est pas correct.");
            assertEquals("null", testUser.getString("lastName"), "Le nom n'est pas correct.");
            assertEquals("null", testUser.getString("firstName"), "Le prénom n'est pas correct.");
            assertEquals("null", testUser.getString("address"), "L'adresse n'est pas correcte.");
            assertEquals("null", testUser.getString("city"), "La ville n'est pas correcte.");
            assertEquals("", testUser.getString("transactionsReferent"), "La liste des transactions référentes n'est pas correcte.");
            assertEquals("", testUser.getString("transactionsClient"), "La liste des transactions clientes n'est pas correcte.");
            assertEquals("", testUser.getString("annonces"), "La liste des annonces n'est pas correcte.");
            assertEquals("", testUser.getString("eval"), "La liste des évaluations n'est pas correcte.");
            assertEquals(100, testUser.getInt("solde"), "Le solde n'est pas correct.");
            assertFalse(testUser.getBoolean("isAdmin"), "Le statut d'administrateur n'est pas correct.");
            assertEquals("", testUser.getString("idConversations"), "La liste des conversations n'est pas correcte.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testIsUsernameUnique() {
        // Vérification de l'unicité d'un nom d'utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            assertFalse(dataUsersUtils.isUserNameUnique("testuser"), "Le nom d'utilisateur testuser est déjà utilisé.");
            assertTrue(dataUsersUtils.isUserNameUnique("testuser2"), "Le nom d'utilisateur testuser2 n'est pas déjà utilisé.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDoesUserExist() {
        // Vérification de l'existence d'un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            assertTrue(dataUsersUtils.doesUserExist("testuser"), "L'utilisateur testuser n'existe pas.");
            assertFalse(dataUsersUtils.doesUserExist("testuser2"), "L'utilisateur testuser2 existe.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetUserByUserName() {
        // Récupération d'un utilisateur par son nom d'utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            User testUser = dataUsersUtils.getUserByUserName("testuser");
            assertEquals("testuser", testUser.getUserName(), "Le nom d'utilisateur n'est pas correct.");
            assertEquals(DataUsersUtils.hashPassword(""), testUser.getPassword(), "Le mot de passe n'est pas correct.");
            assertEquals("null", testUser.getEmail(), "L'email n'est pas correct.");
            assertEquals("null", testUser.getLastName(), "Le nom n'est pas correct.");
            assertEquals("null", testUser.getFirstName(), "Le prénom n'est pas correct.");
            assertEquals("null", testUser.getAddress(), "L'adresse n'est pas correcte.");
            assertEquals("null", testUser.getCity(), "La ville n'est pas correcte.");
            assertEquals("", testUser.getTransactionsReferent(), "La liste des transactions référentes n'est pas correcte.");
            assertEquals("", testUser.getTransactionsClient(), "La liste des transactions clientes n'est pas correcte.");
            assertEquals("", testUser.getAnnonces(), "La liste des annonces n'est pas correcte.");
            assertEquals("", testUser.getEval(), "La liste des évaluations n'est pas correcte.");
            assertEquals(100, testUser.getSolde(), "Le solde n'est pas correct.");
            assertFalse(testUser.isAdmin(), "Le statut d'administrateur n'est pas correct.");
            assertEquals("", testUser.getIdConversations(), "La liste des conversations n'est pas correcte.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCheckPassword() {
        // Vérification du mot de passe d'un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "testpassword", "null", "null", "null", "null", "null");

            assertTrue(dataUsersUtils.checkPassword("testuser", "testpassword"), "Le mot de passe testpassword n'est pas correct.");
            assertFalse(dataUsersUtils.checkPassword("testuser", "testpassword2"), "Le mot de passe testpassword2 est correct.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpdateUser() {
        // Modification d'un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            User testUser = dataUsersUtils.getUserByUserName("testuser");
            testUser.setPassword(DataUsersUtils.hashPassword("testpassword"));
            testUser.setEmail("testemail");
            testUser.setLastName("testlastname");
            testUser.setFirstName("testfirstname");
            testUser.setAddress("testaddress");
            testUser.setCity("testcity");
            testUser.setTransactionsReferent("testtransactionsreferent");
            testUser.setTransactionsClient("testtransactionsclient");
            testUser.setAnnonces("testannonces");
            testUser.setEval("testeval");
            testUser.setSolde(50);
            testUser.setIsAdmin(true);
            testUser.setIdConversations("testidconversations");
            dataUsersUtils.updateUser(testUser);

            JSONObject testUserJSON = dataUsersUtils.getData().getJSONObject("testuser");
            assertEquals(DataUsersUtils.hashPassword("testpassword"), testUserJSON.getString("password"), "Le mot de passe n'est pas correct.");
            assertEquals("testemail", testUserJSON.getString("email"), "L'email n'est pas correct.");
            assertEquals("testlastname", testUserJSON.getString("lastName"), "Le nom n'est pas correct.");
            assertEquals("testfirstname", testUserJSON.getString("firstName"), "Le prénom n'est pas correct.");
            assertEquals("testaddress", testUserJSON.getString("address"), "L'adresse n'est pas correcte.");
            assertEquals("testcity", testUserJSON.getString("city"), "La ville n'est pas correcte.");
            assertEquals("testtransactionsreferent", testUserJSON.getString("transactionsReferent"), "La liste des transactions référentes n'est pas correcte.");
            assertEquals("testtransactionsclient", testUserJSON.getString("transactionsClient"), "La liste des transactions clientes n'est pas correcte.");
            assertEquals("testannonces", testUserJSON.getString("annonces"), "La liste des annonces n'est pas correcte.");
            assertEquals("testeval", testUserJSON.getString("eval"), "La liste des évaluations n'est pas correcte.");
            assertEquals(50, testUserJSON.getInt("solde"), "Le solde n'est pas correct.");
            assertTrue(testUserJSON.getBoolean("isAdmin"), "Le statut d'administrateur n'est pas correct.");
            assertEquals("testidconversations", testUserJSON.getString("idConversations"), "La liste des conversations n'est pas correcte.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddIdConversationToUser() {
        // Ajout d'une conversation à un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            User testUser = dataUsersUtils.getUserByUserName("testuser");
            dataUsersUtils.addIdConversationToUser("testuser", 1);

            JSONObject testUserJSON = dataUsersUtils.getData().getJSONObject("testuser");
            assertEquals("1", testUserJSON.getString("idConversations"), "La liste des conversations n'est pas correcte.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHashPassword() {
        // Vérification du hashage d'un mot de passe
        assertEquals("5f4dcc3b5aa765d61d8327deb882cf99", DataUsersUtils.hashPassword("password"), "Le hashage du mot de passe password n'est pas correct.");
    }

    @Test
    public void testDeleteUser() {
        // Suppression d'un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            int nbUsersBefore = dataUsersUtils.getData().length();
            dataUsersUtils.deleteUser("testuser");

            assertEquals(nbUsersBefore - 1, dataUsersUtils.getData().length(), "Le nombre d'utilisateurs n'est pas correct.");
            assertFalse(dataUsersUtils.getData().has("testuser"), "L'utilisateur testuser n'a pas été supprimé.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetCalendarOf() {
        // Récupération du calendrier d'un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            User testUser = dataUsersUtils.getUserByUserName("testuser");
            int idCalendar = testUser.getPlanning();

            assertEquals(idCalendar, dataUsersUtils.getCalendarOf(testUser.getUserName()), "Le calendrier n'est pas correct.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddAnnonceToUser() {
        // Ajout d'une annonce à un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            User testUser = dataUsersUtils.getUserByUserName("testuser");
            dataUsersUtils.addAnnonceToUser("testuser", 1);

            JSONObject testUserJSON = dataUsersUtils.getData().getJSONObject("testuser");
            assertEquals("1", testUserJSON.getString("annonces"), "La liste des annonces n'est pas correcte.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddTransactionReferentToUser() {
        // Ajout d'une transaction référente à un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            User testUser = dataUsersUtils.getUserByUserName("testuser");
            dataUsersUtils.addTransactionReferentToUser("testuser", 1);

            JSONObject testUserJSON = dataUsersUtils.getData().getJSONObject("testuser");
            assertEquals("1", testUserJSON.getString("transactionsReferent"), "La liste des transactions référentes n'est pas correcte.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddTransactionClientToUser() {
        // Ajout d'une transaction cliente à un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            User testUser = dataUsersUtils.getUserByUserName("testuser");
            dataUsersUtils.addTransactionClientToUser("testuser", 1);

            JSONObject testUserJSON = dataUsersUtils.getData().getJSONObject("testuser");
            assertEquals("1", testUserJSON.getString("transactionsClient"), "La liste des transactions clientes n'est pas correcte.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddConversationToUser() {
        // Ajout d'une conversation à un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            User testUser = dataUsersUtils.getUserByUserName("testuser");
            dataUsersUtils.addConversationToUser("testuser", 1);

            JSONObject testUserJSON = dataUsersUtils.getData().getJSONObject("testuser");
            assertEquals("1", testUserJSON.getString("idConversations"), "La liste des conversations n'est pas correcte.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testAddEvalToUser() {
        // Ajout d'une évaluation à un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");

            User testUser = dataUsersUtils.getUserByUserName("testuser");
            dataUsersUtils.addEvalToUser("testuser", Integer.parseInt("1"));

            JSONObject testUserJSON = dataUsersUtils.getData().getJSONObject("testuser");
            assertEquals("1", testUserJSON.getString("eval"), "La liste des évaluations n'est pas correcte.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeleteAnnonceFromUser() {
        // Suppression d'une annonce d'un utilisateur
        try {
            DataUsersUtils dataUsersUtils = DataUsersUtils.getInstance();
            dataUsersUtils.addUser("testuser", "", "null", "null", "null", "null", "null");
            dataUsersUtils.addAnnonceToUser("testuser", 1);

            User testUser = dataUsersUtils.getUserByUserName("testuser");
            dataUsersUtils.deleteAnnonceFromUser("testuser", "1");

            JSONObject testUserJSON = dataUsersUtils.getData().getJSONObject("testuser");
            assertEquals("", testUserJSON.getString("annonces"), "La liste des annonces n'est pas correcte.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
