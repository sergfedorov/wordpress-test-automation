package tmp;

import java.util.Scanner;

public class Bank {

    public static void main(String[] args) {

        System.out.println("Enter the balance of bank account (integer number, negative or positive): ");
        Scanner scanner = new Scanner(System.in);
        String inputFilePathByUser = scanner.nextLine();

        String regex = "-?[0-9]+";

        if (inputFilePathByUser.matches(regex)) {
            int res = Integer.valueOf(inputFilePathByUser);
            whatIsMyRate(res);
        } else {
            System.out.println("Input validation is failed!");
            return;
        }
    }

    public static void whatIsMyRate(int balance){
        if(balance >= 0 && balance < 100)
            System.out.println("The balance is " + balance + ". Interest rate of your bank account is 3%");
        else if(balance >= 100 && balance < 1000)
            System.out.println("The balance is " + balance + ". Interest rate of your bank account is 5%");
        else if (balance >= 1000)
            System.out.println("The balance is " + balance + ". Interest rate of your bank account is 7%");
        else
            System.out.println("The balance is " + balance + ". Interest rate of your bank account is 0%");
    }
}
