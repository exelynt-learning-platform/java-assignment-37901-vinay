/**
 * =============================================================================
 * SynchronizationDemo demonstrates Thread Synchronization & Thread Safety:
 *
 * 1. THE PROBLEM: RACE CONDITIONS
 *    - Occurs when two or more threads concurrently read and write shared data
 *      without coordination.
 *    - Operations like `balance += amount` are NOT atomic; they involve:
 *        1) Read current value into CPU register.
 *        2) Add amount.
 *        3) Write new value back to memory.
 *    - If a thread switch occurs in between steps 1 and 3, updates are overwritten
 *      and data is corrupted (lost updates / inconsistent state).
 *
 * 2. THE SOLUTION: SYNCHRONIZATION
 *    - Java provides the 'synchronized' keyword built around Intrinsic Locks (Monitors).
 *    - Synchronized Method: Locks the entire method on the invoking instance (this).
 *    - Synchronized Block: Locks a specific critical section on a specified monitor object.
 *    - When Thread A acquires the lock, any other thread attempting to enter any
 *      synchronized block/method protected by the same lock is BLOCKED until Thread A
 *      releases the lock upon exit.
 * =============================================================================
 */

// Shared resource class representing a bank account
class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    /**
     * SYNCHRONIZED METHOD:
     * Guarantees that only ONE thread can execute deposit() on this instance at a time.
     * Automatically acquires the monitor lock on 'this' when entering, releases on exit.
     */
    public synchronized void deposit(double amount) {
        String threadName = Thread.currentThread().getName();
        System.out.printf("[%s] Initiating deposit of $%.2f | Current: $%.2f\n",
                threadName, amount, this.balance);

        double newBalance = this.balance + amount;

        // Introduce simulated processing delay to highlight race condition vulnerability if unlocked
        try {
            Thread.sleep(50);
        } catch (InterruptedException ignored) {}

        this.balance = newBalance;
        System.out.printf("[%s] Deposit Complete: +$%.2f | Updated Balance: $%.2f\n",
                threadName, amount, this.balance);
    }

    /**
     * SYNCHRONIZED BLOCK:
     * Synchronizes only the critical section of the method using 'synchronized(this)'.
     * More flexible and allows non-critical code to execute outside the lock.
     */
    public void withdraw(double amount) {
        String threadName = Thread.currentThread().getName();

        // Non-critical code (runs concurrently outside the synchronized block)
        System.out.printf("[%s] Requesting withdrawal of $%.2f...\n", threadName, amount);

        // CRITICAL SECTION: Protected by synchronized block on 'this'
        synchronized (this) {
            if (this.balance >= amount) {
                System.out.printf("[%s] [LOCK ACQUIRED] Processing withdrawal of $%.2f (Balance: $%.2f)\n",
                        threadName, amount, this.balance);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {}

                this.balance -= amount;
                System.out.printf("[%s] [LOCK RELEASED] Withdrawal of $%.2f Successful | Remaining Balance: $%.2f\n",
                        threadName, amount, this.balance);
            } else {
                System.out.printf("[%s] [DENIED] Insufficient funds! Requested: $%.2f, Available: $%.2f\n",
                        threadName, amount, this.balance);
            }
        }
    }

    public synchronized double getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }
}

public class SynchronizationDemo {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("   THREAD SYNCHRONIZATION & SHARED RESOURCE SAFETY DEMO          ");
        System.out.println("=================================================================\n");

        // 1. Instantiate a SINGLE shared BankAccount resource
        double initialBalance = 1000.0;
        BankAccount sharedAccount = new BankAccount("SAV-990022", initialBalance);
        System.out.printf("Initialized shared account %s with starting balance: $%.2f\n\n",
                sharedAccount.getAccountNumber(), initialBalance);

        // 2. Define concurrent deposit tasks
        Runnable depositTask = () -> {
            for (int i = 0; i < 3; i++) {
                sharedAccount.deposit(200.0);
            }
        };

        // 3. Define concurrent withdrawal tasks
        Runnable withdrawalTask = () -> {
            for (int i = 0; i < 3; i++) {
                sharedAccount.withdraw(150.0);
            }
        };

        // 4. Create multiple concurrent threads accessing the SAME sharedAccount object
        Thread user1 = new Thread(depositTask, "User-Thread-1 (Depositor)");
        Thread user2 = new Thread(withdrawalTask, "User-Thread-2 (Withdrawer)");
        Thread user3 = new Thread(depositTask, "User-Thread-3 (Depositor)");

        System.out.println("Starting 3 concurrent threads accessing the shared BankAccount...\n");
        user1.start();
        user2.start();
        user3.start();

        // 5. Wait for all threads to conclude
        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting for transactions.");
        }

        // 6. Verify final balance calculation
        // Calculation: 1000 + (3 * 200) - (3 * 150) + (3 * 200) = 1000 + 600 - 450 + 600 = 1750.0
        double expectedBalance = 1000.0 + (3 * 200.0) - (3 * 150.0) + (3 * 200.0);
        double actualBalance = sharedAccount.getBalance();

        System.out.println("\n=================================================================");
        System.out.println("                  SYNCHRONIZATION AUDIT REPORT                   ");
        System.out.println("=================================================================");
        System.out.printf("Starting Balance : $%.2f\n", initialBalance);
        System.out.printf("Expected Balance : $%.2f\n", expectedBalance);
        System.out.printf("Actual Balance   : $%.2f\n", actualBalance);
        System.out.println("Thread Safety Status: " +
                (expectedBalance == actualBalance ? "VERIFIED (ZERO RACE CONDITIONS)" : "FAILED (DATA CORRUPTED)"));
        System.out.println("=================================================================");
        System.out.println("Thread synchronization test successfully completed!");
    }
}
