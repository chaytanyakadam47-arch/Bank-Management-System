import java.util.Scanner;

public class User {
    public static void main(String[] args) {
        try {
            BankDAO dao = new BankDAO(); // JDBC-enabled DAO class
            Scanner input = new Scanner(System.in);
            int choice;

            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.print("Enter choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Account No: ");
                    String accNo = input.next();
                    System.out.print("Name: ");
                    String name = input.next();
                    System.out.print("Initial Deposit: ");
                    int deposit = input.nextInt();
                    dao.createAccount(accNo, name, deposit);
                    break;

                case 2:
                    System.out.print("Account No: ");
                    accNo = input.next();
                    System.out.print("Deposit Amount: ");
                    int amount = input.nextInt();
                    dao.deposit(accNo, amount);
                    break;

                case 3:
                    System.out.print("Account No: ");
                    accNo = input.next();
                    System.out.print("Withdrawal Amount: ");
                    int withdrawAmount = input.nextInt();
                    dao.withdraw(accNo, withdrawAmount);
                    break;

                default:
                    System.out.println("Please enter a valid choice.");
                    break;
            }

            dao.close(); // Close DB connection
            System.out.println("Thank you! You have exited successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}