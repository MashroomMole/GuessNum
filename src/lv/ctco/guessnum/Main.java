package lv.ctco.guessnum;

import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static List<GameResult> results = new ArrayList<>();

    public static void main(String[] args) {
        do {
            System.out.println("What's your name?");
            String name = scan.next();
            System.out.println("Hello, " + name + "!");
//            String start = scan.next();


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
        for (GameResult r : results) {
            System.out.printf("Player %s has played %d times at %.2f sec\n",
                    r.name,
                    r.triesCount,
                    r.duration / 1000.0);
//            System.out.println("Player: " + r.name + " has played " + r.triesCount + " times at " + r.duration / 1000.0 + " seconds");

        }
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
}


