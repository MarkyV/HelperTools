/* ============== HelperTools ==============
 * Initial developer: Lukas Diener <lukas.diener@hotmail.com>
 *
 * =====
 * DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE - Version 2
 *
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this license document, and changing it is allowed as long
 * as the name is changed.
 *
 * DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 * TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 * 0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 * =====
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.
 *
 */

package info.michaelkohler.helpertools.string;

import static info.michaelkohler.helpertools.tools.Validator.checkArgument;
import static info.michaelkohler.helpertools.tools.Validator.checkNotNull;

/**
 * The |StringHelper| is a static class which helps
 * performing useful operations on strings easily and
 * efficiently.
 *
 * @author Lukas Diener
 * @version 0.0.1
 */
public final class StringHelper {

    /**
     * Empty private constructor, no instantiation
     * needed.
     */
    private StringHelper() {
        throw new AssertionError("Cannot instantiate this class");
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param in string to check
     * @return whether the string is null or empty
     */
    public static boolean isNullOrEmpty(String in) {
        return in == null || in.equals("");
    }

    /**
     * Creates a continuous string out of a String array and a separator.
     *
     * @param items string array to join
     * @param separator the separator to use between the elements
     * @return joined string
     */
    public static String join(String[] items, String separator) {
        checkNotNull(items, "items cannot be null");
        checkArgument(items.length > 0, "items cannot be empty");
        
        StringBuilder sb = new StringBuilder();
        sb.append(items[0]);
        for (int i = 1; i < items.length; i++) {
            sb.append(separator);
            sb.append(items[i]);
        }
        return sb.toString();
    }

    /**
     * Counts the occurrences of a character in a given string.
     *
     * @param text the text in which to search
     * @param character the character to match
     * @return number of occurrences
     */
    public static int countChar(String text, char character) {
        checkNotNull(text, "text cannot be null");
        
        if (text.indexOf(character) < 0) {
            // If text doesn't contain character, String#indexOf returns -1, so
            // prevent ArrayIndexOutOfBounds by returning now.
            return 0;
        }
        
        int n = 0;
        char[] characters = text.toCharArray();
        for (int i = text.indexOf(character); i < characters.length; i++) {
            if (characters[i] == character) {
                n++;
            }
        }
        return n;
    }

    /**
     * Add a character in front of a string until it
     * reaches the given length. When the string is longer
     * than the specified length, the string is returned
     * as-is.
     *
     * @param string the source string
     * @param length the desired length of the final string
     * @param c the character to fill in
     * @return the pre-padded string
     */
    public static String prepad(String string, int length, char c) {
        checkNotNull(string, "string cannot be null");
        int needed = length - string.length();
        if (needed <= 0) {
            return string;
        }
        
        char[] padding = new char[needed];
        java.util.Arrays.fill(padding, c);
        StringBuffer sb = new StringBuffer(length);
        sb.append(padding);
        sb.append(string);
        return sb.toString();
    }

    /**
     * Add 0 (null) in front of a number until it
     * reaches the given length. When the number is longer
     * than the specified length, the number is returned
     * as-is.
     *
     * @param i the source number
     * @param length the desired length of the final string
     * @return the pre-padded number
     */
    public static String prepadNumber(int i, int length) {
        return prepad(Integer.toString(i), length, '0');
    }

    /**
     * Add a character after  a string until it
     * reaches the given length. When the string is longer
     * than the specified length, the string is returned
     * as-is.
     *
     * @param string the source string
     * @param length the desired length of the final string
     * @param c the character to fill in
     * @return the post-padded string
     */
    public static String postpad(String string, int length, char c) {
        checkNotNull(string, "string cannot be null");
        int needed = length - string.length();
        if (needed <= 0) {
            return string;
        }
        
        char[] padding = new char[needed];
        java.util.Arrays.fill(padding, c);
        StringBuffer sb = new StringBuffer(length);
        sb.append(string);
        sb.append(padding);
        return sb.toString();
    }

    /**
     * Replace all occurrences of a String within another without repeating
     * parts of the string. Example: Replacing "Obama" with "Barack Obama" in
     * "Obama is the president of the U, S of A. Obama has a dog."
     * results in "Barack Obama is the president of the U, S of A. Barack Obama
     * has a dog."
     *
     * @param text the text in which to search
     * @param find the string to find
     * @param replace the replacement
     * @return the new string
     */
    public static String replaceWithoutRepetition(String text, String find,
                                                                  String replace) {
        checkNotNull(text, "text cannot be null");
        checkNotNull(find, "find cannot be null");
        checkNotNull(replace, "replace cannot be null");
        
        String notMatch = replace.replace(find, "");

        String out = text.replaceAll(find, replace);
        out = out.replaceAll(replace + notMatch, replace);
        out = out.replaceAll(notMatch + replace, replace);
        return out;
    }
}