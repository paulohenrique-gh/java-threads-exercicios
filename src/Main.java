import ex1_banking_system.models.Account;
import ex1_banking_system.models.Bank;
import ex1_banking_system.threads.CustomerThread;
import ex1_banking_system.utils.SystemLogger;
import ex2_log_processing.processors.ParallelLogProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("======== Exercise 1 ========");
        runExercise1();
        System.out.println("======== Exercise 2 ========");
        runExercise2();
    }

    public static void runExercise1() {
        // https://www.baeldung.com/java-synchronized
        // https://www.youtube.com/watch?v=HQh0Omi7k7s
        // https://www.w3schools.com/java/java_date.asp
        // https://www.baeldung.com/java-merge-streams

        Account account1 = new Account(123, 500);
        Account account2 = new Account(332, 800);

        Bank bank = new Bank();
        bank.addAccount(account1);
        bank.addAccount(account2);

        List<CustomerThread> account1Threads = Arrays.asList(
                new CustomerThread(account1, 100, 40),
                new CustomerThread(account1, 48, 299),
                new CustomerThread(account1, 192, 92),
                new CustomerThread(account1, 270, 43)
        );

        List<CustomerThread> account2Threads = Arrays.asList(
                new CustomerThread(account2, 30, 3),
                new CustomerThread(account2, 400, 120),
                new CustomerThread(account2, 544, 88),
                new CustomerThread(account2, 484, 110),
                new CustomerThread(account2, 581, 149),
                new CustomerThread(account2, 469, 98)
        );

        Stream.concat(account1Threads.stream(), account2Threads.stream()).forEach(Thread::start);

        Stream.concat(account1Threads.stream(), account2Threads.stream()).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("\nAccount 1 transactions: ");
        SystemLogger.logAccountTransactions(account1);
        System.out.println("Account 1 final balance - Expected: 636 | Actual: " + account1.getBalance());
        System.out.println("\nAccount 2 transactions: ");
        SystemLogger.logAccountTransactions(account2);
        System.out.println("Account 2 final balance - Expected: 2740 | Actual: " + account2.getBalance());
        System.out.println();
    }

    public static void runExercise2() {
        // https://dba-presents.com/jvm/java/240-executorservice-how-to-complete-a-task-by-multiple-threads-in-java
        // https://www.youtube.com/watch?v=ljSCwqJmgr4&list=PLuYctAHjg89YNXAXhgUt6ogMyPphlTVQG&index=11
        // Log file examples: https://www.ibm.com/docs/en/zos/2.4.0?topic=problems-example-log-file

        int numThreads = 3;
        List<String> logsPathList = Arrays.asList(
                "src/ex2_log_processing/logs/demolog1.log",
                "src/ex2_log_processing/logs/demolog2.log",
                "src/ex2_log_processing/logs/demolog2.log"
        );
        String wordToCount = "connection";

        ParallelLogProcessor parallelProcessor = new ParallelLogProcessor(numThreads, logsPathList, wordToCount);

        int count = parallelProcessor.process();
        System.out.println("Total number of occurrences of the word \"" + wordToCount + "\" in log files: " + count);
        System.out.println();
    }
}
