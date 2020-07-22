package readability.methods;

import readability.TestingMethod;
import readability.TextStats;

public abstract class GenericMethod implements TestingMethod {
    protected final TextStats stats;
    protected double score;
    protected int age;

    public GenericMethod(TextStats stats) {
        this.stats = stats;
        calculateScore();
        calculateAge();
    }

    abstract protected void calculateScore();

    abstract protected void calculateAge();

    @Override
    public double getScore() {
        return score;
    }

    @Override
    public int getRecommendedAge() {
        return age;
    }
}
