package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String fileName = parseArgumentsFindFilename(args);
        String text = readTextFromFile(fileName);
        if (text != null) {
            String analysis = ReadabilityAnalyzer.analyze(text);
            System.out.println(analysis);
        }
    }

    private static String readTextFromFile(String fileName) {
        if (fileName == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        try (var lines = Files.lines(
                Paths.get(System.getProperty("user.dir"), fileName))) {
            lines.forEach(result::append);
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
            return null;
        }
        return result.toString();
    }

    private static String parseArgumentsFindFilename(String[] args) {
        if (args.length < 1) {
            System.out.println("Error! No arguments provided! Expected a filename!");
            return null;
        }
        return args[0];
    }
}
