import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * =============================================================================
 * Comprehensive Inventory Management System
 * Integrates all Week 7 Core Concepts:
 *
 * 1. ARRAY USAGE:
 *    - 1D Array (double[]): Stores fixed unit prices for inventory products.
 *      Ideal for fixed-size numeric collections with rapid index-based lookups.
 *    - 2D Array (int[][]): Represents a multi-dimensional matrix of stock quantities
 *      where rows represent products and columns represent distinct warehouses.
 *
 * 2. COLLECTIONS FRAMEWORK:
 *    - List<String> (ArrayList): Maintains an ordered sequence of product names,
 *      allowing indexed access and duplicate tracking if needed.
 *    - Set<String> (HashSet): Ensures uniqueness among product categories by
 *      automatically rejecting duplicate category entries.
 *    - Map<Integer, String> (HashMap): Associates each unique product ID (Key)
 *      with its product name (Value) for fast O(1) lookups.
 *    - Queue<String> (LinkedList): Simulates an incoming FIFO (First-In-First-Out)
 *      restocking queue for processing shipment batches in chronological order.
 *
 * 3. ITERATION TECHNIQUES:
 *    - Enhanced For-Each Loop: Clean, readable syntax for sequential read traversal.
 *    - java.util.Iterator: Standard traversal providing safe element inspection
 *      and removal capabilities.
 *
 * 4. PURPOSE OF GENERICS (TYPE SAFETY):
 *    - By declaring types like List<String> and Map<Integer, String>, the compiler
 *      enforces strict type checking at compile time.
 *    - Prevents accidental insertion of incompatible types (e.g., adding an integer to
 *      a List of strings), eliminating runtime ClassCastException crashes.
 * =============================================================================
 */
public class InventoryManagement {

