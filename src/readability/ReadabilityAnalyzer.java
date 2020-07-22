package readability;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import static java.util.Map.entry;

public class ReadabilityAnalyzer {
    private static String text;
    private static Double score;
    private static Integer characters;
    private static Integer sentences;
    private static Integer words;

    public static String analyze(String input) {
        text = input;
        return  "Words: " + getWordCount() + "\n" +
                "Sentences: " + getSentenceCount() + "\n" +
                "Characters: " + getCharacterCount() + "\n" +
                "The score is: " + getScore() + "\n" +
                "This text should be understood by " + getRecommendedAge();
    }

    private static String getRecommendedAge() {
        int score = (int) Math.ceil(getScore());
        if (score > 14) {
            score = 14;
        }

        return scoresToAges.get(score) + " year olds.";
    }

    private static double getScore() {
        if (score != null) {
            return score;
        }
        double formulaResult = 4.71 * getCharacterCount() / getWordCount()
                + 0.5 * getWordCount() / getSentenceCount() - 21.43;
        score = BigDecimal.valueOf(formulaResult)
                .setScale(2, RoundingMode.FLOOR).doubleValue();
        return score;
    }

    private static int getCharacterCount() {
        if (characters == null) {
            characters = text.replaceAll("\\s", "").length();
        }
        return characters;
    }

    private static int getSentenceCount() {
        if (sentences == null) {
            sentences = text.split("[.?!]\\s*").length;
        }
        return sentences;
    }

    private static int getWordCount() {
        if (words == null) {
            words = text.split("\\s+").length;
        }
        return words;
    }

    private final static Map<Integer, String> scoresToAges = Map.ofEntries(
            entry(1, "5-6"),
            entry(2, "6-7"),
            entry(3, "7-9"),
            entry(4, "9-10"),
            entry(5, "10-11"),
            entry(6, "11-12"),
            entry(7, "12-13"),
            entry(8, "13-14"),
            entry(9, "14-15"),
            entry(10, "15-16"),
            entry(11, "16-17"),
            entry(12, "17-18"),
            entry(13, "18-24"),
            entry(14, "24+")
    );
}
