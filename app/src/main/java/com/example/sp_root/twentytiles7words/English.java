package com.example.sp_root.twentytiles7words;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * An extension of language that implements its abstract methods.
 *
 * @author Dan Kruse 9-24-14
 */
public class English extends Language {

    public English() throws FileNotFoundException {
        this.font = Config.enTextFont;
        this.languageAbbreviation = "EN";
        this.helpMessage = Config.enHELPMESSAGE;
        this.winMessage = Config.enWINMESSAGE;
        //this.seedFile = getClass().getClassLoader().getResourceAsStream(
        //	"EN/words.txt");
        this.seedFile = FullscreenActivity.assetToInputStream("EN/words.txt");
    }

    /**
     * Splits a word into an ArrayList of logical characters.
     *
     * @param word - The word to be split into logical characters.
     * @return - The logical characters of the word as elements of an
     * ArrayList<String>
     * @author Dan-kruse 9-3-14
     */
    public ArrayList<String> splitToLogicalCharacters(String word) {
        ArrayList<String> splitWord = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            splitWord.add(String.valueOf(word.charAt(i)));
        }
        return splitWord;
    }
}
