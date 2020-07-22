package readability.methods;

import readability.TestingMethod;
import readability.TextStats;
import readability.Util;

import java.util.Map;

import static java.util.Map.entry;

public class AutomatedReadabilityIndex extends GenericMethod {
    public AutomatedReadabilityIndex(TextStats stats) {
        super(stats);
    }

    protected void calculateScore() {
        double formulaResult = 4.71 * stats.getCharacters() / stats.getWords()
                + 0.5 * stats.getWords() / stats.getSentences() - 21.43;
        this.score = Util.truncDouble(formulaResult);
    }

    protected void calculateAge() {
        int score = (int) Math.ceil(getScore());
        if (score > 14) {
            score = 14;
        } else if (score < 1) {
            score = 1;
        }
        age = scoresToAges.get(score);
    }

    @Override
    public String getName() {
        return "Automated Readability Index";
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
