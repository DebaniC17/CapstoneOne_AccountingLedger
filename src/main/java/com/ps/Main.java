package com.ps;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {

    static Scanner commandScanner = new Scanner(System.in);
    static Scanner inputScanner = new Scanner(System.in);
    static ArrayList<Transaction> allTransactions = new ArrayList<>();


    public static void main(String[] args) {

        //load transactions from file
        loadTransactionsFromFile();

        int mainMenuCommand;

        do {
            System.out.println("Welcome To The Accounting Ledger Application!");
            System.out.println("Please Enter Your Command: ");
            System.out.println("1) To Add Deposit");
            System.out.println("2) To Make Payment (Debit)");
            System.out.println("3) Display Ledger Menu");
            System.out.println("0) To Exit Application");
            System.out.print("Command: ");

            // capture input command and handle incorrect input
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
                    System.out.println("Exiting Application, Goodbye...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Command Not Found, Please Try Again");
            }

        } while (true);

    }

    //load transactions from csv file into a list
    public static void loadTransactionsFromFile() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("transactions.csv"));

            String firstLine = bufferedReader.readLine();
            String input;

            //reach each line and parse the transaction properties
            while ((input = bufferedReader.readLine()) != null) {
                String[] transactionsArr = input.split("\\|");
                String date = transactionsArr[0];
                String time = transactionsArr[1];
                String description = transactionsArr[2];
                String vendor = transactionsArr[3];
                float amount = Float.parseFloat(transactionsArr[4]);

                allTransactions.add(new Transaction(date, time, description, vendor, amount));

            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDeposit() {

        System.out.println("Welcome To The Deposit Screen!");
        System.out.println("Please Enter The Details Of The Deposit...");

        System.out.print("Date(yyyy-MM-dd): ");
        String date = inputScanner.nextLine();


        System.out.print("Time(HH:mm:ss): ");
        String time = inputScanner.nextLine();

        System.out.print("Description: ");
        String description = inputScanner.nextLine();

        System.out.print("Name: ");
        String vendor = inputScanner.nextLine();

        System.out.print("Amount: ");
        float amount = inputScanner.nextFloat();

        inputScanner.nextLine();

        if (amount > 0) {
            // create(write) a new transaction then add it to the csv list
            Transaction transaction = new Transaction(date, time, description, vendor, amount);
            allTransactions.add(transaction);

            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
                bufferedWriter.write(String.format("\n%s|%s|%s|%s|%.2f",
                        transaction.getDate(),
                        transaction.getTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount()
                ));

                bufferedWriter.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Your Deposit Was Successfully Added!");


            int subMenuCommand;

            do {
                System.out.println("What Would You Like To Do Next?");
                System.out.println("1) Add Another Deposit");
                System.out.println("2) Go To Home Menu");
                System.out.println("0) Exit The Application");
                System.out.print("Command: ");

                try {
                    subMenuCommand = commandScanner.nextInt();
                } catch (InputMismatchException ime) {
                    ime.printStackTrace();
                    subMenuCommand = 0;
                }

                switch (subMenuCommand) {
                    case 1:
                        System.out.println("Back To The Deposit Screen!");
                        addDeposit();
                        break;

                    case 2:
                        System.out.println("Going Back To Home Menu...");
                        break;

                    case 0:
                        System.out.println("Exiting Application, Goodbye...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Command Not Found. Please Try Again.");

                }
            } while (subMenuCommand != 1 && subMenuCommand != 2 && subMenuCommand != 0);

        } else {
            System.out.println("Command Not Found.");
        }

    }


    public static void makePayment() {

        System.out.println("Welcome To The Payment Screen!");

        System.out.println("Please Enter The Details Of The Payment...");

        System.out.print("Date(yyyy-MM-dd): ");
        String date = inputScanner.nextLine();


        System.out.print("Time(HH:mm:ss): ");
        String time = inputScanner.nextLine();

        System.out.print("Description: ");
        String description = inputScanner.nextLine();

        System.out.print("Name: ");
        String vendor = inputScanner.nextLine();

        System.out.print("Amount: ");
        float newAmount = inputScanner.nextFloat();

        inputScanner.nextLine();

        //to make input negative
        if (newAmount > 0) {
            newAmount = -newAmount;


            Transaction transaction = new Transaction(date, time, description, vendor, newAmount);
            allTransactions.add(transaction);

            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
                bufferedWriter.write(String.format("\n%s|%s|%s|%s|%.2f",
                        transaction.getDate(),
                        transaction.getTime(),
                        transaction.getDescription(),
                        transaction.getVendor(),
                        transaction.getAmount()
                ));

                bufferedWriter.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Your Payment Was Successfully Added!");

            int subMenuCommand;

            do {
                System.out.println("What Would You Like To Do Next?");
                System.out.println("1) Make Another Payment");
                System.out.println("2) Go To Home Menu");
                System.out.println("0) Exit Application");
                System.out.print("Command: ");

                try {
                    subMenuCommand = commandScanner.nextInt();
                } catch (InputMismatchException ime) {
                    ime.printStackTrace();
                    subMenuCommand = 0;
                }

                switch (subMenuCommand) {
                    case 1:
                        System.out.println("Welcome Back To The Payment Menu!");
                        makePayment();
                        break;

                    case 2:
                        System.out.println("Going To Hope Menu...");
                        break;

                    case 0:
                        System.out.println("Exiting Application, Goodbye...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Command Not Found. Please Try Again.");
                }
            } while (subMenuCommand != 1 && subMenuCommand != 2 && subMenuCommand != 0);

        } else {
            System.out.println("Command Not Found.");
        }
    }

    public static void displayLedger() {

        int subMenuCommand;

        do {
            System.out.println("Welcome To The Ledger Menu!");
            System.out.println("Please Enter Your Command");
            System.out.println("1) Display All Entries");
            System.out.println("2) Display Deposits");
            System.out.println("3) Display Payments");
            System.out.println("4) Display Reports Menu");
            System.out.println("0) Go Back To Home Menu");
            System.out.print("Command: ");

            subMenuCommand = commandScanner.nextInt();

            switch (subMenuCommand) {
                case 1:
                    allTransactions();
                    break;

                case 2:
                    allDeposits();
                    break;

                case 3:
                    allPayments();
                    break;

                case 4:
                    displayReports();
                    break;

                case 0:
                    System.out.println("Going Back To Home Menu...");
                    break;

                default:
                    System.out.println("Command Not Found, Please Try Again");


            }

        } while (subMenuCommand != 0);

    }

    public static void allTransactions() {
        System.out.println("Display All Transactions");

        if (allTransactions.isEmpty()) {
            System.out.println("No Transactions Found, Going Back To The Main Menu...");
            return;
        }

        Collections.sort(allTransactions, (a, b) -> {
            LocalDateTime dateTimeA = LocalDateTime.of(LocalDate.parse(a.getDate()), LocalTime.parse(a.getTime()));
            LocalDateTime dateTimeB = LocalDateTime.of(LocalDate.parse(b.getDate()), LocalTime.parse(b.getTime()));

            return dateTimeB.compareTo(dateTimeA);

        });

        for (Transaction transaction : allTransactions) {
            System.out.println(transaction);

        }


    }

    public static void allDeposits() {
        System.out.println("Display All Deposits");
        for (int i = 0; i < allTransactions.size(); i++) {
            Transaction transaction = allTransactions.get(i);
            if (transaction.deposit()) {
                System.out.println(transaction);
            }
        }
    }

    public static void allPayments() {
        System.out.println("Display All Payments");
        for (int i = 0; i < allTransactions.size(); i++) {
            Transaction transaction = allTransactions.get(i);
            if (transaction.payment()) {
                System.out.println(transaction);
            }
        }
    }

    public static void displayReports() {

        int subMenuCommand;

        do {
            System.out.println("Welcome To The Reports Menu!");
            System.out.println("Please Enter Your Command");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search By Vendor");
            System.out.println("6) Do A Custom Search");
            System.out.println("0) Go Back To Ledger Menu");
            System.out.print("Command: ");

            subMenuCommand = commandScanner.nextInt();

            switch (subMenuCommand) {
                case 1:
                    displayMonthToDate();
                    break;

                case 2:
                    displayPreviousMonth();
                    break;

                case 3:
                    displayYearToDate();
                    break;

                case 4:
                    displayPreviousYear();
                    break;

                case 5:
                    searchByVendor();
                    break;

                case 6:
                    displayAddedSearchFeatures();
                    break;

                case 0:
                    System.out.println("Going Back To Ledger Menu...");

                default:
                    System.out.println("Command Not Found, Please Try Again");
            }
        } while (subMenuCommand != 0);
    }

    public static void displayMonthToDate() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        System.out.println("Month to date transactions: ");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ArrayList<Transaction> filteredMonthlyTransactions = new ArrayList<>();

        for (int i = 0; i < allTransactions.size(); i++) {
            String newTransactionDate = allTransactions.get(i).getDate();
            LocalDate transactionDate = LocalDate.parse(newTransactionDate, dateFormatter);
            Transaction transaction = allTransactions.get(i);

            int transactionMonth = transactionDate.getMonthValue();
            int transactionYear = transactionDate.getYear();

            if (transactionMonth == currentMonth && transactionYear == currentYear) {

                filteredMonthlyTransactions.add(transaction);

            }

        }

        Collections.sort(filteredMonthlyTransactions, (a, b) -> {

            LocalDateTime dateTimeA = LocalDateTime.of(LocalDate.parse(a.getDate()), LocalTime.parse(a.getTime()));
            LocalDateTime dateTimeB = LocalDateTime.of(LocalDate.parse(b.getDate()), LocalTime.parse(b.getTime()));

            return dateTimeB.compareTo(dateTimeA);

        });

        for (Transaction transaction : filteredMonthlyTransactions) {
            System.out.println(transaction);
        }

    }


    public static void displayPreviousMonth() {

        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusMonths(1);
        int previousMonth = previousMonthDate.getMonthValue();
        int previousYear = previousMonthDate.getYear();

        System.out.println("Previous Month Transactions: ");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ArrayList<Transaction> previousMonthTransactions = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), dateFormatter);

            if (transactionDate.getMonthValue() == previousMonth && transactionDate.getYear() == previousYear) {
                previousMonthTransactions.add(transaction);
            }
        }

        Collections.sort(previousMonthTransactions, (a, b) -> {

            LocalDateTime dateTimeA = LocalDateTime.of(LocalDate.parse(a.getDate()), LocalTime.parse(a.getTime()));
            LocalDateTime dateTimeB = LocalDateTime.of(LocalDate.parse(b.getDate()), LocalTime.parse(b.getTime()));

            return dateTimeB.compareTo(dateTimeA);

        });

        if (previousMonthTransactions.isEmpty()) {
            System.out.println("No Transactions Found, Going Back To The Reports Menu...");
        } else {
            for (Transaction transaction : previousMonthTransactions) {
                System.out.println(transaction);
            }


        }
    }

    public static void displayYearToDate() {

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        System.out.println("Year To Date Transactions: ");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ArrayList<Transaction> yearlyTransactions = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), dateFormatter);

            if (transactionDate.getYear() == currentYear) {
                yearlyTransactions.add(transaction);
            }
        }

        Collections.sort(yearlyTransactions, (a, b) -> {
            LocalDateTime dateYearA = LocalDateTime.of(LocalDate.parse(a.getDate(), dateFormatter), LocalTime.parse(a.getTime()));
            LocalDateTime dateYearB = LocalDateTime.of(LocalDate.parse(b.getDate(), dateFormatter), LocalTime.parse(b.getTime()));

            return dateYearB.compareTo(dateYearA);

        });

        if (yearlyTransactions.isEmpty()) {
            System.out.println("No Transactions Found, Going Back To The Reports Menu...");
        } else {

            for (Transaction transaction : yearlyTransactions) {
                System.out.println(transaction);
            }
        }

    }

    public static void displayPreviousYear() {

        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusYears(1);
        int previousYear = previousMonthDate.getYear();

        System.out.println("Previous Year Transactions: ");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ArrayList<Transaction> previousYearTransactions = new ArrayList<>();

        for (Transaction transaction : allTransactions) {
            LocalDate transactionDate = LocalDate.parse(transaction.getDate(), dateFormatter);
            if (transactionDate.getYear() == previousYear) {
                previousYearTransactions.add(transaction);
            }
        }

        Collections.sort(previousYearTransactions, (a, b) -> {
            LocalDateTime dateYearA = LocalDateTime.of(LocalDate.parse(a.getDate(), dateFormatter), LocalTime.parse(a.getTime()));
            LocalDateTime dateYearB = LocalDateTime.of(LocalDate.parse(b.getDate(), dateFormatter), LocalTime.parse(b.getTime()));

            return dateYearB.compareTo(dateYearA);

        });

        if (previousYearTransactions.isEmpty()) {
            System.out.println("No Transactions Found, Going Back To The Reports Menu...");
        } else {

            for (Transaction transaction : previousYearTransactions) {
                System.out.println(transaction);
            }
        }

    }

    public static void searchByVendor() {

        System.out.println("Search By Vendor");

        System.out.println("Please Enter The Vendor's Name That You're Looking For");
        System.out.print("Name: ");
//
//        inputScanner.nextLine();
        String nameToSearch = inputScanner.nextLine();

        ArrayList<Transaction> vendorsTransactions = new ArrayList<>();

        for (Transaction vendorsTransaction : allTransactions)

            if (vendorsTransaction.getVendor().equalsIgnoreCase(nameToSearch)) {
                vendorsTransactions.add(vendorsTransaction);
            }

        //if more than one transaction found then output in descending order
        if (vendorsTransactions.size() > 1) {

            Collections.sort(vendorsTransactions, (a, b) -> {
                LocalDateTime dateTimeA = LocalDateTime.of(LocalDate.parse(a.getDate()), LocalTime.parse(a.getTime()));
                LocalDateTime dateTimeB = LocalDateTime.of(LocalDate.parse(b.getDate()), LocalTime.parse(b.getTime()));

                return dateTimeB.compareTo(dateTimeA);

            });
        }

        if (vendorsTransactions.isEmpty()) {
            System.out.println("No Transactions Found, Going Back To The Reports Menu...");
        } else {

            for (Transaction transaction : vendorsTransactions) {
                System.out.println(transaction);

            }
        }
    }


    public static void displayAddedSearchFeatures() {

        int subMenuCommand;

        do {
            System.out.println("Welcome To The Added Search Features Menu!");
            System.out.println("Please Enter Your Command");
            System.out.println("1) Search By Description");
            System.out.println("2) Search By Amount");
            System.out.println("3) Go Back To Reports Menu");
            System.out.println("0) Exit Application");
            System.out.print("Command: ");

            try {
                subMenuCommand = commandScanner.nextInt();
            } catch (InputMismatchException ime) {
                ime.printStackTrace();
                subMenuCommand = 0;
            }

            switch (subMenuCommand) {
                case 1:
                    searchByDescription();
                    break;

                case 2:
                    searchByAmountRange();

                case 3:
                    System.out.println("Go Back To Reports Menu");
                    break;

                case 0:
                    System.out.println("Exiting Application, Goodbye...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Command Not Found. Please Try Again.");
            }
        } while (true);
    }

    public static void searchByDescription() {

        System.out.println("Search By Description");

        System.out.println("Please Provide The Description Name That You're Looking For");
        System.out.print("Description: ");
        String typedDescription = inputScanner.nextLine();

        ArrayList<Transaction> descriptionTransactions = new ArrayList<>();

        for (Transaction descriptionTransaction : allTransactions)

            if (descriptionTransaction.getDescription().equalsIgnoreCase(typedDescription)) {

                descriptionTransactions.add(descriptionTransaction);
            }

        if (descriptionTransactions.size() > 1) {

            Collections.sort(descriptionTransactions, (a, b) -> {
                LocalDateTime dateTimeA = LocalDateTime.of(LocalDate.parse(a.getDate()), LocalTime.parse(a.getTime()));
                LocalDateTime dateTimeB = LocalDateTime.of(LocalDate.parse(b.getDate()), LocalTime.parse(b.getTime()));

                return dateTimeB.compareTo(dateTimeA);

            });
        }

        if (descriptionTransactions.isEmpty()) {
            System.out.println("No Transactions Found, Going Back To The Reports Menu...");
        } else {

            for (Transaction transaction : descriptionTransactions) {
                System.out.println(transaction);

            }
        }

    }

    public static void searchByAmountRange() {

        System.out.println("Search By Amount Range");

        System.out.println("Please Enter The Lowest Amount");
        System.out.print("Min Amount: ");
        float minAmount = inputScanner.nextFloat();

        System.out.println("Please Enter The Largest Amount");
        System.out.print("Max Amount: ");
        float maxAmount = inputScanner.nextFloat();

        for (int i = 0; i < allTransactions.size(); i++) {
            Transaction rangeAmountTransactions = allTransactions.get(i);
            float transactionAmount = rangeAmountTransactions.getAmount();

            if (transactionAmount >= minAmount && transactionAmount <= maxAmount) {
                System.out.println(rangeAmountTransactions);
            }
        }


    }

}