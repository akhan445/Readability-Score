package readability;

public class Main {
    public static void main(String[] args) {

        ReadabilityScore text = new ReadabilityScore(args[0]);
        text.determineReadability();
    }
}
