/*
 *  File: Checker.java
 *  Purpose: Load a list of words into an array and use STDIN to find the closest match.
 *  Description: This program uses Levenshtein distance algorithm in order to find the min
 *  number of insertions, deletions, and substitutions between two sequences.
 *
 *  CASE 1: (Capitalize words)
 *      This case is handled by toLowerCase() function.
 *          EX. input: MOrNiNG output: morning
 *  CASE 2: (repeat letters)
 *      Leveshtein distance algorithm is used to find the best choice.
 *          EX. input: ppeeople output: people
 *  CASE 3: (incorrect vowels)
 *      Leveshtein distance algorithm is used to find the best choice.
 *          EX. input: weke output: wake
 *
 *  SEE README for USAGE
 *
 */

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.lang.*;


public class Checker {

    private static Scanner x;
    private static Scanner u = new Scanner(System.in);
    private static ArrayList<String> wordContainer = new ArrayList<>();

    public static void main(String[] args) {
        String usrInput;

        try {
            x = new Scanner(new File("newWords.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file");
        }

        try {
            while (x.hasNextLine()) {
                wordContainer.add(x.next());
            }
            x.close();
        } catch (Exception e) {
            System.out.println("Error - Exception");
        }

        try{
            while(true) {
                if (!u.hasNext()) {
			break;
                }
                System.out.print("> ");
                usrInput = u.next();
                System.out.println(findClosestMatch(usrInput.toLowerCase()));
            }
        } catch(NoSuchElementException e) {
	   System.out.println("Error - NoSuchElementException");
        	e.printStackTrace();
	} 
    }

    private static String findClosestMatch(String word) {
        Map<Integer, String> wordAndDistanceMap = new HashMap<>();
        wordContainer.forEach(s -> wordAndDistanceMap.put(distance(s, word), s));
        return wordAndDistanceMap.get(Collections.min(wordAndDistanceMap.keySet()));
    }

    /*
     *  Levenshtein distance algorithm as presented on wikipedia
     *  Calculates the shortest distance between two Strings by looking at the min number
     *  of insertions, deletions, and substitutions
     */
    private static int distance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
}
