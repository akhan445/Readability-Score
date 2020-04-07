package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadabilityScore {

    private String text;
    private int totalSentences;
    private int totalWords;
    private int totalCharacters;
    private double totalAge;
    private int totalSyllables;
    private int totalPolysyllables;
    private String testType;

    public ReadabilityScore(String fileName) {
        try {
            this.text = new String(Files.readAllBytes(Paths.get(fileName)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void determineReadability() {
        analyzeText();
        displayResults();
        displayReadability(this.testType);

    }

    public void analyzeText() {

        String[] sentences = this.text.split("(!|\\.|\\?)");
        String[] words = String.join(" ", sentences).split("\\s+");
        char[] characters = String.join("", this.text.split("\\s+")).toCharArray();

        this.totalSentences = sentences.length;
        this.totalWords = words.length;
        this.totalCharacters = characters.length;

        //Determine syllables:
        for (String word: words) {
            int numSyllables = 0;

            //iterate over each word and count vowels
            for (int i = 0; i < word.length(); i++) {
                if (isVowel(word.charAt(i))) {
                    //iterate if letter in word is vowel
                    numSyllables++;
                    //exclude double-vowels
                    if (i > 0 && isVowel(word.charAt(i - 1))) {
                        numSyllables--;
                    }
                    // if the last letter is e don't count it
                    if (i == word.length() - 1 && word.charAt(i) == 'e') {
                        numSyllables--;
                    }
                }
            }
            // if syllable count is 0, then consider it 1 otherwise add to total count;
            this.totalSyllables += numSyllables == 0 ? 1 : numSyllables;

            if (numSyllables > 2) this.totalPolysyllables++;
        }

    }
    public static boolean isVowel(char letter) {
        return letter == 'A' || letter == 'a' ||
                letter == 'E' || letter == 'e' ||
                letter == 'I' || letter == 'i' ||
                letter == 'O' || letter == 'o' ||
                letter == 'U' || letter == 'u' ||
                letter == 'Y' || letter == 'y';

    }

    public void displayResults() {
        System.out.println("The text is:");
        System.out.println(text);
        System.out.println("\nWords: " + this.totalWords);
        System.out.println("Sentences: " + this.totalSentences);
        System.out.println("Characters: " + this.totalCharacters);
        System.out.println("Syllables: " + this.totalSyllables);
        System.out.println("Polysyllables: " + this.totalPolysyllables);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        this.testType = new Scanner(System.in).nextLine().trim().toUpperCase();
    }

    public void displayReadability(String testType) {

        System.out.println();
        switch (testType) {
            case "ALL":
                calculateARI();
                calculateFK();
                calculateSMOG();
                calculateCL();
                this.totalAge = this.totalAge / 4;
                break;
            case "ARI":
                calculateARI();
                break;
            case "FK":
                calculateFK();
                break;
            case "SMOG":
                calculateSMOG();
                break;
            case "CL":
                calculateCL();
                break;
        }
        System.out.println("\nThis text should be understood in average by " +
                this.totalAge + " year olds.");
    }

    public int getAgeFromIndex(double score) {
        int age = 0;
        switch ((int) Math.round(score)) {
            case 1:
                age = 6;
                break;
            case 2:
                age = 7;
                break;
            case 3:
                age = 9;
                break;
            case 4:
                age = 10;
                break;
            case 5:
                age = 11;
                break;
            case 6:
                age = 12;
                break;
            case 7:
                age = 13;
                break;
            case 8:
                age = 14;
                break;
            case 9:
                age = 15;
                break;
            case 10:
                age = 16;
                break;
            case 11:
                age = 17;
                break;
            case 12:
                age = 18;
                break;
            case 13:
                age = 24;
                break;
            case 14:
                age = 25;
                break;
        }
        return age;
    }

    public void calculateARI() {

        double score = 4.71 * this.totalCharacters / this.totalWords +
                0.5 * this.totalWords / this.totalSentences - 21.43;

        int age = getAgeFromIndex(score);
        this.totalAge += age;

        System.out.println("Automated Readability Index: " + score +
                " (about " + age + " year olds).");
    }

    public void calculateFK() {

        double score = 0.39 * this.totalWords / this.totalSentences +
                11.8 * this.totalSyllables / this.totalWords - 15.59;

        int age = getAgeFromIndex(score);
        this.totalAge += age;

        System.out.println("Flesch–Kincaid readability tests: " + score +
                " (about " + age + " year olds).");

    }

    public void calculateSMOG() {

        double score = 1.043 * Math.sqrt(this.totalPolysyllables * (30 / this.totalSentences)) + 3.1291;

        int age = getAgeFromIndex(score);
        this.totalAge += age;

        System.out.println("Simple Measure of Gobbledygook: " + score +
                " (about " + age + " year olds).");
    }

    public void calculateCL() {

        String[] letters = this.text.split("[a-zA-z]");

        double L = letters.length / this.totalWords * 100;
        double S = this.totalSentences / this.totalWords * 100;
        double score = 0.0588 * L - 0.296 * S - 15.8;

        int age = getAgeFromIndex(score);
        this.totalAge += age;

        System.out.println("Coleman–Liau index: " + score +
                " (about " + age + " year olds).");
    }

}
