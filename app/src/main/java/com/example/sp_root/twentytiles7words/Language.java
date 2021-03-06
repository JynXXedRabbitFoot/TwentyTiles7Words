package com.example.sp_root.twentytiles7words;

import android.graphics.Typeface;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Language is an abstract class that defines the basic functions of its
 * children.
 *
 * @author Dan Kruse
 */
public abstract class Language {
    protected Typeface font;
    protected String languageAbbreviation;
    protected String helpMessage;
    protected String winMessage;
    protected InputStream seedFile;

    /**
     * Splits a word into an ArrayList of logical characters.
     *
     * @param word - The word to be split into logical characters.
     * @return - The logical characters of the word as elements of an
     * ArrayList<String>
     */
    public abstract ArrayList<String> splitToLogicalCharacters(String word);

    public Typeface getFont() {
        return font;
    }
}
