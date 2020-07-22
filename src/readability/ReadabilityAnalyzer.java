package readability;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import static java.util.Map.entry;

public class ReadabilityAnalyzer {
    private final String text;
    private Integer characters;
    private Integer sentences;
    private Integer words;
    private int ageARI;
    private Double scoreARI;
    private int ageFK;
    private int ageSMOG;
    private int ageCL;

    ReadabilityAnalyzer(String text) {
        this.text = text;
    }

    public String getStats() {
        return  "Words: " + getWordCount() + "\n" +
                "Sentences: " + getSentenceCount() + "\n" +
                "Characters: " + getCharacterCount() + "\n" +
                "Syllables: " + getSyllableCount() + "\n" +
                "Polysyllables: " + getPolysyllableCount() + "\n";
    }

    public String getAnalysis(ScientificMethod userChoice) {
        String result = null;

        switch (userChoice) {
            case ARI:
                result = getARIVerdict();
                break;
            case FK:
                result = getFleschKincaidVerdict();
                break;
            case SMOG:
                result = getSimpleMeasureVerdict();
                break;
            case CL:
                result = getColemanLiauVerdict();
                break;
            case ALL:
                result = getVerdictFromAllMethods();
                break;
        }
        return result;
    }

    private String getFleschKincaidVerdict() {
        ageFK = 2;
        return "Flesch–Kincaid readability tests: ";
    }

    private String getSimpleMeasureVerdict() {
        ageSMOG = 3;
        return "Simple Measure of Gobbledygook: ";
    }

    private String getColemanLiauVerdict() {
        ageCL = 4;
        return "Coleman–Liau index: ";
    }

    private String getVerdictFromAllMethods() {
        String verdictARI = getARIVerdict();
        String verdictFK = getFleschKincaidVerdict();
        String verdictSMOG = getSimpleMeasureVerdict();
        String verdictCL = getColemanLiauVerdict();
        double avgAge
                = truncDouble(1.0 * (ageARI + ageCL + ageSMOG + ageFK) / 4);
        return verdictARI + "\n" +
                verdictFK + "\n" +
                verdictSMOG + "\n" +
                verdictCL + "\n\n" +
                "This text should be understood in average by " + avgAge + " year olds.";
    }

    private int getPolysyllableCount() {
        return 0;
    }

    private int getSyllableCount() {
        return 0;
    }

    private String getARIVerdict() {
        int score = (int) Math.ceil(getScoreARI());
        if (score > 14) {
            score = 14;
        } else if (score < 1) {
            score = 1;
        }
        ageARI = scoresToAges.get(score);
        return "Automated Readability Index: "
                + score + " (about " + ageARI + " year olds).";
    }

    private double getScoreARI() {
        if (scoreARI != null) {
            return scoreARI;
        }
        double formulaResult = 4.71 * getCharacterCount() / getWordCount()
                + 0.5 * getWordCount() / getSentenceCount() - 21.43;
        scoreARI = truncDouble(formulaResult);
        return scoreARI;
    }

    private Double truncDouble(double toBeTruncated) {
        return BigDecimal.valueOf(toBeTruncated)
                .setScale(2, RoundingMode.FLOOR).doubleValue();
    }

    private int getCharacterCount() {
        if (characters == null) {
            characters = text.replaceAll("\\s", "").length();
        }
        return characters;
    }

    private int getSentenceCount() {
        if (sentences == null) {
            sentences = text.split("[.?!]\\s*").length;
        }
        return sentences;
    }

    private int getWordCount() {
        if (words == null) {
            words = text.split("\\s+").length;
        }
        return words;
    }

    private final static Map<Integer, Integer> scoresToAges = Map.ofEntries(
            entry(1, 6),
            entry(2, 7),
            entry(3, 9),
            entry(4, 10),
            entry(5, 11),
            entry(6, 12),
            entry(7, 13),
            entry(8, 14),
            entry(9, 15),
            entry(10, 16),
            entry(11, 17),
            entry(12, 18),
            entry(13, 24),
            entry(14, 25)
    );
}
