/**
 * =============================================================================
 * Abstract Class: BankAccount
 *
 * Concepts Demonstrated:
 * - Abstract Class: Cannot be instantiated directly. Serves as a base blueprint.
 * - Encapsulation: All fields are private, accessible only through public
 *   getters and validated setters.
 * - Abstract Method: Declares calculateInterest() without an implementation,
 *   mandating concrete subclasses to provide business-specific calculation logic.
 * =============================================================================
 */
public abstract class BankAccount {

    // Encapsulation: Private instance fields
    private String accountNumber;
    private double balance;

    /**
     * Parameterized Constructor:
     * Initializes a BankAccount instance.
     *
     * @param accountNumber Unique account identifier
     * @param initialBalance Initial balance amount
     */
    public BankAccount(String accountNumber, double initialBalance) {
        setAccountNumber(accountNumber);
        setBalance(initialBalance);
    }

    // --- Public Getters and Setters (Encapsulation) ---

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (accountNumber != null && !accountNumber.trim().isEmpty()) {
            this.accountNumber = accountNumber.trim();
        } else {
            this.accountNumber = "UNASSIGNED";
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        if (balance >= 0.0) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("Balance cannot be initialized to a negative amount: $" + balance);
        }
    }

    /**
     * Abstract Method:
     * Must be implemented by every concrete subclass.
     *
     * @return double calculated interest amount
     */
    public abstract double calculateInterest();

    /**
     * Concrete Method:
     * Provides common account display logic to be reused and overridden.
     */
    public void displayAccountDetails() {
        System.out.println("Account Number : " + this.accountNumber);
        System.out.printf("Current Balance: $%.2f\n", this.balance);
    }
}
