package com.example.sp_root.twentytiles7words;

import java.util.ArrayList;
import java.util.Collections;

public class WordsToTilesSplitter {
    /**
     * Splits 7 words into 20 tiles containing the words in a broken form.
     *
     * @precondition - Each word must be split into an ArrayList<String> of
     *               logical characters.
     * @precondition - There must be exactly 7 words passed in.
     * @precondition - Each word must contain between 2 and 16 logical
     *               characters inclusive.
     * @precondition - The combination of words needs to contain between 20 and
     *               80 logical characters total, inclusive.
     *
     * @param wordsAsTokens
     *            - The words as tokens to be split into tiles.
     * @return An ArrayList<String> of tiles.
     * @throws IllegalArgumentException
     *             - Indicates the words passed in are causing a problem.
     * @author Dan KRuse
     */

    /**
     * Creates Tiles from a list of words
     * @param wordsAsTokens - The words to be separated into tiles - Must be tokenized to logical characters
     * @return 20 strings in a list
     * @throws IllegalArgumentException - input did not meet specifications.
     * @author Dan KRuse
     */

    private static Controller controller = Controller.instance();

    public static ArrayList<String> getTiles(
            ArrayList<ArrayList<String>> wordsAsTokens)
            throws IllegalArgumentException {

        // Ensure the ArrayList contains exactly seven elements.
        if (wordsAsTokens.size() != 7) {
            throw new IllegalArgumentException(
                    "\nError: Exactly seven strings are required.\n");
        }

        int total_logical_chars = 0;// Track total # of logical characters

        // Ensure each string contains between 4 and 16 characters, inclusive.
        for (int i = 0; i < wordsAsTokens.size(); i++) {
            // Get the length of the word in logical characters.
            int word_length = wordsAsTokens.get(i).size();
            // Ensure the length is between 4 and 16 characters, inclusive.
            if (word_length < 2 || word_length > 16) {
                controller.makeToast("Error: All Words In The Seed File Were Not Between 2 and 16 Characters, Inclusive");
                throw new IllegalArgumentException(
                        "\nError: Words must contain between two and sixteen characters, inclusive.\n");
            }
            // Add the length to the total number of characters.
            total_logical_chars += word_length;
        }

        // Ensure the total # of characters is between 40 and 80 inclusive.
        if (total_logical_chars <= 20 || total_logical_chars >= 80) {
            controller.makeToast("Error: The Words Selected For The Game did not contain between 20 and 80 logical characters total, inclusive");
            throw new IllegalArgumentException(
                    "\nError: The words must contain between 20 and 80 logical characters total, inclusive.\n");
        }

        // Make an ArrayList to hold the 20 generated tiles.
        ArrayList<ArrayList<String>> tiles = new ArrayList<ArrayList<String>>();

        // Break words into substrings.
        for (int i = 0; i < 7; i++) {
            // Copy the word to a temporary string.
            ArrayList<String> word = wordsAsTokens.get(i);
            ArrayList<String> tile;
            // if word is 2-4 chars split into 2 tiles
            if (word.size() < 5) {
                if (word.size() == 2) {
                    //first letter in 1 tile
                    tile = new ArrayList<String>();
                    tile.add(word.remove(0));
                    // Copy the tile to the list of tiles.
                    tiles.add(tile);
                    //2nd letter in another tile
                    tile = new ArrayList<String>();
                    tile.add(word.remove(0));
                    // Copy the tile to the list of tiles.
                    tiles.add(tile);
                } else {// has 3 or 4 chars
                    tile = new ArrayList<String>();
                    // put first 2 into tile
                    tile.add(word.remove(0));
                    tile.add(word.remove(0));
                    // Copy the tile to the list of tiles.
                    tiles.add(tile);
                    // put remaining into tile
                    tile = new ArrayList<String>();
                    for (int v = 0; v < word.size(); v++) {
                        tile.add(word.get(v));
                    }
                    // Copy the tile to the list of tiles.
                    tiles.add(tile);
                }

            } else {

                // While the word has more than five remaining letters, make a
                // substring from four of them.
                while (word.size() > 5) {
                    tile = new ArrayList<String>();
                    for (int j = 0; j < 4; j++) {
                        tile.add(word.remove(0));
                    }
                    // Copy the tile to the list of tiles.
                    tiles.add(tile);
                }
                // If exactly five letters are left, split it into tiles of 3&2
                if (word.size() == 5) {
                    tile = new ArrayList<String>();
                    for (int k = 0; k < 3; k++) {
                        tile.add(word.remove(0));
                    }
                    // Copy the tile to the list of tiles.
                    tiles.add(tile);
                    tile = new ArrayList<String>();
                    for (int l = 0; l < 2; l++) {
                        tile.add(word.remove(0));
                    }
                    // Copy the tile to the list of tiles.
                    tiles.add(tile);
                }
                // Stick the remaining letters in a tile.
                else if (word.size() != 0) {
                    tile = new ArrayList<String>();
                    for (int m = 0; m < word.size(); m++) {
                        tile.add(word.get(m));
                    }
                    // Copy the tile to the list of tiles.
                    tiles.add(tile);
                }
            }
        }

        // Get the number of tiles generated.
        int numberOfTiles = tiles.size();
        Collections.shuffle(tiles);
        ArrayList<String> tile = new ArrayList<String>();
        // If more than 20 tiles were generated, throw an error.
        if (numberOfTiles > 20) {
            controller.makeToast("The Combination of Words Selected Generated Too Many Tiles.");
            throw new IllegalArgumentException(
                    "\nError: This combination of words does not fit.\n");
        }

        // If fewer than 20 tiles were generated, split groups of four until
        // there are enough.
        for (int i = 0; i < numberOfTiles && tiles.size() < 20; i++) {

            // If the tile has four letters, bifurcate it.
            if (tiles.get(i).size() == 4) {
                // Make a new tile from the first half of the original.
                tile = new ArrayList<String>();
                tile.add(tiles.get(i).get(0));
                tile.add(tiles.get(i).get(1));
                tiles.add(tile);
                // Replace the original tile with the 2nd half
                tile = new ArrayList<String>();
                tile.add(tiles.get(i).get(2));
                tile.add(tiles.get(i).get(3));
                tiles.set(i, tile);
            }
        }

        // If fewer than 20 tiles were generated, split groups of three until
        // there are enough.
        for (int i = 0; i < numberOfTiles && tiles.size() < 20; i++) {

            // If the tile has three letters, bifurcate it.
            if (tiles.get(i).size() == 3) {
                // Make a new tile from the first half of the original.
                tile = new ArrayList<String>();
                tile.add(tiles.get(i).get(0));
                tile.add(tiles.get(i).get(1));
                tiles.add(tile);
                // Replace the original tile with the 2nd half
                tile = new ArrayList<String>();
                tile.add(tiles.get(i).get(2));
                tiles.set(i, tile);
            }
        }
        // If fewer than 20 tiles were generated, split groups of 2 until
        // there are enough.
        for (int i = 0; i < numberOfTiles && tiles.size() < 20; i++) {

            // If the tile has 2 letters, bifurcate it.
            if (tiles.get(i).size() == 2) {
                // Make a new tile from the first half of the original.
                tile = new ArrayList<String>();
                tile.add(tiles.get(i).get(0));
                tiles.add(tile);
                // Replace the original tile with the 2nd half
                tile = new ArrayList<String>();
                tile.add(tiles.get(i).get(1));
                tiles.set(i, tile);
            }
        }

        // If it still couldn't generate 20 tiles, throw an error.
        if (tiles.size() != 20) {
            controller.makeToast("This Combination Of Wordas Generated Too Few Tiles. Try Again.");
            throw new IllegalArgumentException(
                    "\nError: This combination of words does not fit.\n");
        }

        ArrayList<String> finalTiles = new ArrayList<String>();
        for (int i = 0; i < tiles.size(); i++) {
            finalTiles.add(arraylistToString(tiles.get(i)));
        }
        // Return the tiles.
        return finalTiles;
    }

    /*
     * covert an arraylist to a string
     */
    private static String arraylistToString(ArrayList<String> word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.size(); i++) {
            sb.append(word.get(i));
        }
        return sb.toString();
    }
}