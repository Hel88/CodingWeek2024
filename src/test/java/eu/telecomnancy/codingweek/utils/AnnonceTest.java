package eu.telecomnancy.codingweek.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class AnnonceTest {

    @Test
    public void testAnnonce() {
        Annonce annonce = new Annonce(1, "titre", "categorie", "description", 10, "referent", true);
        assertTrue(annonce instanceof Annonce);
        assertEquals(1, annonce.getId());
        assertEquals("titre", annonce.getTitre());
        assertEquals("categorie", annonce.getCategorie());
        assertEquals("description", annonce.getDescription());
        assertEquals(10, annonce.getPrix());
        assertEquals("referent", annonce.getReferent());
        assertTrue(annonce.getActif());
    }

    @Test
    public void dataAnnoncesUtilsTestUnicite() {
        DataAnnoncesUtils dataAnnoncesUtils;
        try {
            dataAnnoncesUtils = DataAnnoncesUtils.getInstance();
            DataAnnoncesUtils dataAnnoncesUtils2 = DataAnnoncesUtils.getInstance();
            assertEquals(dataAnnoncesUtils, dataAnnoncesUtils2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddDeleteAnnonce(){
        //ajout et suppression d'une annonce, vérification du nombre d'annonces
        try {
            DataAnnoncesUtils dataAnnoncesUtils = DataAnnoncesUtils.getInstance();
            int nbAnnonces = dataAnnoncesUtils.getAnnonces().size();
            
            //ajout d'une annonce
            dataAnnoncesUtils.addAnnonce("Annonce test junit", "---", "10", "---", "testUser");
            assertEquals(nbAnnonces + 1, dataAnnoncesUtils.getAnnonces().size());

            ArrayList<Annonce> annonces = dataAnnoncesUtils.getAnnoncesByUsername("testUser");
            assertEquals(1,annonces.size());

            //suppression de l'annonce
            dataAnnoncesUtils.deleteAnnonce(annonces.get(0).getId()+"");
            assertEquals(nbAnnonces, dataAnnoncesUtils.getAnnonces().size());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAnnoncesBUsername(){
        //ajout (et suppression) d'annonces de users différents, vérification du nombre d'annonces
        try {
            DataAnnoncesUtils dataAnnoncesUtils = DataAnnoncesUtils.getInstance();
            int nbAnnonces = dataAnnoncesUtils.getAnnonces().size();

            //ajout d'une annonce pour testUser1
            dataAnnoncesUtils.addAnnonce("Annonce test junit", "---", "10", "---", "testUser1");
            assertEquals(nbAnnonces + 1, dataAnnoncesUtils.getAnnonces().size());
            ArrayList<Annonce> annoncesTestUser1 = dataAnnoncesUtils.getAnnoncesByUsername("testUser1");
            assertEquals(1,annoncesTestUser1.size());

            //ajout d'une autre annonce pour testUser1
            dataAnnoncesUtils.addAnnonce("Annonce test junit", "---", "10", "---", "testUser1");
            assertEquals(nbAnnonces + 2, dataAnnoncesUtils.getAnnonces().size());
            annoncesTestUser1 = dataAnnoncesUtils.getAnnoncesByUsername("testUser1");
            assertEquals(2,annoncesTestUser1.size());


            //suppression des annonces créées
            dataAnnoncesUtils.deleteAnnonce(annoncesTestUser1.get(0).getId()+"");
            assertEquals(nbAnnonces, dataAnnoncesUtils.getAnnonces().size());
        } catch (Exception e) {
        }

    }

    @Test
    public void modifyAnnonce(){
        try{

            DataAnnoncesUtils dataAnnoncesUtils = DataAnnoncesUtils.getInstance();
            int nbAnnonces = dataAnnoncesUtils.getAnnonces().size();
            //ajout d'une annonce
            dataAnnoncesUtils.addAnnonce("Annonce test junit", "---", "10", "---", "testUser");
            assertEquals(nbAnnonces + 1, dataAnnoncesUtils.getAnnonces().size());

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Test
    public void testGetAnnonces(){

    }




}
