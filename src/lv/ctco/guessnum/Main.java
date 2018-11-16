package lv.ctco.guessnum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static List<GameResult> results = new ArrayList<>();
    public static final File FILE_RESULT = new File("results.txt");

    public static void main(String[] args) {
        loadResults();
        do {
            System.out.println("What's your name?");
            String name = scan.next();
            System.out.println("Hello, " + name + "!");

            int compNum = rand.nextInt(10) + 1;
            long t1 = System.currentTimeMillis();

            for (int i = 1; i <= 10; i++) {
                System.out.println("Guess the number ->");
                int num = readUserNum();
                if (num == compNum) {
//                    long t2 = System.currentTimeMillis();
                    System.out.println("WOW!!!!");
                    GameResult r = new GameResult();
                    r.name = name;
                    r.triesCount = i;
                    r.duration = System.currentTimeMillis() - t1;
                    results.add(r);
                    break;
                } else if (num > compNum) {
                    System.out.println("Your number is bigger");
                } else {
                    System.out.println("Your number is smaller");
                }
                System.out.println("Your number: " + num);

            }
            System.out.println("Computer's number is " + compNum);
            System.out.println("Press Y to play again");


        } while ("Y".equals(scan.next()));
        showResults();
        saveResults();
    }

    private static void showResults() {
        results.stream()
                .sorted(Comparator.<GameResult>comparingInt(r -> r.triesCount)
                        .<GameResult>thenComparingLong(r -> r.duration))
                .limit(3)
                .forEach(r -> System.out.printf("Player %s has played %d times at %.2f sec\n",
                        r.name,
                        r.triesCount,
                        r.duration / 1000.0));

//        for (GameResult r : results) {
//            System.out.printf("Player %s has played %d times at %.2f sec\n",
//                    r.name,
//                    r.triesCount,
//                    r.duration / 1000.0);
//        }
    }


    private static int readUserNum() {
        while (true) {
            try {
                int num = scan.nextInt();
                if (num < 0 || num > 100) {
                    System.out.println("Please enter a number between 0 and 100");
                    continue;
                }

                return num;
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("You are cheater");
            }
        }
    }

    private static void saveResults() {
        try (PrintWriter fileOut = new PrintWriter(FILE_RESULT)) {

            int skipCount = results.size() - 5;

            results.stream()
                    .skip(skipCount)
                    .forEach(r -> fileOut.printf("%s %d %d\n",
                            r.name,
                            r.triesCount,
                            r.duration));


//            for (GameResult r : results) {
//                if (skipCount <= 0) {
//                    fileOut.printf("%s %d %d\n",
//                            r.name,
//                            r.triesCount,
//                            r.duration);
//                }
//                skipCount--;
//            }
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    private static void loadResults() {
        try (Scanner in = new Scanner(FILE_RESULT)) {
            while (in.hasNext()) {
                GameResult gr = new GameResult();
                gr.name = in.next();
                gr.triesCount = in.nextInt();
                gr.duration = in.nextLong();

                results.add(gr);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}



