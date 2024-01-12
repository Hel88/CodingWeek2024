package eu.telecomnancy.codingweek.controllers;

import eu.telecomnancy.codingweek.Application;
import eu.telecomnancy.codingweek.global.Messages;

public class ConsulterMessagerieController implements Observer {

        private Application app;

        public ConsulterMessagerieController(Application app) {
            this.app = app;
            app.addObserver(this);
        }

    @Override
    public void update(String type) {
        //todo
    }
}
