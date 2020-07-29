package readability.methods;

import readability.TextStats;

public class FleschKincaidTests extends BaseMethod {
    public FleschKincaidTests(TextStats stats) {
        super(stats);
    }

    @Override
    protected void calculateScore() {
        double formulaResult = 0.39 * stats.getWords() / stats.getSentences() + 11.8
                * stats.getSyllables() / stats.getWords() - 15.59;
        setScore(formulaResult);
    }

    @Override
    public String getName() {
        return "Flesch–Kincaid readability tests";
    }
}
