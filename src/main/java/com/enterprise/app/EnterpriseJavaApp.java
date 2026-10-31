package com.enterprise.app;

import com.enterprise.app.config.AppConfig;
import com.enterprise.app.controller.EmployeeController;
import com.enterprise.app.service.EmployeeService;
import com.enterprise.app.view.EmployeeView;

import java.util.logging.Logger;

/**
 * =============================================================================
 *          ENTERPRISE JAVA APPLICATION (MVC + PATTERNS + LOGGING)
 * =============================================================================
 * Main entrypoint demonstrating:
 * 1. Singleton pattern (AppConfig)
 * 2. Factory pattern (EmployeeFactory)
 * 3. MVC separation (Model, View, Controller, Service)
 * 4. Robust Java logging (java.util.logging)
 */
public class EnterpriseJavaApp {

    private static final Logger logger = Logger.getLogger(EnterpriseJavaApp.class.getName());

    public static void main(String[] args) {
        // Retrieve Singleton Configuration
        AppConfig config = AppConfig.getInstance();
        logger.info("Initializing " + config.getProperty("app.name") + " in " + config.getProperty("app.environment"));

        System.out.println("==========================================================================");
        System.out.printf("       %s (v%s) [%s]%n",
                config.getProperty("app.name"),
                config.getProperty("app.version"),
                config.getProperty("app.environment"));
        System.out.println("==========================================================================");

        // Instantiate MVC Components
        EmployeeService service = new EmployeeService();
        EmployeeView view = new EmployeeView();
        EmployeeController controller = new EmployeeController(service, view);

        // Onboard initial employees using Factory pattern via Controller
        System.out.println("\n[1] Onboarding Employees via Factory Pattern:");
        controller.registerEmployee(101, "Vinay Madarkhandi", "Engineering", "ENGINEER");
        controller.registerEmployee(102, "Alice Johnson",     "Marketing",   "ANALYST");
        controller.registerEmployee(103, "Bob Smith",         "Engineering", "MANAGER");
        controller.registerEmployee(104, "Diana Prince",      "Leadership",  "DIRECTOR");

        // Display Directory through View
        System.out.println("\n[2] Displaying Employee Directory via MVC View:");
        controller.showDirectory();

        // Perform business update (Promotion)
        System.out.println("\n[3] Applying Promotion & Salary Increase:");
        controller.promoteEmployee(101, 15000.0, "Principal Architect");

        // View single updated employee card
        controller.showEmployeeDetails(101);

        logger.info("Enterprise application demonstration finished successfully.");
        System.out.println("\n==========================================================================");
        System.out.println("EnterpriseJavaApp execution completed successfully!");
        System.out.println("==========================================================================");
    }
}
