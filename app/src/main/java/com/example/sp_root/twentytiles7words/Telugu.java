package com.example.sp_root.twentytiles7words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Class Used in working with the Telugu language
 *
 * @author Dan Kruse
 */

public class Telugu extends Language {
    InputStream letterDefinition = FullscreenActivity.assetToInputStream("TE/Telugu.txt");
    ;

    // Contains the Unicode for the halant character which is used to combine consonants.
    // (this is for consonant blends)
    private Letter halant;

    // Contains the default vowel.
    private Letter defaultVowel;

    // Contains the alphabet.
    private ArrayList<Letter> alphabet;

    // Contains the largest size of a transliterate character.
    private int maxTransliterateSize = 0;

    BufferedReader br;

    /**
     * Creates a Transliteration object and loads transliteration data from the
     * specified file. Instances of the File class are immutable and there is
     * not a default constructor because the transliteration data must be loaded
     * from a file.
     *
     * @throws IOException
     * @throws FileNotFoundException If the file does not exist, is a directory rather than a
     *                               regular file, or for some other reason cannot be opened for
     *                               reading.
     */
    public Telugu() throws Exception {
        loadTransliterationData(letterDefinition);
        this.languageAbbreviation = "TE";
        this.font = FullscreenActivity.teTextFont;
        this.seedFile = FullscreenActivity.assetToInputStream("TE/words.txt");
        this.helpMessage = Config.teHELPMESSAGE;
        this.winMessage = Config.teWINMESSAGE;
    }

    /**
     * Parses the specified string of characters in a native script into a list
     * of the characters.
     *
     * @param text A String in a native script to be parsed into individual
     *             characters.
     * @return - An ArrayList of the characters parsed from the specified string.
     */

    public ArrayList<String> splitToLogicalCharacters(String text) {
        // Used to create the list of the characters parsed from the specified
        // string.
        LinkedList<String> logical_char_list = new LinkedList<String>();

        // Used to create the return value;
        // This is used to simply convert the LinkedList to Array
        ArrayList<String> result;

        // Check for valid string.
        if (text == null || text.isEmpty()) {
            return new ArrayList<String>();
        }

        // Contains the index of the the matching character in alphabet.
        int match;
        int vowel_match;
        boolean add_to_previous = false;
        String str = null;
        Collections.sort(alphabet, new LetterCompareUnicode());

        // Iterates through each character in the text.
        for (int i = 0; i < text.length(); i++) {

            if (i != 0) {// Look for M, M can not be the 1'st letter.
                match = Collections.binarySearch(alphabet,
                        new Letter(text.charAt(i)), new LetterCompareUnicode());
                if (match == 1) {
                    str = logical_char_list.removeLast()
                            + Character.toString(text.charAt(i));
                    logical_char_list.addLast(str);
                    continue;
                }
            }
            if (add_to_previous) {
                str = logical_char_list.removeLast()
                        + Character.toString(text.charAt(i));
                logical_char_list.addLast(str);
            } else {
                logical_char_list.addLast(Character.toString(text.charAt(i)));
            }

            // Searches for a match in the alphabet.
            match = Collections.binarySearch(alphabet,
                    new Letter(text.charAt(i)), new LetterCompareUnicode());
            // Match found
            if (match > 0 && i + 1 < text.length()) {
                switch (alphabet.get(match).getType()) {

                    case Letter.HALANT:
                        // Check if next letter is a consonant.
                        match = Collections.binarySearch(alphabet,
                                new Letter(text.charAt(i + 1)),
                                new LetterCompareUnicode());
                        if (match > 0
                                && alphabet.get(match).getType() == Letter.CONSONANT) {
                            add_to_previous = true;
                        } else {
                            add_to_previous = false;
                        }
                        break;
                    case Letter.CONSONANT:
                        // Check if next letter is a dependent vowel or a halant.
                        match = Collections.binarySearch(alphabet,
                                new Letter(text.charAt(i + 1)),
                                new LetterCompareUnicode());
                        vowel_match = searchDependentUnicode(text.charAt(i + 1));
                        if ((match > 0 && alphabet.get(match).getType() == Letter.HALANT)
                                || (vowel_match > 0)) {
                            add_to_previous = true;
                        } else {
                            add_to_previous = false;
                        }
                        break;
                    default:
                        add_to_previous = false;
                }
            } else {
                add_to_previous = false;
            }
        }
        // Convert result from LinkedList to an array.
        result = new ArrayList<String>();
        result.addAll(logical_char_list);
        return result;
    }

    /**
     * This method returns the length of logical characters in a Telugu String
     *
     * @param phrase - The phrase to count characters for.
     * @return The number of logical characters in the telugu string.
     */
    public int getLengthOfLangPhrase(String phrase) {
        ArrayList<String> logical_char_array = splitToLogicalCharacters(phrase);
        return logical_char_array.size();
    }

