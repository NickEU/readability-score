package readability.methods;

import readability.TestingMethod;
import readability.TextStats;
import readability.Util;

public abstract class BaseMethod implements TestingMethod {
    protected final TextStats stats;
    protected double score;
    protected int age;

    public BaseMethod(TextStats stats) {
        this.stats = stats;
        calculateScore();
        calculateAge();
    }

    abstract protected void calculateScore();

    protected void calculateAge() {
        int score = (int) Math.ceil(getScore());
        if (score > 14) {
            score = 14;
        } else if (score < 1) {
            score = 1;
        }
        setAge(Util.scoresToAges.get(score));
    }

    protected void setScore(double score) {
        this.score = score;
    }

    protected void setAge(int age) {
        this.age = age;
    }

    @Override
    public double getScore() {
        return score;
    }

    @Override
    public int getRecommendedAge() {
        return age;
    }
}
