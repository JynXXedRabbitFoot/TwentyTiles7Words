package com.example.sp_root.twentytiles7words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Model7LW {
    // Hold the words and clues as they are loaded from a file.
    private ArrayList<ArrayList<String>> wordsAndClues;
    // hold the words after they are broken into tiles.
    private ArrayList<String> tiles;
    // The language of the words and clues.
    Language language;
    private Controller controller;

    public Model7LW(Language language) throws Exception {
        this.language = language;
        this.controller = Controller.instance();
        loadWords();
        makeTokens();
    }

    /*
     * Converts the words into tiles.
     */
    private void makeTokens() {
        ArrayList<ArrayList<String>> wordsToBeTokenized = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < wordsAndClues.size(); i++) {
            wordsToBeTokenized.add(language
                    .splitToLogicalCharacters(wordsAndClues.get(i).get(0)));
        }
        tiles = WordsToTilesSplitter.getTiles(wordsToBeTokenized);
    }

    /*
     * Loads the words and clues from a file.
     */
    private void loadWords() throws Exception {
        wordsAndClues = new ArrayList<ArrayList<String>>();
        String line = "";
        String delimiter = ",";
        InputStream fis = this.language.seedFile;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            controller.makeToast("The Word File Specified Is Not Encoded As UTF-8.");
            throw new Exception("Encoding Error");
        }
        // Loop file while data exists.
        try {
            while ((line = br.readLine()) != null) {
                // use space as separator, split lines to ArrayLists
                wordsAndClues.add(new ArrayList<String>(Arrays.asList(line
                        .split(delimiter))));
            }
        } catch (IOException e) {
            controller.makeToast("There Was An Error Reading Valid Data From The File.");
            throw new Exception("File read error");
        }
        // Discard first element as it is a header.
        wordsAndClues.remove(0);
        for (int i = 0; i < wordsAndClues.size(); i++) {
            for (int j = 0; j < wordsAndClues.get(i).size(); j++) {
                wordsAndClues.get(i).set(j, wordsAndClues.get(i).get(j).trim());
            }
        }
        Collections.shuffle(wordsAndClues);
        if (wordsAndClues.size() < 7) {
            controller.makeToast("Insufficient Words In The Seed File. File Must Contain 8 Words Minimum.");
            throw new Exception(
                    "Insufficient Words Seed File. File Must Contain 8 Words Minimum.");
        } else {
            while (wordsAndClues.size() > 7) {
                wordsAndClues.remove(0);
            }
        }
        for (int i = 0; i < wordsAndClues.size(); i++) {
            String tempString = removeSpaces(wordsAndClues.get(i).get(0));
            wordsAndClues.get(i).set(0, tempString);
        }

        br.close();
        fis.close();
    }

    private String removeSpaces(String arg) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arg.length(); i++) {
            if (arg.charAt(i) != ' ') {
                sb.append(arg.charAt(i));
            }
        }
        return sb.toString();
    }

    public ArrayList<ArrayList<String>> getWordsAndClues() {
        return wordsAndClues;
    }

    public ArrayList<String> getTiles() {
        return tiles;
    }
}