package lv.ctco.guessnum;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
//        System.out.println("What's your name?");
//        String name = scan.next();
//        System.out.println("Hello, "+ name + "!");


        int compNum = rand.nextInt(10) + 1;

        for (int i = 1; i <= 10; i++) {
            System.out.println("Guess the number");
            int num;
            try {
                num = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You are cheater");
                return;
            }
            System.out.println("My number: " + num);
            if (num == compNum) {
                System.out.println("WOW!!!!");
                break;
            } else if (num > compNum) {
                System.out.println("Your number is bigger");
            } else {
                System.out.println("Your number is smaller");
            }

        }
        System.out.println("AHAHAHA " + compNum);
    }
}

