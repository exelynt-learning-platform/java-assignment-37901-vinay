import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DesignPatternDemo demonstrates core Gang of Four (GoF) software design patterns:
 * 1. Singleton Pattern: Ensures a class has only one global instance and provides a global point of access.
 * 2. Factory Pattern: Defines an interface for creating objects, delegating instantiation to subclasses.
 * 3. Observer Pattern: Establishes a 1-to-many dependency so when one object changes state, all dependents are notified.
 */
public class DesignPatternDemo {

    // =========================================================================
    // PATTERN 1: SINGLETON PATTERN (Configuration Management)
    // =========================================================================
    /**
     * ConfigurationManager manages global application properties.
     * Uses the Bill Pugh Singleton approach (Initialization-on-demand holder idiom),
     * which is thread-safe, lazy-loaded, and requires no explicit synchronization overhead.
     */
    static class ConfigurationManager {

        private final Map<String, String> settings = new HashMap<>();

        // Private constructor prevents direct instantiation from outside
        private ConfigurationManager() {
            System.out.println("  [Singleton] Initializing ConfigurationManager instance...");
            // Default configuration settings
            settings.put("app.name", "Enterprise Student & Employee Portal");
            settings.put("app.version", "2.5.0");
            settings.put("environment", "Production");
            settings.put("db.timeout", "30");
        }

        // Static inner helper class responsible for holding the Singleton instance
        private static class InstanceHolder {
            private static final ConfigurationManager INSTANCE = new ConfigurationManager();
        }

        // Global access point to retrieve the single instance
        public static ConfigurationManager getInstance() {
            return InstanceHolder.INSTANCE;
        }

        public String getProperty(String key) {
            return settings.get(key);
        }

        public void setProperty(String key, String value) {
            settings.put(key, value);
        }

        public void printAllSettings() {
            System.out.println("  Current Configuration State:");
            settings.forEach((k, v) -> System.out.printf("    - %-15s : %s%n", k, v));
        }
    }

    // =========================================================================
    // PATTERN 2: FACTORY PATTERN (Object Creation)
    // =========================================================================
    /**
     * Notification Interface - Common abstraction for notification delivery.
     */
    interface Notification {
        void notifyUser(String recipient, String message);
        String getChannelType();
    }

    // Concrete Product A: Email Notification
    static class EmailNotification implements Notification {
        @Override
        public void notifyUser(String recipient, String message) {
            System.out.printf("  [EMAIL -> %s] Subj: Alert | Body: %s%n", recipient, message);
        }

        @Override
        public String getChannelType() {
            return "EMAIL";
        }
    }

    // Concrete Product B: SMS Notification
    static class SMSNotification implements Notification {
        @Override
        public void notifyUser(String recipient, String message) {
            System.out.printf("  [SMS -> %s] Text: %s%n", recipient, message);
        }

        @Override
        public String getChannelType() {
            return "SMS";
        }
    }

    // Concrete Product C: Push Notification
    static class PushNotification implements Notification {
        @Override
        public void notifyUser(String recipient, String message) {
            System.out.printf("  [PUSH -> %s] App Notification: %s%n", recipient, message);
        }

        @Override
        public String getChannelType() {
            return "PUSH";
        }
    }

    /**
     * NotificationFactory encapsulates object creation logic.
     * The client application does not instantiate concrete classes directly (no 'new EmailNotification()'),
     * adhering to the Dependency Inversion Principle.
     */
    static class NotificationFactory {
        public static Notification createNotification(String channel) {
            if (channel == null || channel.trim().isEmpty()) {
                throw new IllegalArgumentException("Channel type cannot be null or empty.");
            }

            switch (channel.trim().toUpperCase()) {
                case "EMAIL":
                    return new EmailNotification();
                case "SMS":
                    return new SMSNotification();
                case "PUSH":
                    return new PushNotification();
                default:
                    throw new IllegalArgumentException("Unknown notification channel: " + channel);
            }
        }
    }

    // =========================================================================
    // PATTERN 3: OBSERVER PATTERN (Event Notification)
    // =========================================================================
    /**
     * Observer Interface: Defines the update contract for listeners.
     */
    interface EventObserver {
        void onEvent(String eventName, String eventData);
    }

