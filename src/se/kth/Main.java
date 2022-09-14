/**
 * The task is to write a program that finds a word index. The index, in this case means the count of characters
 * from the beginning to the word.
 */

package se.kth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the word you search for: ");
        String word = scanner.next();
        searchWordPlacement(word);
    }

    static void searchWordPlacement(String word) {
        // Preparations
        String text = Utilities.readText("theFilteredText.txt");

        // Search the word index.
        int[] spaces = Utilities.calculateSpaces(text);
        ST<String, Integer> wordTable = Utilities.getWords(text, spaces);
        System.out.println(wordTable.get(word));
        System.out.println(wordTable.getIndices(word));
        String[] temp = wordTable.getIndices(word).split("-");
        System.out.println(temp.length);
        System.out.println("");
    }
}
