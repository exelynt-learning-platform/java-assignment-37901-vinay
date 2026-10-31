/**
 * =============================================================================
 * Subclass: SavingsAccount extends BankAccount implements Transaction
 *
 * Concepts Demonstrated:
 * - Inheritance: Inherits common fields and methods from abstract BankAccount.
 * - Interface Implementation: Implements Transaction interface contracts.
 * - Method Overriding: Overrides displayAccountDetails() and calculateInterest().
 * - 'super' Keyword: Invokes parent constructor super() and parent method super.displayAccountDetails().
 * - Exception Handling Integration: Throws IllegalArgumentException for invalid transactions.
 * =============================================================================
 */
public class SavingsAccount extends BankAccount implements Transaction {

    // Encapsulation: Private field specific to SavingsAccount
    private double interestRate; // Annual percentage (e.g. 4.5 for 4.5%)

    /**
     * Parameterized Constructor:
     * Uses 'super' keyword to initialize inherited BankAccount fields.
     */
    public SavingsAccount(String accountNumber, double initialBalance, double interestRate) {
        super(accountNumber, initialBalance); // Invokes BankAccount(accountNumber, balance)
        setInterestRate(interestRate);
    }

    // --- Getter and Setter for interestRate ---

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate >= 0.0) {
            this.interestRate = interestRate;
        } else {
            throw new IllegalArgumentException("Interest rate cannot be negative: " + interestRate + "%");
        }
    }

    /**
     * Implementation of BankAccount's abstract method:
     * Calculates annual simple interest: (balance * interestRate) / 100.
     */
    @Override
    public double calculateInterest() {
        return (getBalance() * this.interestRate) / 100.0;
    }

    /**
     * Implementation of Transaction.deposit():
     * Validates deposit amount, throwing an exception if invalid.
     */
    @Override
    public void deposit(double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Deposit error: Deposit amount must be positive. Received: $" + amount);
        }
        double newBalance = getBalance() + amount;
        setBalance(newBalance);
        System.out.printf("[Deposit Success] +$%.2f deposited to %s | New Balance: $%.2f\n",
                amount, getAccountNumber(), getBalance());
    }

    /**
     * Implementation of Transaction.withdraw():
     * Validates withdrawal amount and checks for insufficient funds.
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Withdrawal error: Withdrawal amount must be positive. Received: $" + amount);
        }
        if (amount > getBalance()) {
            throw new IllegalArgumentException(String.format(
                    "Withdrawal error: Insufficient funds in %s. Requested: $%.2f, Available: $%.2f",
                    getAccountNumber(), amount, getBalance()));
        }
        double newBalance = getBalance() - amount;
        setBalance(newBalance);
        System.out.printf("[Withdrawal Success] -$%.2f withdrawn from %s | Remaining Balance: $%.2f\n",
                amount, getAccountNumber(), getBalance());
    }

    /**
     * Overridden method displayAccountDetails():
     * Uses 'super.displayAccountDetails()' to print base details, then adds interest info.
     */
    @Override
    public void displayAccountDetails() {
        System.out.println("--- Account Type: Savings Account ---");
        // Reusing parent class implementation via 'super' keyword
        super.displayAccountDetails();
        System.out.printf("Interest Rate  : %.2f%%\n", this.interestRate);
        System.out.printf("Annual Interest: $%.2f\n", calculateInterest());
    }
}
