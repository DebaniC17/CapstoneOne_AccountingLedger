package com.ps;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int mainMenuCommand;

        do {
            System.out.println("Welcome to Accounting Ledger Application");
            System.out.println("Please enter your command: ");
            System.out.println("1) To Add Deposit");
            System.out.println("2) To Make Payment (Deposit)");
            System.out.println("3) Display Ledger Screen");
            System.out.println("0) To Exit Application");
            System.out.println("Command: ");

            try {
                mainMenuCommand = commandScanner.nextInt();
            } catch (InputMismatchException ime) {
                mainMenuCommand = 0;
            }

            switch (mainMenuCommand) {
                case 1:
                    addDeposit();
                    break;

                case 2:
                    makePayment();
                    break;

                case 3:
                    displayLedger();
                    break;

                case 0:
                    System.out.println("Exiting...");

                default:
                    System.out.println("Command not found, please try again");
            }

        } while (mainMenuCommand != 0);

    }
}