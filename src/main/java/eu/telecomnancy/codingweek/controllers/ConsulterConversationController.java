package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;

public class ConsulterConversationController implements Observer {

    private Application app;
    private String message;

    public ConsulterConversationController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    public void initialize() {
//        message = app.getConversationAffichee();
//        if (message == null){return;}
//        //todo
    }

    @Override
    public void update(String type) {
        if(type == "conversation"){
            initialize();
        }
    }
}
