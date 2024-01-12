package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;

public class MesConversationController implements Observer {
    private Application app;

    public MesConversationController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    public void initialize() {
        //todo
    }

    public void update(String type) {
        if (type == "conversation") {
            initialize();
        }
    }
}
