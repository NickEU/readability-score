package readability;

import readability.methods.AutomatedReadabilityIndex;
import readability.methods.ColemanLiauIndex;
import readability.methods.FleschKincaidTests;
import readability.methods.SMOGIndex;

public class ReadabilityAnalyzer {
    private final TextStats stats;


    ReadabilityAnalyzer(String text) {
        this.stats = new TextStats(text);
    }

    public String getStats() {
        return  "Words: " + stats.getWords() + "\n" +
                "Sentences: " + stats.getSentences() + "\n" +
                "Characters: " + stats.getCharacters() + "\n" +
                "Syllables: " + stats.getSyllables() + "\n" +
                "Polysyllables: " + stats.getPolysyllables() + "\n";
    }

    public String getAnalysis(ScientificMethod userChoice) {
        String result = null;

        switch (userChoice) {
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
        return null;
//        double avgAge
//                = truncDouble(1.0 * (ageARI + ageCL + ageSMOG + ageFK) / 4);
//        return verdictARI + "\n" +
//                verdictFK + "\n" +
//                verdictSMOG + "\n" +
//                verdictCL + "\n\n" +
//                "This text should be understood in average by " + avgAge + " year olds.";
    }
}
