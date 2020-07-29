package readability.methods;

import readability.TextStats;
import readability.Util;

public class SMOGIndex extends BaseMethod {
    public SMOGIndex(TextStats stats) {
        super(stats);
    }

    @Override
    protected void calculateScore() {
        double formulaResult = 1.043 * Math.sqrt(stats.getPolysyllables()
        * 30.0 / stats.getSentences()) + 3.1291;
        setScore(Util.truncDouble(formulaResult));
    }

    @Override
    public String getName() {
        return "Simple Measure of Gobbledygook";
    }
}
