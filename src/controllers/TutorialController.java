package controllers;

import views.TutorialView;

public class TutorialController {
	 
    private TutorialView view;
 
    public TutorialController(TutorialView view) {
        this.view = view;
        registerListeners();
    }
 
    private void registerListeners() {
    }
}