    // Concrete Observer 1: Audit Logger
    static class AuditLogObserver implements EventObserver {
        @Override
        public void onEvent(String eventName, String eventData) {
            System.out.printf("  [Audit Log Observer] Recorded event '%s' -> %s%n", eventName, eventData);
        }
    }

    // Concrete Observer 2: Security Alert Monitor
    static class SecurityAlertObserver implements EventObserver {
        @Override
        public void onEvent(String eventName, String eventData) {
            if (eventName.contains("CRITICAL") || eventName.contains("SECURITY")) {
                System.out.printf("  [Security Alert Observer] !!! High-priority security alert: '%s' -> %s%n",
                        eventName, eventData);
            }
        }
    }

    /**
     * Subject / Publisher: Maintains a list of observers and notifies them of events.
     */
    static class EventPublisher {
        private final List<EventObserver> observers = new ArrayList<>();

        public void subscribe(EventObserver observer) {
            observers.add(observer);
        }

        public void unsubscribe(EventObserver observer) {
            observers.remove(observer);
        }

        public void notifyObservers(String eventName, String eventData) {
            for (EventObserver observer : observers) {
                observer.onEvent(eventName, eventData);
            }
        }
    }

    // =========================================================================
    // MAIN DEMONSTRATION METHOD
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("       JAVA DESIGN PATTERNS: SINGLETON, FACTORY & OBSERVER                ");
        System.out.println("==========================================================================");

        // ---------------------------------------------------------------------
        // 1. DEMONSTRATING SINGLETON PATTERN
        // ---------------------------------------------------------------------
        System.out.println("\n--- 1. SINGLETON PATTERN: Configuration Management ---");
        System.out.println("Requesting ConfigurationManager instance #1:");
        ConfigurationManager config1 = ConfigurationManager.getInstance();

        System.out.println("Requesting ConfigurationManager instance #2:");
        ConfigurationManager config2 = ConfigurationManager.getInstance();

        // Mutate setting via config1
        config1.setProperty("environment", "Staging-US-East");

        // Verify that config2 reflects the mutation because both reference the same object
        System.out.println("\nVerifying Singleton Identity:");
        System.out.println("  Config 1 Hashcode : " + System.identityHashCode(config1));
        System.out.println("  Config 2 Hashcode : " + System.identityHashCode(config2));
        System.out.println("  config1 == config2? " + (config1 == config2));
        System.out.println("  Environment read from config2: " + config2.getProperty("environment"));
        config2.printAllSettings();

        // ---------------------------------------------------------------------
        // 2. DEMONSTRATING FACTORY PATTERN
        // ---------------------------------------------------------------------
        System.out.println("\n--- 2. FACTORY PATTERN: Notification Creation ---");
        System.out.println("Creating notifications through NotificationFactory:");

        Notification email = NotificationFactory.createNotification("EMAIL");
        Notification sms = NotificationFactory.createNotification("SMS");
        Notification push = NotificationFactory.createNotification("PUSH");

        email.notifyUser("vinay@example.com", "Your semester grade report is now ready.");
        sms.notifyUser("+1-555-0199", "Your OTP verification code is 482910.");
        push.notifyUser("user_dev_37901", "New message received from instructor.");

        // ---------------------------------------------------------------------
        // 3. DEMONSTRATING OBSERVER PATTERN
        // ---------------------------------------------------------------------
        System.out.println("\n--- 3. OBSERVER PATTERN: Event Notification ---");
        EventPublisher publisher = new EventPublisher();

        AuditLogObserver auditLog = new AuditLogObserver();
        SecurityAlertObserver securityMonitor = new SecurityAlertObserver();

        publisher.subscribe(auditLog);
        publisher.subscribe(securityMonitor);

        System.out.println("Publishing standard event (USER_LOGIN):");
        publisher.notifyObservers("USER_LOGIN", "User 'Vinay' logged in from 192.168.1.15");

        System.out.println("\nPublishing security event (CRITICAL_SECURITY_BREACH_ATTEMPT):");
        publisher.notifyObservers("CRITICAL_SECURITY_BREACH_ATTEMPT", "3 consecutive failed admin logins detected");

        System.out.println("\n==========================================================================");
        System.out.println("Design patterns demonstration completed successfully!");
        System.out.println("==========================================================================");
    }
}
