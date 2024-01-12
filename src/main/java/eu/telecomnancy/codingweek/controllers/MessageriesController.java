package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;

public class MessageriesController implements Observer {
    private Application app;

    public MessageriesController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    public void update(String type) {
        //todo
    }
}
