public class Bank {
    private Account account;

    public void createAccount(String accountNumber, String name, int deposit) {
        account = new Account(accountNumber, name, deposit);
        System.out.println("Account created successfully.");
    }

    public void deposit(int amount) {
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("No account found.");
        }
    }

    public void withdraw(int amount) {
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrawal successful.");
                System.out.println("Current balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("No account found.");
        }
    }

    public Account getAccount() {
        return account;
    }
}
