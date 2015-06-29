package com.example.sp_root.twentytiles7words;

/**
 * The Letter Class stores information for Telugu characters.
 */
public class Letter {
    /**
     * Specifies that this letter contains no data.
     */
    public final static int EMPTY = -1;
    /**
     * Specifies that this letter is not of a specific type.
     */
    public final static int NO_TYPE = 0;
    /**
     * Specifies that this letter is a vowel.
     */
    public final static int VOWEL = 1;
    /**
     * Specifies that this letter is a consonant.
     */
    public final static int CONSONANT = 2;
    /**
     * Specifies that this letter is a halant.
     */
    public final static int HALANT = 3;
    /**
     * Specifies that this letter is a number.
     */
    public final static int NUMBER = 4;
    /**
     * Specifies that this letter is of an other type.
     */
    public final static int OTHER = 5;

    // Contains the null character.
    private final static char NULL_CHARACTER = '\u0000';
    // Holds the type of the letter.
    private int type;
    // Holds the transliteration of the letter.
    private String transliteration;
    // Holds the Unicode of the Telugu letter.
    private char unicode;
    // Holds the Unicode of the dependent form of the Telugu letter.
    private char dependentUnicode;

    /**
     * Creates a Letter containing the specified Unicode of the Telugu letter.
     *
     * @param unicode A char containing the Unicode of the Telugu letter.
     */
    public Letter(char unicode) {
        this(NO_TYPE, "", unicode, NULL_CHARACTER);
    }

    /**
     * Creates a Letter containing the specified type, transliteration and
     * Unicode of the Telugu letter.
     *
     * @param type            An int containing the type of the Letter.
     * @param transliteration A String containing the transliteration of the Letter.
     * @param unicode         A char containing the Unicode of the Telugu letter.
     */
    public Letter(int type, String transliteration, char unicode) {
        this(type, transliteration, unicode, NULL_CHARACTER);
    }

    /**
     * Creates a Letter containing the specified type, transliteration, Unicode
     * of the Telugu letter, and Unicode of the dependent form of the Telugu
     * letter.
     *
     * @param type              An int containing the type of the Letter.
     * @param transliteration   A String containing the transliteration of the Letter.
     * @param unicode           A char containing the Unicode of the Telugu letter.
     * @param dependent_unicode A char containing the Unicode of the dependent form of the
     *                          Telugu letter.
     */
    public Letter(int type, String transliteration, char unicode,
                  char dependent_unicode) {
        this.type = type;
        this.transliteration = transliteration;
        this.unicode = unicode;
        dependentUnicode = dependent_unicode;
    }

    /**
     * Returns the type of the Letter.
     *
     * @return An int containing the type of the Letter.
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the Unicode of the Telugu letter.
     *
     * @return A char containing the Unicode of the Telugu letter.
     */
    public char getUnicode() {
        return unicode;
    }

    /**
     * Returns the Unicode of the dependent form of the Telugu letter.
     *
     * @return A char containing the Unicode of the dependent form of the Telugu
     * letter.
     */
    public char getDependentUnicode() {
        return dependentUnicode;
    }
}
