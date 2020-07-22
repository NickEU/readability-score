package readability.methods;

import readability.TextStats;

public class SMOGIndex extends GenericMethod {
    public SMOGIndex(TextStats stats) {
        super(stats);
    }

    @Override
    protected void calculateScore() {

    }

    @Override
    protected void calculateAge() {

    }

    @Override
    public String getName() {
        return "Simple Measure of Gobbledygook";
    }
}
