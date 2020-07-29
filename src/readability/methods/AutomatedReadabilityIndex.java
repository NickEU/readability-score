package readability.methods;

import readability.TextStats;
import readability.Util;

public class AutomatedReadabilityIndex extends BaseMethod {
    public AutomatedReadabilityIndex(TextStats stats) {
        super(stats);
    }

    protected void calculateScore() {
        double formulaResult = 4.71 * stats.getCharacters() / stats.getWords()
                + 0.5 * stats.getWords() / stats.getSentences() - 21.43;
        setScore(Util.truncDouble(formulaResult));
    }

    @Override
    public String getName() {
        return "Automated Readability Index";
    }
}
