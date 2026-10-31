import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * =============================================================================
 * InventoryManagement demonstrates practical usage of Java Collections & Generics:
 *
 * 1. Type-Safe Generic Maps:
 *    - Map<Integer, String> productNames: Maps Product ID (Integer) to Product Name (String).
 *    - Map<Integer, Integer> productStock: Maps Product ID (Integer) to Available Quantity (Integer).
 *
 * 2. Type-Safe Generic List:
 *    - List<Integer> productCatalogIds: Tracks the ordered sequence of registered product IDs.
 *
 * 3. Core Operations:
 *    - Add new products to the inventory.
 *    - Update quantities for existing products.
 *    - Query and display complete inventory records in formatted tabular style.
 * =============================================================================
 */
public class InventoryManagement {

    // Generic collections storing inventory state
    private final Map<Integer, String> productNames = new HashMap<>();
    private final Map<Integer, Integer> productStock = new HashMap<>();
    private final List<Integer> productCatalogIds = new ArrayList<>();

    /**
     * Adds a new product to the inventory.
     *
     * @param productId   Unique product identifier
     * @param productName Name/description of the product
     * @param quantity    Initial stock quantity
     */
    public void addProduct(int productId, String productName, int quantity) {
        if (productNames.containsKey(productId)) {
            System.out.println("Warning: Product ID " + productId + " already exists. Use update instead.");
            return;
        }

        // Store in generic Maps and List
        productNames.put(productId, productName);
        productStock.put(productId, Math.max(0, quantity));
        productCatalogIds.add(productId);

        System.out.printf("[Added]: ID %d | %-20s | Initial Stock: %d units\n",
                productId, productName, quantity);
    }

    /**
     * Updates the stock quantity of an existing product.
     *
     * @param productId   Product identifier to update
     * @param newQuantity Updated stock count
     */
    public void updateQuantity(int productId, int newQuantity) {
        if (!productNames.containsKey(productId)) {
            System.out.println("Error: Cannot update. Product ID " + productId + " not found in inventory!");
            return;
        }

        int previousStock = productStock.get(productId);
        int updatedStock = Math.max(0, newQuantity);
        productStock.put(productId, updatedStock);

        System.out.printf("[Updated]: ID %d (%s) | Previous: %d units -> New Stock: %d units\n",
                productId, productNames.get(productId), previousStock, updatedStock);
    }

    /**
     * Displays all inventory items in a clean, formatted table.
     * Iterates over the generic List and retrieves data from the generic Maps.
     */
    public void displayInventory() {
        System.out.println("\n=================================================================");
        System.out.println("                   CURRENT INVENTORY STATUS                      ");
        System.out.println("=================================================================");
        System.out.printf("%-12s | %-25s | %-12s | %-12s\n", "Product ID", "Product Name", "Quantity", "Stock Status");
        System.out.println("-----------------------------------------------------------------");

        if (productCatalogIds.isEmpty()) {
            System.out.println("No products currently in inventory.");
        } else {
            // Iterating through the List of registered product IDs
            for (Integer id : productCatalogIds) {
                String name = productNames.get(id);
                Integer quantity = productStock.get(id);

                String status;
                if (quantity == 0) {
                    status = "OUT OF STOCK";
                } else if (quantity < 10) {
                    status = "LOW STOCK";
                } else {
                    status = "IN STOCK";
                }

                System.out.printf("%-12d | %-25s | %-12d | %-12s\n", id, name, quantity, status);
            }
        }
        System.out.println("=================================================================");
        System.out.println("Total Distinct Products in Catalog: " + productCatalogIds.size() + "\n");
    }

    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================================");
        System.out.println("       INVENTORY MANAGEMENT SYSTEM (COLLECTIONS & GENERICS)      ");
        System.out.println("=================================================================\n");

        // 1. Initial Product Population
        System.out.println("--- 1. Initializing Inventory with Products ---");
        inventory.addProduct(101, "Dell UltraSharp Monitor", 25);
        inventory.addProduct(102, "Logitech MX Master Mouse", 50);
        inventory.addProduct(103, "Keychron K2 Keyboard", 8); // Low stock
        inventory.addProduct(104, "Apple MacBook Pro M3", 15);
        inventory.addProduct(105, "Sony WH-1000XM5", 0); // Out of stock

        // 2. Display Initial Inventory
        inventory.displayInventory();

        // 3. Performing Stock Updates
        System.out.println("--- 2. Performing Inventory Stock Updates ---");
        inventory.updateQuantity(103, 30); // Restocked Keychron keyboard
        inventory.updateQuantity(105, 12); // Restocked Sony headphones
        inventory.updateQuantity(101, 20); // Decreased monitor stock after sales
        inventory.updateQuantity(999, 10); // Attempting to update non-existent product

        // 4. Interactive Addition from User Input
        System.out.println("\n--- 3. Interactive Product Addition ---");
        try {
            System.out.print("Enter New Product ID (integer): ");
            int newId = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            System.out.print("Enter Product Name: ");
            String newName = scanner.nextLine();

            System.out.print("Enter Product Quantity: ");
            int newQty = scanner.nextInt();

            inventory.addProduct(newId, newName, newQty);
        } catch (Exception e) {
            System.out.println("Notice: Skipping interactive addition due to input format.");
        }

        // 5. Final Inventory Display
        System.out.println("\n--- 4. Final Inventory Report ---");
        inventory.displayInventory();

        scanner.close();
        System.out.println("Inventory Management System completed successfully!");
    }
}
