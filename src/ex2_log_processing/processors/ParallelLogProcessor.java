package ex2_log_processing.processors;

import java.util.List;
import java.util.concurrent.*;

public class ParallelLogProcessor {
    private final ExecutorService executor;
    private List<String> logsPaths;
    private String wordToCount;

    public ParallelLogProcessor(int numThreads, List<String> logsPaths, String wordToCount) {
        this.logsPaths = logsPaths;
        this.wordToCount = wordToCount;
        this.executor = Executors.newFixedThreadPool(numThreads);
    }

    public List<String> getLogsPaths() {
        return logsPaths;
    }

    public void setLogsPaths(List<String> logsPaths) {
        this.logsPaths = logsPaths;
    }

    public String getWordToCount() {
        return wordToCount;
    }

    public void setWordToCount(String wordToCount) {
        this.wordToCount = wordToCount;
    }

    public Integer process() {
        List<LogProcessor> processors = this.logsPaths
                .stream()
                .map(path -> new LogProcessor(path, wordToCount))
                .toList();

        int total = 0;

        try {
            List<Future<Integer>> totalCount = executor.invokeAll(processors);

            for (Future<Integer> count : totalCount) {
                total += count.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

        return total;
    }
}
