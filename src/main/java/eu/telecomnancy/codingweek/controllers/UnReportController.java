package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;

public class UnReportController implements Observer{

    private final Application app;

    public UnReportController(Application app) {
        this.app = app;
        app.addObserver(this);
    }

    @Override
    public void update(String str) {
        // TODO Auto-generated method stub
    }
}
