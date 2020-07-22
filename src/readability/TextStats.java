package readability;

public class TextStats {
    private final String text;
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
        countPolysyllables();
    }

    private void countCharacters() {
        characters = getText().replaceAll("\\s", "").length();
    }

    private void countSentences() {
        sentences = text.split("[.?!]\\s*").length;
    }

    private void countWords() {
        words = text.split("\\s+").length;
    }

    private void countSyllables() {
        syllables = 0;
    }

    private void countPolysyllables() {
        polysyllables = 0;
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
