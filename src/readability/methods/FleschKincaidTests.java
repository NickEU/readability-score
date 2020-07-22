package readability.methods;

import readability.TextStats;

public class FleschKincaidTests extends GenericMethod {
    public FleschKincaidTests(TextStats stats) {
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
        return "Flesch–Kincaid readability tests";
    }
}