    /*
     *Loads letter information from an inputStream,
     * creates Letters, and adds them to an alphabet.
     * @param filename - the InputStream to read data from
     * @throws Exception - Error reading from the file.
     */
    private void loadTransliterationData(InputStream filename) throws Exception {
        // Used to store the line being read.
        String[] currentLine;
        try {
            br = new BufferedReader(new InputStreamReader(filename, "UTF8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        alphabet = new ArrayList<Letter>();

        try {
            String line;
            while ((line = br.readLine()) != null) {
                // use space as separator, split lines to ArrayLists

                currentLine = line.trim().split("\\s+");

                // Checks that it isn't empty and stores line.
                if (currentLine.length > 0) {

                    /* Stores the recommended font name.
                    if (currentLine[0].toLowerCase().equals("font")) {
                        recommendedFont = currentLine[1];
                        for (int i = 2; i < currentLine.length; i++) {
                            recommendedFont += " " + currentLine[i];
                        }
                    }*/

                    // Adds vowel to alphabet
                    if (currentLine[0].toLowerCase().equals("vowel")) {
                        if (currentLine.length == 3) {
                            alphabet.add(new Letter(Letter.VOWEL, currentLine[1],
                                    hexToChar(currentLine[2])));
                            maxTransliterateSize = Math.max(maxTransliterateSize,
                                    currentLine[1].length());
                            // Stores default vowel
                            if (defaultVowel == null) {
                                defaultVowel = new Letter(Letter.VOWEL,
                                        currentLine[1], hexToChar(currentLine[2]));
                            }
                        } else if (currentLine.length == 4) {
                            alphabet.add(new Letter(Letter.VOWEL, currentLine[1],
                                    hexToChar(currentLine[2]), hexToChar(currentLine[3])));
                            maxTransliterateSize = Math.max(maxTransliterateSize,
                                    currentLine[1].length());
                        }
                    }

                    // Adds consonant to alphabet
                    else if (currentLine[0].toLowerCase().equals("consonant")
                            && currentLine.length == 3) {
                        alphabet.add(new Letter(Letter.CONSONANT, currentLine[1],
                                hexToChar(currentLine[2])));
                        maxTransliterateSize = Math.max(maxTransliterateSize,
                                currentLine[1].length());
                    }

                    // Adds halant to alphabet
                    else if (currentLine[0].toLowerCase().equals("halant")
                            && currentLine.length == 3 && halant == null) {
                        halant = new Letter(Letter.HALANT, currentLine[1],
                                hexToChar(currentLine[2]));
                        alphabet.add(halant);
                        maxTransliterateSize = Math.max(maxTransliterateSize,
                                currentLine[1].length());
                    }

                    // Adds number to alphabet
                    else if (currentLine[0].toLowerCase().equals("number")
                            && currentLine.length == 3) {
                        alphabet.add(new Letter(Letter.NUMBER, currentLine[1],
                                hexToChar(currentLine[2])));
                        maxTransliterateSize = Math.max(maxTransliterateSize,
                                currentLine[1].length());
                    }

                    // Adds other characters to alphabet
                    else if (currentLine[0].toLowerCase().equals("other")
                            && currentLine.length == 3) {
                        alphabet.add(new Letter(Letter.OTHER, currentLine[1],
                                hexToChar(currentLine[2])));
                        maxTransliterateSize = Math.max(maxTransliterateSize,
                                currentLine[1].length());
                    }

                    // Adds equivalents to equivalent list
                    else if (currentLine[0].toLowerCase().equals("equivalent")
                            && currentLine.length == 3) {
                        // TODO
                    }
                }
            }
        } catch (IOException e) {
            Controller.instance().makeToast("There Was An Error Reading Valid Data From The File.");
            throw new Exception("File read error");
        }
    }

    /*
     * Converts the specified hex number to a char.
     *
     * @param hex String containing a hex number.
     *
     * @return A char containing the specified hex number.
     */
    private char hexToChar(String hex) {
        return (char) Integer.decode("#" + hex).intValue();
    }

    /*
     * Searches the alphabet for Dependent Unicode that match the specified
     * Unicode value.
     *
     * @param unicode - A Unicode value that is being searched for.
     *
     * @return - Returns the index if a match is found or -1 if no match is found.
     */
    private int searchDependentUnicode(char unicode) {
        // Iterates through each letter in the alphabet searching for a match.
        for (int i = 0; i < alphabet.size(); i++) {
            if (alphabet.get(i).getDependentUnicode() == unicode) {
                return i;
            }
        }
        // No match found.
        return -1;
    }

    /*
     * An Internal private class Used to compare two Letters based on their
     * Unicode values
     */
    private class LetterCompareUnicode implements Comparator<Letter> {

        /**
         * Compares the Unicode value of the two specified Letters for order.
         *
         * @param compare_1 - The first Letter to be compared.
         * @param compare_2 - The second Letter to be compared.
         * @return a negative integer, zero, or a positive integer as the
         * Unicode value of the first Letter is less than, equal to, or
         * greater than the Unicode value of the second Letter.
         */
        @Override
        public int compare(Letter compare_1, Letter compare_2) {
            return (int) (compare_1.getUnicode() - compare_2.getUnicode());
        }
    }
}
