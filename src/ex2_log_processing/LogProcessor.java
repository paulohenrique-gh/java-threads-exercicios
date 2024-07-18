package ex2_log_processing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class LogProcessor implements Callable<Integer> {
    private String filePath;
    private String wordToCount;
    private List<String> lines;

    public LogProcessor(String filePath, String wordToCount) {
        this.filePath = filePath;
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

    private Integer countOccurrences() {
        // WIP:
        return 0;

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
    public Integer call() throws Exception {
        // processar arquivo de log para contar as ocorrências de uma palavra específica
        return 0;
    }
}
