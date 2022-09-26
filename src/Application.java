import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("*******WELCOME TO ING BANK*******");
        System.out.println("___________________________________________________________");
        System.out.println("---------ACCOUNT CREATION---------");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        String accountNumber = Utils.getRandomNumber(8);
        System.out.print("Enter your pin number: ");
        String pin = sc.nextLine();

        System.out.println("Congratulations! Account created successfully......\n");

        ATM atm = new ATM(name, accountNumber, pin);

        while (true) {
            System.out.print("Do you want to do any transaction?(y/n):");
            String transaction = sc.nextLine();
            if (transaction.equals("y")) {
                atm.transaction();
            } else if (transaction.equals("n")) {
                System.out.print("| Thanks for choosing us as your bank |\n" +
                        "| Visit us again!                     |");
                break;
            } else {
                System.out.println("Wrong command!  Enter 'y' for YES and 'n' for NO.");
            }

        }
    }
}
