package tmp;

public class BankAccountRate {

    public static void main(String[] args) {
        int myBalance = 778787887;
        whatIsMyRate(myBalance);
    }

    public static void whatIsMyRate(int accountBalance){
        if(accountBalance >= 0 && accountBalance < 100)
            System.out.println("The balance is " + accountBalance + ". Interest rate of your bank account is 3%");
        else if(accountBalance >= 100 && accountBalance < 1000)
            System.out.println("The balance is " + accountBalance + ". Interest rate of your bank account is 5%");
        else if (accountBalance >= 1000)
            System.out.println("The balance is " + accountBalance + ". Interest rate of your bank account is 7%");
        else
            System.out.println("The balance is " + accountBalance + ". Interest rate of your bank account is 0%");
    }
}
