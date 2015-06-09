package com.example.sp_root.twentytiles7words;

import android.graphics.Typeface;

import static android.graphics.Typeface.DEFAULT;

public class Config {

    // font size for Telugu
    public static final float FONTSIZETELUGU = 26.0f;
    // Default Font Size
    public static final float FONTSIZEDEFAULT = 18.0f;
    // Languages to choose from
    // requires a language class as well as editing a few lines of code in the
    // gui to incorporate more languages.
    public static final String[] LANGUAGES = { "English", "Telugu" };

    //default language is its position in the LANGUAGES[] (0 = english, 1 = telugu);
    public static final int DEFAULTLANGUAGE = 1;

    //works
    public static String directoryForHTMLFiles = "/Users/";

    //works
    public static boolean createHTML = false;


    /*
     * English Options
     */
    // Help Message
    public static final String enHELPMESSAGE = "To start the game select a language from the drop-down menu at the bottom of the screen and click \"New Game\""
            + "\nBuild a word that answers a clue from the clues column."
            + "\nTo build the word click on letter tiles in the appropriate order."
            + "\nAs you click on the letter tiles they will be moved to the text box."
            + "\nWhen you have built the word hit the guess button."
            + "\nIf the word is right it will be shown in the solutions,"
            + "\notherwise the tiles will be put back into play."
            + "\nIf you make a mistake you can hit clear to empty the guess box and try again.";

    // Win Message
    public static final String enWINMESSAGE = "             YOU DID IT !!! GREAT JOB!!!"
            + "\n Click on 'Start New Game' if you would like to play another game. ";

    public static Typeface enTextFont =  DEFAULT;

    public static String enWordsFileName = "words.txt";

    public static String enWordsFilePath = System.getProperty("user.home") + "\\7LW\\EN\\";



    /*
     * Telugu options
     */
    // Help Message
    public static final String teHELPMESSAGE = "In Telugu  To start the game select a language from the drop-down menu at the bottom of the screen and click \"New Game\""
            + "\nBuild a word that answers a clue from the clues column."
            + "\nTo build the word click on letter tiles in the appropriate order."
            + "\nAs you click on the letter tiles they will be moved to the text box."
            + "\nWhen you have built the word hit the guess button."
            + "\nIf the word is right it will be shown in the solutions,"
            + "\notherwise the tiles will be put back into play."
            + "\nIf you make a mistake you can hit clear to empty the guess box and try again.";

    // Win Message
    public static final String teWINMESSAGE = "   in Telugu          YOU DID IT !!! GREAT JOB!!!"
            + "\n Click on 'Start New Game' if you would like to play another game. ";

    public static String teWordsFileName = "words.txt";

    public static String teWordsFilePath = System.getProperty("user.home") + "\\7LW\\TE\\";
}
