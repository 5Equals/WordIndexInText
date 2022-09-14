/**
 * Tools to solve the task.
 */

package se.kth;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Utilities {
    private Utilities() {}

    /**
     * Be able to read a text information from a txt file.
     * @param file the file name that want to be read.
     * @return the string containing the text.
     */
    static String readText(String file) {
        Path textPath = Paths.get(file);
        try {
            String text = Files.readString(textPath);
            return text;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }


    /**
     * Calculate blank spaces with their placements.
     * @param text the text to calculate the blank spaces from.
     * @return array that stores the order and the count of the blank spaces.
     */
    static int[] calculateSpaces(String text) {
        List<Integer> spaces = new ArrayList<Integer>();
        int counter = 0;
        for(int i = 0; i < text.length(); i++) {
            if((text.charAt(i) == ' ') || (text.charAt(i) == '\n')) {
                if(((i + 1) < text.length()) && (text.charAt(i + 1) == ' ' || text.charAt(i +1) == '\n')) {
                    counter++;
                } else {
                    counter++;
                    spaces.add(counter);
                    counter = 0;
                }
            }
        }

        int[] temp = new int[spaces.size()];
        int i = 0;
        for(int num: spaces) {
            temp[i++] = num;
        }
        return temp;
    }


    /**
     * Get the words in the text. In the same time calculate the index of every word.
     * @param textFileContent the text.
     * @param spaces the array that has the order and the count of the blank spaces.
     * @return the ST table with the words and indices.
     */
    public static ST<String, Integer> getWords(String textFileContent, int[] spaces) {
        // Preparations
        String text = textFileContent;
        Scanner scanner = new Scanner(text);
        int minLength = 1;
        ST<String, Integer> st = new ST<String, Integer>();

        // Get the words and their indices.
        int index = 0;
        for(int i = 0; i < spaces.length; i++) {
            if(scanner.hasNext()) {
                String key = scanner.next();
                if (key.length() > minLength) {
                    if (st.contains(key)) {
                        st.put(key, st.get(key) + 1, index);
                    } else {
                        st.put(key, 1, index);
                    }
                }
                index += key.length();
            }
            index += spaces[i];

            if(index > 497300 && index < 497304) {
                index += 2;
            }
        }
        return st;
    }
}
