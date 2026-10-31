import java.util.ArrayList;

/**
 * =============================================================================
 * Driver Class: BankSystemDemo
 *
 * Core Concepts Demonstrated:
 * 1. Inheritance: SavingsAccount extends BankAccount.
 * 2. Method Overriding: Subclass customizes displayAccountDetails() and calculateInterest().
 * 3. Polymorphism: BankAccount references holding SavingsAccount instances with dynamic dispatch.
 * 4. Abstract Classes: BankAccount defines abstract calculateInterest() contract.
 * 5. Interfaces: Transaction interface contracts deposit() and withdraw().
 * 6. Encapsulation: Private state manipulated exclusively via getters/setters.
 * 7. Collections: ArrayList<BankAccount> manages multiple accounts dynamically.
 * 8. Exception Handling: Robust try-catch blocks intercept invalid transactions.
 * =============================================================================
 */
public class BankSystemDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("       MINI PROJECT: BANK ACCOUNT SYSTEM (OOP PART 2)            ");
        System.out.println("=================================================================\n");

        // ---------------------------------------------------------------------
        // 1. Collections & Polymorphism:
        // Storing multiple SavingsAccount objects inside an ArrayList<BankAccount>
        // ---------------------------------------------------------------------
        ArrayList<BankAccount> accountsList = new ArrayList<>();

        // Creating accounts polymorphically
        BankAccount acc1 = new SavingsAccount("SA-101", 10000.0, 4.0);
        BankAccount acc2 = new SavingsAccount("SA-102", 25000.0, 5.5);
        BankAccount acc3 = new SavingsAccount("SA-103", 5000.0, 3.5);

        accountsList.add(acc1);
        accountsList.add(acc2);
        accountsList.add(acc3);

        // ---------------------------------------------------------------------
        // 2. Displaying All Accounts via Polymorphic Iteration
        // ---------------------------------------------------------------------
        System.out.println("--- 1. Bank Accounts Overview (Polymorphic Iteration) ---\n");
        int count = 1;
        for (BankAccount account : accountsList) {
            System.out.println("Account Record #" + count++ + ":");
            // Dynamic Method Dispatch: Executes SavingsAccount.displayAccountDetails() at runtime
            account.displayAccountDetails();
            System.out.println("--------------------------------------------------");
        }

        // ---------------------------------------------------------------------
        // 3. Transaction Operations with Exception Handling (try-catch)
        // ---------------------------------------------------------------------
        System.out.println("\n--- 2. Executing Transactions with Exception Handling ---");

        // Transaction Test 1: Valid Deposit
        System.out.println("\n[Test 1] Valid Deposit Operation:");
        try {
            if (acc1 instanceof Transaction) {
                Transaction txn = (Transaction) acc1;
                txn.deposit(2500.0);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }

        // Transaction Test 2: Valid Withdrawal
        System.out.println("\n[Test 2] Valid Withdrawal Operation:");
        try {
            if (acc1 instanceof Transaction) {
                Transaction txn = (Transaction) acc1;
                txn.withdraw(4000.0);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }

        // Transaction Test 3: Invalid Deposit (Negative Amount - Exception Handled)
        System.out.println("\n[Test 3] Invalid Deposit (Negative Amount):");
        try {
            if (acc2 instanceof Transaction) {
                Transaction txn = (Transaction) acc2;
                System.out.println("Attempting: deposit(-500.0)...");
                txn.deposit(-500.0); // Will throw IllegalArgumentException
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }

        // Transaction Test 4: Invalid Withdrawal (Overdraft / Insufficient Funds - Exception Handled)
        System.out.println("\n[Test 4] Invalid Withdrawal (Insufficient Funds):");
        try {
            if (acc3 instanceof Transaction) {
                Transaction txn = (Transaction) acc3;
                System.out.printf("Current balance: $%.2f | Attempting: withdraw($15000.0)...\n", acc3.getBalance());
                txn.withdraw(15000.0); // Will throw IllegalArgumentException
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }

        // ---------------------------------------------------------------------
        // 4. Final Account Summary After Transactions
        // ---------------------------------------------------------------------
        System.out.println("\n=================================================================");
        System.out.println("                 FINAL ACCOUNT BALANCES & INTEREST               ");
        System.out.println("=================================================================");
        for (BankAccount account : accountsList) {
            System.out.printf("Account: %-8s | Balance: $%-10.2f | Annual Interest: $%.2f\n",
                    account.getAccountNumber(), account.getBalance(), account.calculateInterest());
        }

        System.out.println("\nBank Account System execution completed successfully!");
    }
}
