package readability.methods;

import readability.TestingMethod;
import readability.TextStats;
import readability.Util;

import java.util.Map;

import static java.util.Map.entry;

public abstract class BaseMethod implements TestingMethod {
    protected final TextStats stats;
    protected double score;
    protected int age;
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

    public BaseMethod(TextStats stats) {
        this.stats = stats;
        calculateScore();
        calculateAge();
    }

    abstract protected void calculateScore();

    private void calculateAge() {
        int score = (int) Math.round(getScore());
        if (score > 14) {
            score = 14;
        } else if (score < 1) {
            score = 1;
        }
        setAge(scoresToAges.get(score));
    }

    protected void setScore(double formulaResult) {
        this.score = Util.truncDouble(formulaResult);
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
