package readability.methods;

import readability.TextStats;

public class ColemanLiauIndex extends GenericMethod {
    public ColemanLiauIndex(TextStats stats) {
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
        return "Coleman–Liau index";
    }
}
