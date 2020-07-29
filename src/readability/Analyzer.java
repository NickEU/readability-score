package readability;

import readability.methods.AutomatedReadabilityIndex;
import readability.methods.ColemanLiauIndex;
import readability.methods.FleschKincaidTests;
import readability.methods.SMOGIndex;

public class Analyzer {
    private final TextStats stats;

    Analyzer(String text) {
        this.stats = new TextStats(text);
    }

    public String getStats() {
        return  "Words: " + stats.getWords() + "\n" +
                "Sentences: " + stats.getSentences() + "\n" +
                "Characters: " + stats.getCharacters() + "\n" +
                "Syllables: " + stats.getSyllables() + "\n" +
                "Polysyllables: " + stats.getPolysyllables() + "\n";
    }

    public String getResults(UserCmd userCmd) {
        String result = null;

        switch (userCmd) {
            case ARI:
                result = getVerdict(new AutomatedReadabilityIndex(stats));
                break;
            case FK:
                result = getVerdict(new FleschKincaidTests(stats));
                break;
            case SMOG:
                result = getVerdict(new SMOGIndex(stats));
                break;
            case CL:
                result = getVerdict(new ColemanLiauIndex(stats));
                break;
            case ALL:
                result = getVerdictFromAllMethods();
                break;
        }
        return result;
    }

    private String getVerdict(TestingMethod app) {
        return app.getName() + ": " + app.getScore()
                + " (about " + app.getRecommendedAge() + " year olds).";
    }

    private String getVerdictFromAllMethods() {
        var ari = new AutomatedReadabilityIndex(stats);
        var fk = new FleschKincaidTests(stats);
        var smog = new SMOGIndex(stats);
        var cl = new ColemanLiauIndex(stats);

        double avgAge
                = Util.truncDouble(1.0
                * (getRecommendedAge(ari) + getRecommendedAge(cl)
                + getRecommendedAge(smog) + getRecommendedAge(fk)) / 4);
        return getVerdict(ari) + "\n" +
                getVerdict(fk) + "\n" +
                getVerdict(smog) + "\n" +
                getVerdict(cl) + "\n\n" +
                "This text should be understood in average by " + avgAge + " year olds.";
    }

    private double getRecommendedAge(TestingMethod app) {
        return app.getRecommendedAge();
    }
}
