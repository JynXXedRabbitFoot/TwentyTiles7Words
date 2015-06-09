package com.example.sp_root.twentytiles7words;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This controller interfaces between the 7LW GUI and Model.
 *
 * @author Dan KRuse
 */
public class Controller {

    private Language language;
    private ArrayList<ArrayList<String>> wordsAndClues = new ArrayList<ArrayList<String>>();
    private ArrayList<String> tiles;
    private static Controller controller;
    FullscreenActivity fullscreenActivity;

    private Controller() {
    }

    public static Controller instance() {
        if (null == controller) {
            controller = new Controller();
        }
        return controller;
    }

    /*public Controller(Language language, FullscreenActivity fullscreenActivity) throws IOException {
        this.language = language;
        this.fullscreenActivity = fullscreenActivity;
        start();
    }*/

    public void start() throws IOException {
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

    public void makeToast(String msg) {
        fullscreenActivity.makeToast(msg);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public FullscreenActivity getFullscreenActivity() {
        return fullscreenActivity;
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
