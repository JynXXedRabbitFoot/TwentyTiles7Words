package com.example.sp_root.twentytiles7words;

import android.graphics.Typeface;

import static android.graphics.Typeface.DEFAULT;

/**
 * Class Config holds configurable options for the game
 *
 * @author Dan Kruse
 */
public class Config {
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
    public static final String enWINMESSAGE = "YOU DID IT !!! GREAT JOB!!!"
            + "\n Click on 'Start New Game' if you would like to play another game. ";

    public static Typeface enTextFont = DEFAULT;

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
}
