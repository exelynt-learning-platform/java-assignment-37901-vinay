/**
 * =============================================================================
 * Class: BankAccount
 * Demonstrates Encapsulation Best Practices in Java:
 * 1. Data Hiding: Fields 'accountNumber' and 'balance' are declared as 'private'
 *    so they cannot be accessed or corrupted directly from outside this class.
 * 2. Controlled Access: Public getters (accessors) provide read-only access.
 * 3. Validation Rules: Public setters (mutators) validate incoming data before
 *    modifying internal state, ensuring business invariants are never violated.
 * =============================================================================
 */
class BankAccount {
    // 1. Private fields (hidden from direct outside access)
    private String accountNumber;
    private double balance;

    // Parameterized constructor utilizing setters to enforce validation from creation
    public BankAccount(String accountNumber, double initialBalance) {
        setAccountNumber(accountNumber);
        setBalance(initialBalance);
    }

    /**
     * Getter for accountNumber.
     * Provides read-only access to the private field.
     */
    public String getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Setter for accountNumber.
     * Enforces validation: Account number must not be null or blank, and must meet minimum length.
     */
    public void setAccountNumber(String accountNumber) {
        if (accountNumber != null && accountNumber.trim().length() >= 5) {
            this.accountNumber = accountNumber.trim();
        } else {
            System.out.println("Validation Error: Account number must be at least 5 characters long.");
        }
    }

    /**
     * Getter for balance.
     * Returns current balance safely.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Setter for balance.
     * Enforces validation: Balance cannot be negative.
     */
    public void setBalance(double balance) {
        if (balance >= 0.0) {
            this.balance = balance;
        } else {
            System.out.printf("Validation Error: Balance cannot be negative ($%.2f rejected). Balance remains: $%.2f\n",
                    balance, this.balance);
        }
    }

    /**
     * Business method: Deposit
     * Encapsulates addition to balance with positive amount check.
     */
    public void deposit(double amount) {
        if (amount > 0.0) {
            this.balance += amount;
            System.out.printf("Successfully deposited: $%.2f | New balance: $%.2f\n", amount, this.balance);
        } else {
            System.out.println("Deposit Error: Deposit amount must be greater than zero.");
        }
    }

    /**
     * Business method: Withdraw
     * Encapsulates withdrawal logic preventing overdraft and invalid values.
     */
    public void withdraw(double amount) {
        if (amount <= 0.0) {
            System.out.println("Withdrawal Error: Withdrawal amount must be greater than zero.");
        } else if (amount > this.balance) {
            System.out.printf("Withdrawal Error: Insufficient funds! Requested: $%.2f, Available: $%.2f\n",
                    amount, this.balance);
        } else {
            this.balance -= amount;
            System.out.printf("Successfully withdrawn: $%.2f | Remaining balance: $%.2f\n", amount, this.balance);
        }
    }

    /**
     * Helper method to print account summary.
     */
    public void displayAccountInfo() {
        System.out.println("Account Number: " + (this.accountNumber != null ? this.accountNumber : "UNASSIGNED"));
        System.out.printf("Current Balance: $%.2f\n", this.balance);
    }
}

/**
 * =============================================================================
 * Driver Class: EncapsulationDemo
 * Demonstrates reading, updating, validating, and protecting encapsulated data.
 * =============================================================================
 */
public class EncapsulationDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      ENCAPSULATION BEST PRACTICES DEMO           ");
        System.out.println("==================================================\n");

        // 1. Instantiate object with valid initial data
        System.out.println("--- 1. Creating BankAccount Object ---");
        BankAccount account = new BankAccount("ACC-98765", 2500.0);
        account.displayAccountInfo();

        // 2. Reading fields using public getters
        System.out.println("\n--- 2. Accessing Fields via Getters ---");
        System.out.println("getAccountNumber() : " + account.getAccountNumber());
        System.out.printf("getBalance()        : $%.2f\n", account.getBalance());

        // 3. Updating fields with valid inputs via setters
        System.out.println("\n--- 3. Updating Balance with Valid Value via Setter ---");
        account.setBalance(3200.50);
        System.out.printf("Updated balance via getBalance(): $%.2f\n", account.getBalance());

        // 4. Testing Validation: Attempting invalid (negative) balance update
        System.out.println("\n--- 4. Testing Negative Balance Validation ---");
        System.out.println("Attempting: account.setBalance(-500.0)...");
        account.setBalance(-500.0); // Validation intercepts and blocks this change
        System.out.printf("Verified Balance remains protected: $%.2f\n", account.getBalance());

        // 5. Testing Validation: Attempting invalid account number
        System.out.println("\n--- 5. Testing Invalid Account Number Validation ---");
        System.out.println("Attempting: account.setAccountNumber(\"AB\")...");
        account.setAccountNumber("AB"); // Too short, rejected by setter validation
        System.out.println("Verified Account Number remains unchanged: " + account.getAccountNumber());

        // 6. Testing encapsulated business methods
        System.out.println("\n--- 6. Testing Encapsulated Deposit & Withdraw Methods ---");
        account.deposit(800.0);
        account.withdraw(1500.0);
        account.withdraw(10000.0); // Triggers insufficient funds check

        System.out.println("\n--- Final Account State ---");
        account.displayAccountInfo();

        System.out.println("\nEncapsulation and data validation demonstrated successfully!");
    }
}
