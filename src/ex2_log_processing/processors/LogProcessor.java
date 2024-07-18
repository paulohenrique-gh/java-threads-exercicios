package ex2_log_processing.processors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class LogProcessor implements Callable<Integer> {
    private String filePath;
    private String wordToCount;
    private List<String> lines;

    public LogProcessor(String filePath, String wordToCount) {
        this.filePath = filePath;
        this.wordToCount = wordToCount;
        this.lines = new ArrayList<>();
    }

    private void readLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                this.lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer countWordOccurrences() {
        return this.getLines()
                .stream()
                .mapToInt(line -> {
                    String[] words = line.split(" ");
                    long occurrences = Arrays.stream(words)
                            .filter(word -> word.equalsIgnoreCase(this.getWordToCount()))
                            .count();
                    return (int) occurrences;
                })
                .sum();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getWordToCount() {
        return wordToCount;
    }

    public void setWordToCount(String wordToCount) {
        this.wordToCount = wordToCount;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public Integer call() {
        this.readLog();
        return this.countWordOccurrences();
    }
}
