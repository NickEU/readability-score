package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = parseArgumentsFindFilename(args);
        String text = readTextFromFile(fileName);
        if (text != null) {
            var analyzer = new Analyzer(text);
            String stats = analyzer.getStats();
            System.out.println(stats);
            UserCmd userCmd = getInputFromUser();
            String analysis = analyzer.getResults(userCmd);
            System.out.println(analysis);
        }
    }

    private static UserCmd getInputFromUser() {
        Scanner sc = new Scanner(System.in);
        UserCmd result = null;
        while (result == null) {
            System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
            String cmd = sc.nextLine().toUpperCase();
            switch (cmd) {
                case "ARI":
                    result = UserCmd.ARI;
                    break;
                case "FK":
                    result = UserCmd.FK;
                    break;
                case "SMOG":
                    result = UserCmd.SMOG;
                    break;
                case "CL":
                    result = UserCmd.CL;
                    break;
                case "all":
                    result = UserCmd.ALL;
                    break;
                default:
                    System.out.println("Error! Not a supported method.");
            }
        }
        return result;
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
