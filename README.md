# Employee Management System (JDBC Database Integration)

A Java console-based Employee Management System that connects to a relational database (SQLite or MySQL) using JDBC and performs complete CRUD (Create, Read, Update, Delete) operations with secure `PreparedStatement` queries and robust exception handling.

---

## Database Schema

The database table `employees` is created using the following definition (committed in `schema.sql`):

```sql
CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DOUBLE NOT NULL
);
```

---

## JDBC Workflow Explanation

1. **Driver Loading & Registration**:
   - `Class.forName("org.sqlite.JDBC")` or automatic discovery via JDBC 4.0 Service Provider Interface (`META-INF/services/java.sql.Driver`).

2. **Connection Management**:
   - Established using `DriverManager.getConnection(url, user, password)`.
   - Security best practice: Zero hardcoded credentials in source code. Credentials are read from environment variables (`DB_URL`, `DB_USER`, `DB_PASSWORD`) or git-ignored configuration files, defaulting safely to local SQLite.
   - Resource lifecycle: All database connections are managed with `try-with-resources` ensuring automatic closing.

3. **Secure Query Execution (`PreparedStatement`)**:
   - Uses pre-compiled templates with `?` placeholders for parameterized data binding.
   - Prevents SQL Injection attacks and allows execution plan caching.

4. **Result Processing (`ResultSet`)**:
   - Navigates record sets row-by-row using `rs.next()`.
   - Extracts typed values using `rs.getInt()`, `rs.getString()`, and `rs.getDouble()`.

5. **Exception Handling**:
   - Captures `SQLException` and inspects `getMessage()`, `getSQLState()`, and vendor `getErrorCode()`.

---

## Sample Program Run

```text
==========================================================================
       EMPLOYEE MANAGEMENT SYSTEM - CONSOLE-BASED JDBC CRUD               
==========================================================================
Connected to database successfully: SQLite

[INFO] Non-interactive mode detected: Executing automated CRUD demonstration workflow...

--- 1. ADD EMPLOYEES (CREATE) ---
  [SUCCESS] Added: ID=101, Name='Vinay Madarkhandi', Dept='Engineering', Salary=$88,000.00
  [SUCCESS] Added: ID=102, Name='Alice Johnson', Dept='Marketing', Salary=$67,000.00
  [SUCCESS] Added: ID=103, Name='Bob Smith', Dept='Engineering', Salary=$74,000.00
  [SUCCESS] Added: ID=104, Name='Charlie Brown', Dept='Finance', Salary=$71,000.00

--- 2. VIEW ALL EMPLOYEES (READ) ---
  +-----+------------------------------+---------------+---------------+
  | ID  | Name                         | Department    | Salary ($)    |
  +-----+------------------------------+---------------+---------------+
  | 101 | Vinay Madarkhandi            | Engineering   |      88000.00 |
  | 102 | Alice Johnson                | Marketing     |      67000.00 |
  | 103 | Bob Smith                    | Engineering   |      74000.00 |
  | 104 | Charlie Brown                | Finance       |      71000.00 |
  +-----+------------------------------+---------------+---------------+

--- 3. UPDATE EMPLOYEE DETAILS (UPDATE) ---
Updating Employee ID 101: Promoting to Lead Engineer with salary $98,000.00
  [SUCCESS] Updated Employee ID 101 successfully!

Verifying updated record:
  +-----+------------------------------+---------------+---------------+
  | ID  | Name                         | Department    | Salary ($)    |
  +-----+------------------------------+---------------+---------------+
  | 101 | Vinay Madarkhandi (Lead)     | Engineering   |      98000.00 |
  | 102 | Alice Johnson                | Marketing     |      67000.00 |
  | 103 | Bob Smith                    | Engineering   |      74000.00 |
  | 104 | Charlie Brown                | Finance       |      71000.00 |
  +-----+------------------------------+---------------+---------------+

--- 4. DELETE EMPLOYEE (DELETE) ---
Removing Employee ID 103 (Bob Smith)...
  [SUCCESS] Deleted Employee ID 103 successfully!

Final Database State after Deletion:
  +-----+------------------------------+---------------+---------------+
  | ID  | Name                         | Department    | Salary ($)    |
  +-----+------------------------------+---------------+---------------+
  | 101 | Vinay Madarkhandi (Lead)     | Engineering   |      98000.00 |
  | 102 | Alice Johnson                | Marketing     |      67000.00 |
  | 104 | Charlie Brown                | Finance       |      71000.00 |
  +-----+------------------------------+---------------+---------------+

==========================================================================
Employee Management System terminated cleanly. Connection closed.
==========================================================================
```
