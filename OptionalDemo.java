import java.util.Optional;

/**
 * OptionalDemo demonstrates how the java.util.Optional class safely handles
 * nullable values and avoids NullPointerException (NPE) in Java.
 *
 * Why Optional Prevents Null Issues:
 * -----------------------------------
 * 1. Explicit API Contract:
 *    - In traditional Java, returning 'null' to indicate absence of value easily
 *      causes NullPointerException if the caller forgets a defensive check.
 *    - Returning Optional<T> explicitly documents that a value may be absent,
 *      forcing the caller to handle both cases at compile-time.
 *
 * 2. Eliminates Defensive Boilerplate:
 *    - Instead of nested "if (obj != null && obj.getField() != null)" blocks,
 *      Optional provides functional methods like map(), filter(), and flatMap().
 *
 * 3. Graceful Fallbacks:
 *    - Provides built-in safe default strategies such as orElse(), orElseGet(),
 *      and orElseThrow() without risking crashes.
 */
public class OptionalDemo {

    // User model class with potentially nullable fields
    static class User {
        private final int id;
        private final String name;
        private final String email;       // Can be null
        private final String phoneNumber; // Can be null

        public User(int id, String name, String email, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        // Return Optional to explicitly signal nullable field
        public Optional<String> getEmail() {
            return Optional.ofNullable(email);
        }

        // Return Optional to explicitly signal nullable field
        public Optional<String> getPhoneNumber() {
            return Optional.ofNullable(phoneNumber);
        }

        @Override
        public String toString() {
            return String.format("User[ID=%d, Name='%s', Email='%s', Phone='%s']",
                    id, name, email != null ? email : "N/A", phoneNumber != null ? phoneNumber : "N/A");
        }
    }

    // Mock database lookup method returning Optional<User>
    public static Optional<User> findUserById(int id) {
        if (id == 1) {
            return Optional.of(new User(1, "Vinay Madarkhandi", "vinay@example.com", "+1-555-0199"));
        } else if (id == 2) {
            // User with null email and null phone
            return Optional.of(new User(2, "Alice Johnson", null, null));
        } else {
            // User not found in database
            return Optional.empty();
        }
    }

    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("       SAFE NULL HANDLING USING JAVA OPTIONAL CLASS           ");
        System.out.println("==============================================================");

        // -------------------------------------------------------------------------
        // 1. Creating Optional Instances: of(), ofNullable(), empty()
        // -------------------------------------------------------------------------
        System.out.println("\n--- 1. Creating Optional Instances ---");

        // Optional.of() requires a non-null value (throws NPE immediately if value is null)
        Optional<String> nonNullOpt = Optional.of("Antigravity Concurrency");
        System.out.println("Optional.of() value: " + nonNullOpt.get());

        // Optional.ofNullable() safely wraps a value that might be null
        String maybeNull = null;
        Optional<String> nullableOpt = Optional.ofNullable(maybeNull);
        System.out.println("Optional.ofNullable(null) isPresent: " + nullableOpt.isPresent());

        // Optional.empty() represents an explicitly empty optional
        Optional<String> emptyOpt = Optional.empty();
        System.out.println("Optional.empty() isPresent: " + emptyOpt.isPresent());

        // -------------------------------------------------------------------------
        // 2. Checking and Consuming Values: isPresent(), ifPresent()
        // -------------------------------------------------------------------------
        System.out.println("\n--- 2. Checking & Consuming: isPresent() and ifPresent() ---");

        Optional<User> user1Opt = findUserById(1);
        if (user1Opt.isPresent()) {
            System.out.println("User 1 exists: " + user1Opt.get().getName());
        }

        // ifPresent executes Consumer lambda only if value is present (no get() needed)
        user1Opt.ifPresent(u -> System.out.println("  -> Successfully retrieved: " + u));

        // -------------------------------------------------------------------------
        // 3. Fallback Strategies: orElse(), orElseGet(), orElseThrow()
        // -------------------------------------------------------------------------
        System.out.println("\n--- 3. Safe Fallbacks: orElse(), orElseGet(), and orElseThrow() ---");

        Optional<User> user2Opt = findUserById(2); // Exists, but email/phone are null
        Optional<User> missingUserOpt = findUserById(99); // Does not exist (empty)

        // orElse: provides an immediate fallback default object
        User fallbackUser = missingUserOpt.orElse(new User(0, "Guest User", "guest@domain.com", "000-000-0000"));
        System.out.println("orElse() result for missing user: " + fallbackUser);

        // orElseGet: computes the fallback lazily via Supplier (only executed if empty)
        User lazyUser = missingUserOpt.orElseGet(() -> {
            System.out.println("  [Supplier] Generating default anonymous user lazily...");
            return new User(-1, "Anonymous", "anon@domain.com", "N/A");
        });
        System.out.println("orElseGet() result: " + lazyUser);

        // Handling nullable fields inside user2
        user2Opt.ifPresent(user -> {
            String email = user.getEmail().orElse("no-email-provided@domain.com");
            String phone = user.getPhoneNumber().orElse("No Phone Registered");
            System.out.println("\nUser 2 Fallbacks:");
            System.out.println("  User Name : " + user.getName());
            System.out.println("  Email     : " + email);
            System.out.println("  Phone     : " + phone);
        });

        // orElseThrow: safely throws an exception if value is absent
        try {
            System.out.println("\nAttempting to retrieve non-existent user with orElseThrow():");
            missingUserOpt.orElseThrow(() -> new IllegalArgumentException("User ID 99 was not found in database!"));
        } catch (IllegalArgumentException ex) {
            System.out.println("  Caught expected exception: " + ex.getMessage());
        }

        // -------------------------------------------------------------------------
        // 4. Transforming and Filtering: map(), filter()
        // -------------------------------------------------------------------------
        System.out.println("\n--- 4. Transforming & Filtering with map() and filter() ---");

        // map() applies transformation if value is present; returns empty Optional otherwise
        Optional<String> upperEmailOpt = user1Opt
                .flatMap(User::getEmail)
                .map(String::toUpperCase);

        System.out.println("Uppercase Email (via map): " + upperEmailOpt.orElse("NONE"));

        // filter() tests a condition; keeps value if Predicate matches, returns empty otherwise
        Optional<User> adultUserOpt = user1Opt
                .filter(u -> u.getId() > 0);
        System.out.println("User matching ID > 0 filter: " + adultUserOpt.map(User::getName).orElse("No match"));

        Optional<User> invalidIdOpt = user1Opt
                .filter(u -> u.getId() > 100);
        System.out.println("User matching ID > 100 filter: " + invalidIdOpt.map(User::getName).orElse("No match"));

        System.out.println("\n==============================================================");
        System.out.println("OptionalDemo completed successfully without any null crashes!");
        System.out.println("==============================================================");
    }
}
