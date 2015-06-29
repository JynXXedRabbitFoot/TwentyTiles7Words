package com.example.sp_root.twentytiles7words;

import java.util.ArrayList;

/**
 * This Singleton controller interfaces between the GUI and Model.
 *
 * @author Dan KRuse
 */
public class Controller {

    private Language language;
    private ArrayList<ArrayList<String>> wordsAndClues = new ArrayList<ArrayList<String>>();
    private ArrayList<String> tiles;
    private static Controller controller;
    FullscreenActivity fullscreenActivity;

    /*
    Singleton design pattern
     */
    private Controller() {
    }

    /**
     * Accessor for the static instance of this controller.
     *
     * @return
     */
    public static Controller instance() {
        if (null == controller) {
            controller = new Controller();
        }
        return controller;
    }

    /**
     * Creates a new model
     */
    public void start() {
        Model7LW puzzle = null;
        try {
            puzzle = new Model7LW(language);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Load words & clues from the model.
        wordsAndClues = puzzle.getWordsAndClues();
        // Load tiles from the model.
        tiles = puzzle.getTiles();
    }

    /**
     * Chained Method. Calls the same method in the FullScreenActivity class to make a toast.
     * Located here because any class can access an instance of this class.
     *
     * @param msg
     */
    public void makeToast(String msg) {
        fullscreenActivity.makeToast(msg);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setFullscreenActivity(FullscreenActivity fullscreenActivity) {
        this.fullscreenActivity = fullscreenActivity;
    }

    public ArrayList<ArrayList<String>> getWordsAndClues() {
        return wordsAndClues;
    }

    public ArrayList<String> getTiles() {
        return tiles;
    }
}
