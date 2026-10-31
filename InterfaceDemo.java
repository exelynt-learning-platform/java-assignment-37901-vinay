/**
 * InterfaceDemo demonstrates Java 8 interface enhancements:
 * 1. Default methods: Concrete methods defined within an interface using the 'default' keyword.
 *    They allow adding new methods to existing interfaces without breaking backward compatibility
 *    for existing implementing classes.
 * 2. Static methods: Utility methods defined within an interface using the 'static' keyword.
 *    They can be called directly using the interface name without requiring an implementing object.
 */
public class InterfaceDemo {

    // -------------------------------------------------------------------------
    // 1. Interface definition with Abstract, Default, and Static methods
    // -------------------------------------------------------------------------
    interface Vehicle {

        // Abstract method: Must be implemented by any concrete class
        void start();

        // Abstract method: Must be implemented by any concrete class
        void stop();

        // Default method 1: Provides a standard default implementation
        // Implementing classes inherit this without having to implement it
        default void playRadio() {
            System.out.println("  [Vehicle Default] FM Radio is playing music smoothly.");
        }

        // Default method 2: Can be optionally overridden by implementing classes
        default void applyEmergencyBrakes() {
            System.out.println("  [Vehicle Default] Standard hydraulic emergency brakes applied!");
        }

        // Static method 1: Helper/Utility method belonging to the Vehicle interface
        // Called via Vehicle.isElectric(...)
        static boolean isElectric(String fuelType) {
            return fuelType != null && fuelType.equalsIgnoreCase("ELECTRIC");
        }

        // Static method 2: General interface guidelines/version
        static void displayVehicleGuidelines() {
            System.out.println("  [Vehicle Static Guideline] All vehicles must comply with safety standards.");
        }
    }

    // -------------------------------------------------------------------------
    // 2. Class implementing the interface (inheriting default methods)
    // -------------------------------------------------------------------------
    static class Car implements Vehicle {
        private final String model;
        private final String fuelType;

        public Car(String model, String fuelType) {
            this.model = model;
            this.fuelType = fuelType;
        }

        @Override
        public void start() {
            System.out.println("Car [" + model + "] engine started. Fuel type: " + fuelType);
        }

        @Override
        public void stop() {
            System.out.println("Car [" + model + "] engine turned off.");
        }

        // Inherits playRadio() directly from Vehicle default method
    }

    // -------------------------------------------------------------------------
    // 3. Another class implementing the interface and overriding a default method
    // -------------------------------------------------------------------------
    static class ElectricCar implements Vehicle {
        private final String model;

        public ElectricCar(String model) {
            this.model = model;
        }

        @Override
        public void start() {
            System.out.println("Electric Car [" + model + "] silent EV propulsion activated.");
        }

        @Override
        public void stop() {
            System.out.println("Electric Car [" + model + "] EV power isolated.");
        }

        // Overriding the default method to provide custom EV regenerative braking logic
        @Override
        public void applyEmergencyBrakes() {
            System.out.println("  [ElectricCar Overridden Default] Regenerative anti-lock EV braking applied instantly!");
        }
    }

    // -------------------------------------------------------------------------
    // Main Method to execute and demonstrate calling default and static methods
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("     DEFAULT AND STATIC METHODS IN INTERFACES (JAVA 8+)       ");
        System.out.println("==============================================================");

        // --- Demonstrating Static Methods on Interface ---
        System.out.println("\n--- 1. Calling Interface Static Methods Directly ---");
        System.out.println("Calling Vehicle.displayVehicleGuidelines():");
        Vehicle.displayVehicleGuidelines();

        System.out.println("\nCalling Vehicle.isElectric(\"Electric\"): " + Vehicle.isElectric("Electric"));
        System.out.println("Calling Vehicle.isElectric(\"Petrol\"):   " + Vehicle.isElectric("Petrol"));

        // --- Demonstrating Concrete Class using Inherited Default Method ---
        System.out.println("\n--- 2. Concrete Class (Car) Using Inherited Default Method ---");
        Car standardCar = new Car("Sedan LX", "Petrol");
        standardCar.start();

        System.out.println("\nCalling default method playRadio() inherited from Vehicle interface:");
        standardCar.playRadio();

        System.out.println("\nCalling default method applyEmergencyBrakes() inherited from Vehicle:");
        standardCar.applyEmergencyBrakes();
        standardCar.stop();

        // --- Demonstrating Concrete Class Overriding Default Method ---
        System.out.println("\n--- 3. Concrete Class (ElectricCar) Overriding Default Method ---");
        ElectricCar ev = new ElectricCar("Model Alpha");
        ev.start();

        System.out.println("\nCalling default method playRadio() (inherited):");
        ev.playRadio();

        System.out.println("\nCalling overridden default method applyEmergencyBrakes():");
        ev.applyEmergencyBrakes();
        ev.stop();

        System.out.println("\n==============================================================");
        System.out.println("Interface default and static methods demo completed successfully!");
        System.out.println("==============================================================");
    }
}
