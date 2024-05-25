package pathfinder.model;

import java.io.*;
import java.util.List;
import java.nio.file.*;


public class ScoresLoader {

    public static void saveAttempts(int attempts, String difficulty)
    {
        String filename = "src/main/java/pathfinder/model/BestScores.txt";
        Path path = Paths.get(filename);
        String newAttempts =  String.valueOf(attempts);
        try
        {
            List<String> lines = Files.readAllLines(path);
            switch (difficulty)
            {
                case "Easy":
                    lines.set(0, newAttempts);
                    break;
                case "Medium":
                    lines.set(1, newAttempts);
                    break;
                case "Hard":
                    lines.set(2, newAttempts);
                    break;
            }
            Files.write(Paths.get(filename), lines);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static String getAttempts(String difficulty)
    {
        String filename = "src/main/java/pathfinder/model/BestScores.txt";
        Path path = Paths.get(filename);
        try
        {
            List<String> lines = Files.readAllLines(path);
            switch (difficulty)
            {
                case "Easy":
                    String easyAttempts = lines.get(0);
                    return easyAttempts;
                case "Medium":
                    String medAttempts = lines.get(1);
                    return medAttempts;
                case "Hard":
                    String hardAttempts = lines.get(2);
                    return hardAttempts;
            }
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "N/A";
    }
}

