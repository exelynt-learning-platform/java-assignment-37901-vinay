# EnterpriseJavaApp - Advanced Java Architecture & Best Practices

An enterprise-ready, modular Java application demonstrating architectural patterns, Model-View-Controller (MVC) separation, Gang of Four (GoF) design patterns, structured logging, and JUnit 5 unit testing.

---

## 1. Chosen Design Patterns

### A. Singleton Pattern (`AppConfig`)
- **Purpose**: Manages centralized, global configuration parameters (environment, version, active currency, timeouts).
- **Implementation**: Utilizes the **Bill Pugh Initialization-on-demand holder idiom** via `private static class Holder`. This guarantees thread-safety, lazy loading, and zero synchronization overhead without explicit locking.

### B. Factory Pattern (`EmployeeFactory`)
- **Purpose**: Encapsulates entity instantiation logic.
- **Implementation**: Consumers request an `Employee` based on functional tier (`ENGINEER`, `MANAGER`, `ANALYST`, `DIRECTOR`) without being tightly coupled to internal constructor signatures or default compensation formulas.

---

## 2. Model-View-Controller (MVC) Flow

The application enforces a strict **Separation of Concerns (SoC)** across discrete layers:

1. **Model (`Employee`)**:
   - Represents the core business domain entity. Contains entity attributes, accessors, and mutators with zero dependency on UI or controllers.

2. **View (`EmployeeView`)**:
   - Pure presentation layer. Formats and renders individual employee profile cards and full organizational directory tables to standard output.

3. **Service Layer (`EmployeeService`)**:
   - Encapsulates business validation rules (e.g. duplicate ID detection, promotion logic, payroll aggregation) and integrates application event logging.

4. **Controller (`EmployeeController`)**:
   - Intermediary layer that coordinates user actions, calls the `EmployeeFactory` and `EmployeeService`, and instructs the `EmployeeView` to refresh.

---

## 3. Logging Strategy

- Implemented using the standard `java.util.logging.Logger` API without legacy third-party dependencies.
- **Log Levels Applied**:
  - `INFO`: Records successful employee onboarding, promotions, and major lifecycle events.
  - `FINE`: Granular data retrieval tracing.
  - `WARNING`: Handled duplicate ID registration attempts and non-fatal anomalies.
  - `SEVERE`: Records failed promotion lookups for non-existent employee records.

---

## 4. Testing Approach & JUnit 5 Test Run Summary

The test suite in `src/test/java/com/enterprise/app/service/EmployeeServiceTest.java` validates:
- Singleton identity reference verification (`assertSame`).
- Factory tier creation validation.
- Employee registration and retrieval.
- Expected exception validation using `assertThrows` for duplicate IDs and invalid promotions.
- Total payroll calculation across multiple entities.

### Maven Test Run Summary (`mvn clean test`)

```text
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.enterprise.app.service.EmployeeServiceTest
Oct 31, 2026 3:56:03 PM com.enterprise.app.service.EmployeeService addEmployee
INFO: Registered new employee: Diana Prince (ID: 301)
Oct 31, 2026 3:56:03 PM com.enterprise.app.service.EmployeeService applyPromotion
INFO: Applying promotion request for Employee ID: 301
Oct 31, 2026 3:56:03 PM com.enterprise.app.service.EmployeeService applyPromotion
INFO: Promotion applied: Diana Prince salary adjusted $95000.00 -> $110000.00
Oct 31, 2026 3:56:03 PM com.enterprise.app.service.EmployeeService addEmployee
INFO: Registered new employee: Emp1 (ID: 1)
Oct 31, 2026 3:56:03 PM com.enterprise.app.service.EmployeeService addEmployee
INFO: Registered new employee: Emp2 (ID: 2)
Oct 31, 2026 3:56:03 PM com.enterprise.app.service.EmployeeService addEmployee
INFO: Registered new employee: Vinay Madarkhandi (ID: 101)
Oct 31, 2026 3:56:03 PM com.enterprise.app.service.EmployeeService addEmployee
INFO: Registered new employee: Bob Smith (ID: 201)
Oct 31, 2026 3:56:03 PM com.enterprise.app.service.EmployeeService addEmployee
WARNING: Conflict: Employee ID 201 already exists in repository.
Oct 31, 2026 3:56:03 PM com.enterprise.app.service.EmployeeService applyPromotion
INFO: Applying promotion request for Employee ID: 999
Oct 31, 2026 3:56:03 PM com.enterprise.app.service.EmployeeService applyPromotion
SEVERE: Failed to apply promotion: Employee ID 999 was not found.
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.054 s -- in com.enterprise.app.service.EmployeeServiceTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```
