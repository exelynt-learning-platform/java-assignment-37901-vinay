/**
 * =============================================================================
 * Abstract Class: BankAccount
 * - Cannot be instantiated directly using 'new BankAccount()'.
 * - Serves as an incomplete template providing common state and behavior while
 *   mandating subclasses to implement specific behavior.
 * - Contains both concrete methods (shared logic) and abstract methods (must be overridden).
 * =============================================================================
 */
abstract class BankAccount {
    // Fields common to all bank accounts
    String accountNumber;
    double balance;

    // Parameterized constructor in abstract class (called by subclasses via super())
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    /**
     * Concrete method: Reusable logic common across all account types.
     */
    public void displayAccountInfo() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.printf("Current Balance: $%.2f\n", this.balance);
    }

    /**
     * Abstract method: Declared without a method body (no curly braces).
     * Every concrete subclass MUST provide its own implementation of this method.
     *
     * @return double representing calculated annual interest
     */
    public abstract double calculateInterest();
}

/**
 * =============================================================================
 * Concrete Subclass: SavingsAccount extends BankAccount
 * Implements the abstract method calculateInterest() based on a percentage rate.
 * =============================================================================
 */
class SavingsAccount extends BankAccount {
    double interestRate; // Annual interest percentage (e.g., 4.5 for 4.5%)

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance); // Invokes BankAccount constructor
        this.interestRate = interestRate;
    }

    /**
     * Mandatory implementation of abstract method calculateInterest().
     * Calculates annual simple interest: (balance * interestRate) / 100.
     */
    @Override
    public double calculateInterest() {
        return (this.balance * this.interestRate) / 100.0;
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("--- Savings Account ---");
        super.displayAccountInfo();
        System.out.printf("Interest Rate  : %.2f%%\n", this.interestRate);
        System.out.printf("Annual Interest: $%.2f\n", calculateInterest());
    }
}

/**
 * =============================================================================
 * Concrete Subclass: FixedDepositAccount extends BankAccount
 * Demonstrates another subclass providing distinct calculation logic for the
 * same abstract method.
 * =============================================================================
 */
class FixedDepositAccount extends BankAccount {
    int durationMonths;
    double fdInterestRate;

    public FixedDepositAccount(String accountNumber, double balance, int durationMonths, double fdInterestRate) {
        super(accountNumber, balance);
        this.durationMonths = durationMonths;
        this.fdInterestRate = fdInterestRate;
    }

    @Override
    public double calculateInterest() {
        // Simple term interest: balance * (rate / 100) * (months / 12)
        return this.balance * (this.fdInterestRate / 100.0) * (this.durationMonths / 12.0);
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("--- Fixed Deposit Account ---");
        super.displayAccountInfo();
        System.out.println("Term Duration  : " + this.durationMonths + " months");
        System.out.printf("FD Rate        : %.2f%%\n", this.fdInterestRate);
        System.out.printf("Total Interest : $%.2f\n", calculateInterest());
    }
}

/**
 * =============================================================================
 * Driver Class: AbstractDemo
 * Demonstrates polymorphic invocation using abstract class reference types.
 * =============================================================================
 */
public class AbstractDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      ABSTRACT CLASSES & METHODS IN JAVA          ");
        System.out.println("==================================================\n");

        // 1. Using Abstract Class reference to hold a SavingsAccount instance
        // 'BankAccount account1 = new BankAccount(...)' would cause a compilation error
        // because abstract classes cannot be directly instantiated.
        BankAccount account1 = new SavingsAccount("SA-100452", 25000.0, 4.5);

        // 2. Using Abstract Class reference to hold a FixedDepositAccount instance
        BankAccount account2 = new FixedDepositAccount("FD-998811", 50000.0, 24, 7.25);

        // 3. Invoking methods polymorphically via abstract reference
        account1.displayAccountInfo();
        System.out.printf("Calculated Interest via abstract reference: $%.2f\n\n", account1.calculateInterest());

        account2.displayAccountInfo();
        System.out.printf("Calculated Interest via abstract reference: $%.2f\n\n", account2.calculateInterest());

        // 4. Processing an array of abstract references
        System.out.println("--- Polymorphic Account Interest Summary ---");
        BankAccount[] bankPortfolios = { account1, account2 };
        for (BankAccount acc : bankPortfolios) {
            System.out.printf("Account: %s | Balance: $%.2f | Interest: $%.2f\n",
                    acc.accountNumber, acc.balance, acc.calculateInterest());
        }

        System.out.println("\nAll abstract class operations completed successfully!");
    }
}
