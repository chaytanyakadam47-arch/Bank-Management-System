import java.sql.*;

public class BankDAO {
    private Connection conn;

    public BankDAO() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Use your DB driver
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "kadam@1234");
    }

    public void createAccount(String accNo, String name, int deposit) throws SQLException {
        String sql = "INSERT INTO accounts (account_number, name, balance) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accNo);
            stmt.setString(2, name);
            stmt.setInt(3, deposit);
            stmt.executeUpdate();
            System.out.println("Account created successfully.");
        }
    }

    public void deposit(String accNo, int amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, amount);
            stmt.setString(2, accNo);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Deposit successful.");
            } else {
                System.out.println("Account not found.");
            }
        }
    }

    public void withdraw(String accNo, int amount) throws SQLException {
        String checkSql = "SELECT balance FROM accounts WHERE account_number = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, accNo);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                int currentBalance = rs.getInt("balance");
                if (amount > currentBalance) {
                    System.out.println("Insufficient balance.");
                } else {
                    String updateSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, amount);
                        updateStmt.setString(2, accNo);
                        updateStmt.executeUpdate();
                        System.out.println("Withdrawal successful.");
                    }
                }
            } else {
                System.out.println("Account not found.");
            }
        }
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}