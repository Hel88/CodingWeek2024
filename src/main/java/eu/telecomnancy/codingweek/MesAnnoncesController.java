package eu.telecomnancy.codingweek;

public class MesAnnoncesController {
    

    private HelloApplication app;

    public MesAnnoncesController(HelloApplication app) {
        this.app = app;
    }

    public void detailsAnnonce(){
        app.getSceneController().switchToMonAnnonce();
    }

    public void creerAnnonce(){
        app.getSceneController().switchToCreationAnnonce();
    }


}
