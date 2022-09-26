
import java.util.*;

public class ATM {
    private final String name;
    private final String accountNumber;
    private final String pin;
    private int balance;
    private List<List<String>> actions = new ArrayList<>();

    public ATM(String name, String accountNumber, String pin) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    public void showAccountDetails() {
        System.out.println("*******ACCOUNT DETAILS*******");
        System.out.println("Your account name: " + this.name);
        System.out.println("Your account number: " + this.accountNumber);
        System.out.println("Your balance: " + this.balance);
    }

    public void checkBalance() {
        System.out.println("Current Time: " + Utils.getTime());
        System.out.println("Your balance: " + this.balance);
    }

    public void deposit(String message, int amount) {
        actions.add(Utils.makeListTransaction(message, amount));
        this.balance += amount;
    }

    public void withdraw(String message, int amount) {
        if (this.balance - amount >= 0) {
            actions.add(Utils.makeListTransaction(message, amount));
            this.balance -= amount;
        } else {
            System.out.println("Sorry, you have insufficient balance");
        }
    }

    public void trackWithdrawals() {
        actions.forEach(innerList -> {
            String line = String.join("\n", innerList);
            System.out.println(line);
        });
    }

    public void transaction() {
        System.out.print("Enter your pin number: ");
        Scanner sc = new Scanner(System.in);

        if (!this.pin.equals(sc.nextLine())) {
            System.out.println("Sorry, you entered the pin inccorectly");
            return;
        }

        Utils.printOptions();
        while (true) {
            System.out.print("Choose one of this numbers: ");
            int nrCommand = Integer.parseInt(sc.nextLine());
            int actualAmount = 0;
            if (nrCommand >= 1 && nrCommand <= 6) {
                switch (nrCommand) {
                    case 1:
                        this.showAccountDetails();
                        break;
                    case 2:
                        this.checkBalance();
                        break;
                    case 3:
                        try {
                            System.out.print("How much you want to add: ");
                            actualAmount = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.print("Input an integer value: ");
                        }
                        System.out.print("Enter your transaction message: ");
                        String message = sc.nextLine();
                        deposit(message, actualAmount);
                        break;
                    case 4:
                        try {
                            System.out.print("How much you want to withdraw: ");
                            actualAmount = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.print("Input an integer value: ");
                        }
                        System.out.print("Enter your transaction message: ");
                        String mess = sc.nextLine();
                        withdraw(mess, actualAmount);
                        break;
                    case 5:
                        trackWithdrawals();
                        break;
                    case 6:
                        Utils.writeInFile(actions);
                        return;
                }
            } else {
                System.out.println("Wrong command!  Enter one of the available numbers.\n");
            }
        }
    }

}