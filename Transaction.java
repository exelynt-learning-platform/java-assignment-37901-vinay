/**
 * =============================================================================
 * Interface: Transaction
 *
 * Concepts Demonstrated:
 * - Interface: An abstract type that specifies a contract of methods that
 *   implementing classes must define.
 * - Methods in an interface are implicitly 'public' and 'abstract'.
 * =============================================================================
 */
public interface Transaction {

    /**
     * Deposits a positive monetary amount into the account.
     *
     * @param amount Deposit amount
     * @throws IllegalArgumentException if amount is negative or zero
     */
    void deposit(double amount);

    /**
     * Withdraws a positive monetary amount from the account.
     *
     * @param amount Withdrawal amount
     * @throws IllegalArgumentException if amount is negative or exceeds available balance
     */
    void withdraw(double amount);
}
