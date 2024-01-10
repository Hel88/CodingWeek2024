package eu.telecomnancy.codingweek.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Set;

import org.json.JSONObject;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.utils.Annonce;
import eu.telecomnancy.codingweek.utils.FileAccess;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class MesAnnoncesController implements Observer{
    
    @FXML
    private VBox VBoxAnnonces;

    private Application app;

    private ArrayList<Annonce> annonces;

    public MesAnnoncesController(Application app) {
        this.app = app;
        annonces = new ArrayList<Annonce>();
        app.addObserver(this);
    }

    public void detailsAnnonce(Annonce annonce){
        app.setAnnonceAffichee(annonce);
        app.notifyObservers("annonce");
        app.getSceneController().switchToMonAnnonce();
    }

    // public void creerAnnonce(){
    //     app.getSceneController().switchToCreationAnnonce();
    // }
  
    public void creerAnnonceDemandeService(){
        app.setCategorieAnnonceACreer("DemandeService");
        app.notifyObservers("annonce");
        app.getSceneController().switchToCreationAnnonce();
    }

    public void creerAnnonceDemandeMateriel(){
        app.setCategorieAnnonceACreer("DemandeMateriel");
        app.notifyObservers("annonce");
        app.getSceneController().switchToCreationAnnonce();
    }

    public void creerAnnonceOffreService(){
        app.setCategorieAnnonceACreer("OffreService");
        app.notifyObservers("annonce");
        app.getSceneController().switchToCreationAnnonce();
    }

    public void creerAnnonceOffreMateriel(){
        app.setCategorieAnnonceACreer("OffreMateriel");
        app.notifyObservers("annonce");
        app.getSceneController().switchToCreationAnnonce();
    }


    @FXML
    public void initialize() throws IOException {
        //gère l'affichage
        annonces = new ArrayList<Annonce>();

        VBoxAnnonces.getChildren().clear();
        
        synchroJson(); //synchronise les annonces avec le json
        
        //Affichage de chaque annonce
        
        for (Annonce annonce : this.annonces){
            HBox hbox = new HBox();

            hbox.setPrefWidth(600);

            HBox hboxGauche = new HBox();
            HBox hboxCentre = new HBox();
            HBox hboxDroite = new HBox();

            hbox.getChildren().add(hboxGauche);
            hbox.getChildren().add(hboxCentre);
            hbox.getChildren().add(hboxDroite);

            hboxGauche.setPrefWidth(300);
            hboxCentre.setPrefWidth(100);
            hboxDroite.setPrefWidth(150);
            
            Label titrelabel = new Label(annonce.getTitre());
            titrelabel.setStyle("-fx-font-weight: bold;");
            hboxGauche.getChildren().add(titrelabel);

            hboxGauche.getChildren().add(new Label("   "));

            Label categorie = new Label(annonce.getCategorie());
            categorie.setStyle("-fx-text-fill: gray;");
            hboxGauche.getChildren().add(categorie);

            Label prix = new Label("Prix: ");
            prix.setStyle("-fx-text-fill: gray;");

            hboxCentre.getChildren().add(prix);
            hboxCentre.getChildren().add(new Label(annonce.getPrix()+""));
            Image image = new Image(getClass().getResource("images/florain.jpg").toExternalForm());
            ImageView imagev = new ImageView(image);
            
            Rectangle clip = new Rectangle(image.getWidth(), image.getHeight());
            clip.setArcWidth(20); // Modifier la courbure selon vos préférences
            clip.setArcHeight(20);
            imagev.setClip(clip);
            imagev.setFitHeight(18);
            imagev.setFitWidth(18);


            hboxCentre.getChildren().add(imagev);

            Button button = new Button();
            button.setText("Voir les détails");
            button.setOnAction((event) -> {
                detailsAnnonce(annonce);
            });
            button.setId(annonce.getId()+"");
            hboxDroite.getChildren().add(button);
            
            hbox.setStyle("-fx-background-color: #eeeeee;");
            hbox.setSpacing(10);
            VBoxAnnonces.getChildren().add(hbox);
        }
    }

      public void synchroJson() throws IOException {
        //synchronise les annonces avec le json

        // Lecture dans le fichier JSON
          FileAccess fileAccess = new FileAccess();
          String filePath = fileAccess.getPathOf("annonces.json");
          File file = new File(filePath);
          String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
          JSONObject existingData = new JSONObject(fileContent);

        //parcourir le json et ajouter les annonces à la liste
        
        Set<String> keys = existingData.keySet();
        keys.remove("id_annonce");
        for (String key : keys){
            JSONObject annonce = existingData.getJSONObject(key);
            
            //AJOUTER VERIF POUR QUE LES ANNONCES CORRESPONDENT AU USER CONNECTE

            //System.out.println(annonce.getString("referent"));
            if (app.getMainUser()!=null){
                if (annonce.getString("referent").equals(app.getMainUser().getUserName())){
                    this.annonces.add(new Annonce(Integer.parseInt(key),annonce.getString("titre"), annonce.getString("categorie"), annonce.getString("description"), annonce.getInt("prix"), annonce.getString("referent"), annonce.getBoolean("actif")));
                }
            }
        }
        }

        @Override
        public void update(String type) {
            try {
                initialize();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



