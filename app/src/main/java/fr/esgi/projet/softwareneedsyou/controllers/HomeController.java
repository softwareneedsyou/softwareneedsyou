package fr.esgi.projet.softwareneedsyou.controllers;

import fr.esgi.projet.softwareneedsyou.models.DataModel;

public class HomeController {
    private DataModel model;

    public void initModel(DataModel model) {
        if(this.model != null){
            throw new IllegalStateException("There can only be one model.");
        }
        this.model = model;
    }
}
