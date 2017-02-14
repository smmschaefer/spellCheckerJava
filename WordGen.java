import java.io.*;
import java.util.*;
import java.lang.*;


public class WordGen {

    private static Scanner x;
    private static ArrayList<String> wordContainer = new ArrayList<>();
    private static ArrayList<Integer> getWords = new ArrayList<>();
    private static List<String> vowelList = new ArrayList<>();
    private static Random getRandom = new Random();

    public static void main(String[] args) {

        vowelList.add("a");
        vowelList.add("e");
        vowelList.add("i");
        vowelList.add("o");
        vowelList.add("u");
        vowelList.add("A");
        vowelList.add("E");
        vowelList.add("I");
        vowelList.add("O");
        vowelList.add("U");

        try {
            x = new Scanner(new File("newWords.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Failed to findVowelPosition file. Exiting program");
        }

        try{
            while (x.hasNextLine()) {
                wordContainer.add(x.next());
            }
            x.close();
        } catch (Exception e) {
            System.out.println("Error - NoElementException");
        }

        loadEnglishWords();
        createMissSpelledWords();
    }

    private static void loadEnglishWords() {
        int count = 0;
        int listSize = wordContainer.size();

        while(count != listSize - 1) {
            getWords.add(getRandom.nextInt(listSize));
            count++;
        }
    }

    private static void createMissSpelledWords() {
        int temp;
        int ran;
	
        for(int i = 0; i < getWords.size(); i++) {
            temp = getWords.get(i);
            ran = getRandom.nextInt(14);

            switch(ran) {
                case 0:
                    System.out.println(addCapsToWord(wordContainer.get(temp)));
                    break;
                case 1:
                    System.out.println(addLettersToWord(wordContainer.get(temp)));
                    break;
                case 2:
                    System.out.println(scrambleVowels(wordContainer.get(temp)));
                    break;
                case 3:
                    System.out.println(addCapsToWord(scrambleVowels(wordContainer.get(temp))));
                    break;
                case 4:
                    System.out.println(addCapsToWord(addLettersToWord(wordContainer.get(temp))));
                    break;
                case 5:
                    System.out.println(scrambleVowels(addCapsToWord(wordContainer.get(temp))));
                    break;
                case 6:
                    System.out.println(scrambleVowels(addLettersToWord(wordContainer.get(temp))));
                case 7:
                    System.out.println(addLettersToWord(addCapsToWord(wordContainer.get(temp))));
                    break;
                case 8:
                    System.out.println(addLettersToWord(scrambleVowels(wordContainer.get(temp))));
                    break;
                case 9:
                    System.out.println(addLettersToWord(addCapsToWord(scrambleVowels(wordContainer.get(temp)))));
                case 10:
                    System.out.println(addLettersToWord(scrambleVowels(addCapsToWord(wordContainer.get(temp)))));
                    break;
                case 11:
                    System.out.println(addCapsToWord(addLettersToWord(scrambleVowels(wordContainer.get(temp)))));
                    break;
                case 12:
                    System.out.println(addCapsToWord(scrambleVowels(addLettersToWord(wordContainer.get(temp)))));
                    break;
                case 13:
                    System.out.println(scrambleVowels(addCapsToWord(addLettersToWord(wordContainer.get(temp)))));
                    break;
                case 14:
                    System.out.println(scrambleVowels(addLettersToWord(addCapsToWord(wordContainer.get(temp)))));
                default:
                    System.out.println("An error has occurred");
                    break;
            }
        }
    }

    private static String addCapsToWord(String word) {
        StringBuilder sb = new StringBuilder(word.length());

        for(char c : word.toCharArray())
            sb.append(getRandom.nextBoolean()
                    ? Character.toLowerCase(c)
                    : Character.toUpperCase(c));

        return sb.toString();
    }

    private static String addLettersToWord(String word) {
        int rand = getRandom.nextInt(8);
        int freq;
        int count = 0;
        StringBuilder sb = new StringBuilder(word.length());
        String[] apart = word.split("");
        List<Integer> num = new ArrayList<>();

        for(int i = 0; i < rand; i++) {
            num.add(getRandom.nextInt(word.length()));
        }

        Collections.sort(num);

        for(String temp : apart) {
            sb.append(temp);
            if (num.contains(count)) {
                freq = Collections.frequency(num, count);
                for(int k = 0; k < freq; k++) {
                    sb.append(temp);
                }
            }
            count++;
        }
        return sb.toString();
    }

    private static String scrambleVowels(String word) {
        int count = 0;
        int findVowelPosition = 0;
        StringBuilder sb = new StringBuilder(word.length());
        String[] apart = word.split("");
        List<String> splittingWord = new ArrayList<>();
        List<Integer> vowelPos = new ArrayList<>();

        for(int i = 0; i < word.length(); i++) {
            splittingWord.add(apart[i]);
        }
        
        for(String temp : splittingWord) {
            if (temp.matches(".*[AEIOUaeiou]")) {
                vowelPos.add(findVowelPosition);
            }
            findVowelPosition++;
        }

        for(String buildWord : apart) {
            if (vowelPos.contains(count)) {
                buildWord = vowelList.get(getRandom.nextInt(vowelList.size()));
            }
            sb.append(buildWord);
            count++;
        }
        return sb.toString();
    }
}
