/**
 * =============================================================================
 * Interface 1: Transaction
 * Defines contract for transactional banking operations.
 * Methods in interfaces are implicitly 'public abstract'.
 * =============================================================================
 */
interface Transaction {
    void deposit(double amount);
    void withdraw(double amount);
}

/**
 * =============================================================================
 * Interface 2: Interestable
 * Defines contract for interest-bearing accounts.
 * =============================================================================
 */
interface Interestable {
    double calculateInterest();
}

/**
 * =============================================================================
 * Implementing Class: SavingsAccount implements Transaction, Interestable
 *
 * Demonstrates Multiple Inheritance via Interfaces:
 * - Java does not allow a class to extend multiple classes (to avoid the "Diamond Problem").
 * - However, a class CAN implement multiple interfaces simultaneously!
 * - SavingsAccount inherits behavior contracts from BOTH Transaction and Interestable.
 * =============================================================================
 */
class SavingsAccount implements Transaction, Interestable {
    private String accountNumber;
    private double balance;
    private double annualInterestRate; // e.g., 5.0 for 5%

    // Constructor
    public SavingsAccount(String accountNumber, double initialBalance, double annualInterestRate) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * Implementation of Transaction.deposit()
     */
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.printf("Deposited: $%.2f | New Balance: $%.2f\n", amount, this.balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    /**
     * Implementation of Transaction.withdraw()
     */
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.printf("Withdrawn: $%.2f | Remaining Balance: $%.2f\n", amount, this.balance);
        } else if (amount > this.balance) {
            System.out.println("Transaction Failed: Insufficient funds.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    /**
     * Implementation of Interestable.calculateInterest()
     */
    @Override
    public double calculateInterest() {
        return (this.balance * this.annualInterestRate) / 100.0;
    }

    /**
     * Displays complete account status.
     */
    public void displayAccountDetails() {
        System.out.println("Account Number : " + this.accountNumber);
        System.out.printf("Current Balance: $%.2f\n", this.balance);
        System.out.printf("Interest Rate  : %.2f%%\n", this.annualInterestRate);
        System.out.printf("Annual Interest: $%.2f\n", calculateInterest());
    }

    public double getBalance() {
        return this.balance;
    }
}

/**
 * =============================================================================
 * Driver Class: InterfaceDemo
 * Demonstrates multiple inheritance by invoking all interface methods on
 * concrete objects and interface reference types.
 * =============================================================================
 */
public class InterfaceDemo {

    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("   JAVA INTERFACES & MULTIPLE INHERITANCE DEMONSTRATION       ");
        System.out.println("==============================================================\n");

        // 1. Direct object instantiation of SavingsAccount
        System.out.println("--- 1. Creating SavingsAccount (implements Transaction & Interestable) ---");
        SavingsAccount myAccount = new SavingsAccount("SAV-990011", 5000.0, 4.5);
        myAccount.displayAccountDetails();

        // 2. Calling Transaction interface methods
        System.out.println("\n--- 2. Executing Transaction Methods ---");
        myAccount.deposit(1500.0);
        myAccount.withdraw(2000.0);
        myAccount.withdraw(10000.0); // Testing overdraft handling

        // 3. Calling Interestable interface method
        System.out.println("\n--- 3. Executing Interestable Method ---");
        double interest = myAccount.calculateInterest();
        System.out.printf("Calculated interest on current balance: $%.2f\n", interest);

        // 4. Demonstrating Interface Reference Polymorphism
        System.out.println("\n--- 4. Interface Reference Polymorphism ---");

        // Using Transaction reference (only deposit and withdraw are directly accessible)
        Transaction txnRef = myAccount;
        System.out.print("Invoking deposit via Transaction reference: ");
        txnRef.deposit(500.0);

        // Using Interestable reference (only calculateInterest is directly accessible)
        Interestable interestRef = myAccount;
        System.out.printf("Invoking calculateInterest via Interestable reference: $%.2f\n", interestRef.calculateInterest());

        System.out.println("\n--- Final Account Status ---");
        myAccount.displayAccountDetails();

        System.out.println("\nMultiple inheritance via interfaces demonstrated successfully!");
    }
}