    public static void main(String[] args) {
        System.out.println("=================================================================");
        System.out.println("     COMPREHENSIVE INVENTORY MANAGEMENT SYSTEM (WEEK 7)          ");
        System.out.println("=================================================================\n");

        // ---------------------------------------------------------------------
        // 1. ARRAY USAGE
        // ---------------------------------------------------------------------

        // 1A. Single-Dimensional Array: Stores product prices
        // Index mapping: 0 -> Laptop, 1 -> Wireless Mouse, 2 -> Mechanical Keyboard, 3 -> USB-C Hub
        double[] productPrices = { 1299.99, 79.50, 149.00, 45.25 };

        // 1B. Multi-Dimensional Array (2D): Stock across 3 Warehouses (North, South, Central)
        // Rows (4 products) x Columns (3 warehouses)
        int[][] warehouseStock = {
            { 25, 15, 30 },  // Product 0: Laptop
            { 80, 60, 110 }, // Product 1: Wireless Mouse
            { 40, 20, 35 },  // Product 2: Mechanical Keyboard
            { 90, 75, 85 }   // Product 3: USB-C Hub
        };
        String[] warehouseNames = { "Warehouse North", "Warehouse South", "Warehouse Central" };

        // ---------------------------------------------------------------------
        // 2. COLLECTIONS FRAMEWORK WITH TYPE-SAFE GENERICS
        // ---------------------------------------------------------------------

        // 2A. List (ArrayList<String>): Ordered list of product names
        List<String> productList = new ArrayList<>();
        productList.add("Dell XPS Laptop");
        productList.add("Logitech MX Master 3S");
        productList.add("Keychron K2 Wireless Keyboard");
        productList.add("Anker 7-in-1 USB-C Hub");

        // 2B. Set (HashSet<String>): Stores unique product categories
        Set<String> categorySet = new HashSet<>();
        categorySet.add("Computers");
        categorySet.add("Peripherals");
        categorySet.add("Accessories");
        categorySet.add("Peripherals"); // Duplicate: Automatically rejected by HashSet
        categorySet.add("Computers");   // Duplicate: Automatically rejected by HashSet

        // 2C. Map (HashMap<Integer, String>): Product ID to Product Name mapping
        Map<Integer, String> productIdMap = new HashMap<>();
        productIdMap.put(101, productList.get(0));
        productIdMap.put(102, productList.get(1));
        productIdMap.put(103, productList.get(2));
        productIdMap.put(104, productList.get(3));

        // 2D. Queue (LinkedList<String>): FIFO incoming shipment queue
        Queue<String> restockingQueue = new LinkedList<>();
        restockingQueue.offer("Batch #1001: 50 Dell XPS Laptops");
        restockingQueue.offer("Batch #1002: 120 Logitech Mice");
        restockingQueue.offer("Batch #1003: 60 Keychron Keyboards");

        // ---------------------------------------------------------------------
        // 3. OUTPUT & ITERATION TECHNIQUES
        // ---------------------------------------------------------------------

        // OUTPUT 1: Product List via Enhanced For-Each Loop
        System.out.println("--- 1. Registered Product Catalog (Enhanced For-Each Loop) ---");
        int catalogIndex = 1;
        for (String product : productList) {
            System.out.printf("  [%d] %-30s | Base Price: $%.2f\n",
                    catalogIndex, product, productPrices[catalogIndex - 1]);
            catalogIndex++;
        }

        // OUTPUT 2: Unique Categories via Iterator
        System.out.println("\n--- 2. Unique Product Categories (java.util.Iterator) ---");
        Iterator<String> categoryIterator = categorySet.iterator();
        while (categoryIterator.hasNext()) {
            String category = categoryIterator.next();
            System.out.println("  * Category: " + category);
        }

        // OUTPUT 3: Product ID Mapping via Map EntrySet
        System.out.println("\n--- 3. Product ID to Name Mapping (HashMap) ---");
        for (Map.Entry<Integer, String> entry : productIdMap.entrySet()) {
            System.out.printf("  Product ID: %-5d => %s\n", entry.getKey(), entry.getValue());
        }

        // OUTPUT 4: Stock Details across Warehouses (2D Array Nested Loop Traversal)
        System.out.println("\n--- 4. Warehouse Stock Distribution (2D Array Traversal) ---");
        System.out.printf("%-30s | %-16s | %-16s | %-17s | %-10s\n",
                "Product", warehouseNames[0], warehouseNames[1], warehouseNames[2], "Total Units");
        System.out.println("---------------------------------------------------------------------------------------------------");

        int[] totalStockPerProduct = new int[productList.size()];

        for (int i = 0; i < warehouseStock.length; i++) {
            int rowTotal = 0;
            System.out.printf("%-30s | ", productList.get(i));

            for (int j = 0; j < warehouseStock[i].length; j++) {
                int stockCount = warehouseStock[i][j];
                rowTotal += stockCount;
                System.out.printf("%-16d | ", stockCount);
            }
            totalStockPerProduct[i] = rowTotal;
            System.out.printf("%-10d\n", rowTotal);
        }
        System.out.println("---------------------------------------------------------------------------------------------------");

        // OUTPUT 5: Incoming Restocking Queue Processing (FIFO Queue)
        System.out.println("\n--- 5. Incoming Inventory Restocking Queue (Queue.poll FIFO) ---");
        System.out.println("Restocking orders waiting in queue: " + restockingQueue.size());
        while (!restockingQueue.isEmpty()) {
            System.out.println("  [Processing]: " + restockingQueue.poll());
        }
        System.out.println("Queue status: All pending restock shipments processed successfully.");

        // OUTPUT 6: Final Inventory Financial Valuation Summary
        System.out.println("\n=================================================================");
        System.out.println("                 FINAL INVENTORY VALUATION SUMMARY               ");
        System.out.println("=================================================================");
        double grandTotalValuation = 0.0;
        int grandTotalItems = 0;

        for (int i = 0; i < productList.size(); i++) {
            int units = totalStockPerProduct[i];
            double unitPrice = productPrices[i];
            double productValuation = units * unitPrice;

            grandTotalItems += units;
            grandTotalValuation += productValuation;

            System.out.printf("%-30s | %4d units @ $%-8.2f = Total Value: $%,10.2f\n",
                    productList.get(i), units, unitPrice, productValuation);
        }

        System.out.println("-----------------------------------------------------------------");
        System.out.printf("Grand Total Units in Stock      : %d items\n", grandTotalItems);
        System.out.printf("Total Aggregate Inventory Value : $%,.2f\n", grandTotalValuation);
        System.out.println("=================================================================");
        System.out.println("Inventory Management System operations completed successfully!");
    }
}
