package eu.telecomnancy.codingweek;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

    Stage primaryStage;
    Scene connexion;
    Scene inscription;
    Scene creationAnnonce;

    public SceneController(Stage primaryStage, HelloApplication app) throws Exception {

        this.primaryStage = primaryStage;
        //app.setSceneController(this);

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

        primaryStage.setScene(this.connexion);
        primaryStage.show();
    }

    public void switchToInscription() {
        primaryStage.setScene(this.inscription);
    }

    public void switchToConnexion() {
        primaryStage.setScene(this.connexion);
    }

    public void switchToCreationAnnonce() {
        primaryStage.setScene(this.creationAnnonce);
    }

}
