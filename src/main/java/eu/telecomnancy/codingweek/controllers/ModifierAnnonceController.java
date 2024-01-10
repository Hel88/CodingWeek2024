package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;

public class ModifierAnnonceController implements Observer{
    private Application app;
    public ModifierAnnonceController(Application app){
        this.app = app;
        app.addObserver(this);
    }

    @Override
    public void update(String type){
        //TODO
    }
}
