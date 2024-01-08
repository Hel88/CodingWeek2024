package eu.telecomnancy.codingweek;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneController {

    Stage primaryStage;
    Scene connexion;
    Scene inscription;
    Scene menu;
    Scene creationAnnonce;
    Scene monProfil;
    Scene monAnnonce;
    BorderPane layout;


    public SceneController(Stage primaryStage, HelloApplication app) throws Exception {

        this.primaryStage = primaryStage;
        //app.setSceneController(this);

        this.layout = new BorderPane();

        FXMLLoader pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("Connexion.fxml"));
        pageLoader.setControllerFactory(iC->new ConnexionController(app));
        Scene pageScene = new Scene(pageLoader.load());
        this.connexion = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("Inscription.fxml"));
        pageLoader.setControllerFactory(iC->new InscriptionController(app));
        pageScene = new Scene(pageLoader.load());
        this.inscription = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("CreationAnnonce.fxml"));
        pageLoader.setControllerFactory(iC->new CreationAnnonceController(app));
        pageScene = new Scene(pageLoader.load());
        this.creationAnnonce = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("menuBar.fxml"));
        pageLoader.setControllerFactory(iC->new MenuController(app));
        pageScene = new Scene(pageLoader.load());
        this.menu = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("MonProfil.fxml"));
        pageLoader.setControllerFactory(iC->new MonProfilController(app));
        pageScene = new Scene(pageLoader.load());
        this.monProfil = pageScene;

        pageLoader = new FXMLLoader();
        pageLoader.setLocation(getClass().getResource("MonAnnonce.fxml"));
        pageLoader.setControllerFactory(iC->new MonAnnonceController(app));
        pageScene = new Scene(pageLoader.load());
        this.monAnnonce = pageScene;        
        
        layout.setTop(menu.getRoot());
        setView(this.connexion);
        
        primaryStage.setScene(new Scene(layout));
        primaryStage.show();
    }

    public void setView(Scene scene){
        layout.setCenter(scene.getRoot());
    }

    public void switchToInscription() {
        //primaryStage.setScene(this.inscription);
        setView(this.inscription);
    }

    public void switchToConnexion() {
        //primaryStage.setScene(this.connexion);
        setView(this.connexion);
    }

    public void switchToCreationAnnonce() {
        primaryStage.setScene(this.creationAnnonce);
    }

    public void switchToMonProfil() {
        primaryStage.setScene(this.monProfil);
    }

    public void switchToMonAnnonce() {
        primaryStage.setScene(this.monAnnonce);
    }

}
