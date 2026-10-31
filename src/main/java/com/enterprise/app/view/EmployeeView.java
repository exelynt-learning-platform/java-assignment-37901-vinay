package com.enterprise.app.view;

import com.enterprise.app.model.Employee;

import java.util.List;

/**
 * =============================================================================
 *                               VIEW LAYER
 * =============================================================================
 * Responsible strictly for rendering reports and data representations to users.
 */
public class EmployeeView {

    public void displayEmployeeCard(Employee emp) {
        System.out.println("  +--------------------------------------------------------------------------+");
        System.out.printf("  | ID: %-4d | Name: %-22s | Role: %-20s |%n",
                emp.getId(), emp.getName(), emp.getRole());
        System.out.printf("  | Department: %-18s | Salary: $%,-24.2f |%n",
                emp.getDepartment(), emp.getSalary());
        System.out.println("  +--------------------------------------------------------------------------+");
    }

    public void displayEmployeeDirectory(List<Employee> employees) {
        System.out.println("\n  ==========================================================================");
        System.out.println("                    ENTERPRISE EMPLOYEE DIRECTORY                         ");
        System.out.println("  ==========================================================================");
        System.out.println("  +-----+----------------------+---------------+----------------------+---------------+");
        System.out.println("  | ID  | Name                 | Department    | Role                 | Salary ($)    |");
        System.out.println("  +-----+----------------------+---------------+----------------------+---------------+");

        for (Employee emp : employees) {
            System.out.printf("  | %-3d | %-20s | %-13s | %-20s | %13.2f |%n",
                    emp.getId(), emp.getName(), emp.getDepartment(), emp.getRole(), emp.getSalary());
        }
        System.out.println("  +-----+----------------------+---------------+----------------------+---------------+");
        System.out.printf("  Total Employees Displayed: %d%n%n", employees.size());
    }

    public void displayStatus(String message) {
        System.out.println("  [View Notice] " + message);
    }
}
