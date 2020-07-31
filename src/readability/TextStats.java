package readability;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public class TextStats {
    private final String text;
    private String[] tokens;
    private Integer characters;
    private Integer sentences;
    private Integer words;
    private Integer syllables;
    private Integer polysyllables;

    TextStats(String text) {
        this.text = text;
        countCharacters();
        countSentences();
        countWords();
        countSyllables();
    }

    private void countCharacters() {
        characters = getText().replaceAll("\\s", "").length();
    }

    private void countSentences() {
        sentences = text.split("[.?!]\\s*").length;
    }

    private void countWords() {
        setTokens(text.replaceAll("[.,?!:;]", "").split("\\s+"));
        words = getTokens().length;
    }

    private void countSyllables() {
        String regex = "(?!e\\b)([yaeiou]+|\\b[b-df-hj-np-tv-z]+e\\b)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        AtomicLong sylCount = new AtomicLong();
        AtomicLong polySylCount = new AtomicLong();
        Arrays.stream(getTokens())
                .forEach(word -> {
                    long syllablesInWord = pattern.matcher(word).results().count();
                    sylCount.addAndGet(syllablesInWord);
                    if (syllablesInWord >= 3) {
                        polySylCount.getAndIncrement();
                    }
                });
        syllables = (int) sylCount.get();
        polysyllables = (int) polySylCount.get();
    }

    private String[] getTokens() {
        return tokens;
    }

    private void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public Integer getCharacters() {
        return characters;
    }

    public Integer getSentences() {
        return sentences;
    }

    public Integer getWords() {
        return words;
    }

    public Integer getSyllables() {
        return syllables;
    }

    public Integer getPolysyllables() {
        return polysyllables;
    }

    public String getText() {
        return text;
    }
}
