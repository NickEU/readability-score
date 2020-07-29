package readability.methods;

import readability.TextStats;
import readability.Util;

public class ColemanLiauIndex extends BaseMethod {
    public ColemanLiauIndex(TextStats stats) {
        super(stats);
    }

    @Override
    protected void calculateScore() {
        double charsPer100Words = 100.0 * stats.getCharacters() / stats.getWords();
        double sentencesPer100Words = 100.0 * stats.getSentences() / stats.getWords();
        double formulaResult = charsPer100Words * 0.0588 - 0.296 * sentencesPer100Words - 15.8;
        setScore(Util.truncDouble(formulaResult));
    }

    @Override
    public String getName() {
        return "Coleman–Liau index";
    }
}
