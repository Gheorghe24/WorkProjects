import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Utils {
    private static final Random rnd = new Random();

    public static @NotNull String getRandomNumber(int digCount) {
        StringBuilder sb = new StringBuilder(digCount);
        for (int i = 0; i < digCount; i++)
            sb.append((char) ('0' + rnd.nextInt(10)));
        return sb.toString();
    }

    public static @NotNull String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static @NotNull List<String> makeListTransaction(String message, int amount) {
        List<String> informations = new ArrayList<>();

        informations.add(message);
        informations.add(Integer.toString(amount));
        informations.add(getTime());

        return informations;
    }

    public static void printOptions() {
        System.out.print("You can choose one of this commands: \n" +
                "1 - See your account details\n" +
                "2 - Check your balance\n" +
                "3 - Deposit in account\n" +
                "4 - Withdraw\n" +
                "5 - Track your withdrawals\n" +
                "6 - Exit\n"
        );
    }

    public static void writeInFile(@NotNull List<List<String>> list) {
        try {
            FileWriter myWriter = new FileWriter("receipt.txt");
            myWriter.write("");
            for (List<String> innerList : list) {
                String line = String.join("\n", innerList);
                myWriter.write(line);
            }
            myWriter.close();
            System.out.println("Take your receipt for today's transactions .");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
