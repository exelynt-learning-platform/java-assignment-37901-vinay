# Logging and Debugging Best Practices

This project demonstrates robust application event logging in Java using the standard `java.util.logging` framework.

---

## Logging Levels Used

- **`INFO`**: Key application lifecycle milestones (startup, major processing steps, shutdown).
- **`CONFIG`**: Startup parameters and environment configuration (timeout, pool size, active profiles).
- **`FINE`**: Granular debug tracking detailing method entries and exits for troubleshooting.
- **`WARNING`**: Recoverable conditions (e.g. missing optional fields, default fallback activations).
- **`SEVERE`**: Critical errors and exceptions (e.g. arithmetic failures, network timeouts) logged with complete stack trace context.

---

## Sample Log Output

```text
[2026-10-31 15:46:04.140] [INFO   ] [LoggingDemo] Application starting up: Student & Employee Analytics Engine v3.2.0
[2026-10-31 15:46:04.143] [CONFIG ] [LoggingDemo] Configuration Loaded: maxConnections=50, timeoutSeconds=30, activeProfile=Production
[2026-10-31 15:46:04.143] [INFO   ] [LoggingDemo] Beginning batch processing of student records...
[2026-10-31 15:46:04.143] [FINE   ] [LoggingDemo] Entering processStudentRecord() for ID: STU-101
[2026-10-31 15:46:04.144] [INFO   ] [LoggingDemo] Successfully validated and saved student record: STU-101
[2026-10-31 15:46:04.144] [FINE   ] [LoggingDemo] Exiting processStudentRecord() for ID: STU-101
[2026-10-31 15:46:04.144] [FINE   ] [LoggingDemo] Entering processStudentRecord() for ID: STU-102
[2026-10-31 15:46:04.144] [INFO   ] [LoggingDemo] Successfully validated and saved student record: STU-102
[2026-10-31 15:46:04.144] [FINE   ] [LoggingDemo] Exiting processStudentRecord() for ID: STU-102
[2026-10-31 15:46:04.144] [FINE   ] [LoggingDemo] Entering processStudentRecord() for ID: STU-MISSING-METADATA
[2026-10-31 15:46:04.148] [WARNING] [LoggingDemo] Record STU-MISSING-METADATA is missing email address; applying default fallback placeholder.
[2026-10-31 15:46:04.148] [FINE   ] [LoggingDemo] Exiting processStudentRecord() for ID: STU-MISSING-METADATA
[2026-10-31 15:46:04.148] [FINE   ] [LoggingDemo] Entering processStudentRecord() for ID: STU-104
[2026-10-31 15:46:04.148] [INFO   ] [LoggingDemo] Successfully validated and saved student record: STU-104
[2026-10-31 15:46:04.148] [FINE   ] [LoggingDemo] Exiting processStudentRecord() for ID: STU-104
[2026-10-31 15:46:04.148] [INFO   ] [LoggingDemo] Batch processing stage completed successfully.
[2026-10-31 15:46:04.148] [INFO   ] [LoggingDemo] Attempting external system synchronization...
[2026-10-31 15:46:04.148] [SEVERE ] [LoggingDemo] Failed to compute financial tuition balance: Division by zero
  Exception: java.lang.ArithmeticException: / by zero
    at LoggingDemo.simulateDatabaseFailure(LoggingDemo.java:85)
[2026-10-31 15:46:04.148] [SEVERE ] [LoggingDemo] External payment gateway communication failure
  Exception: java.io.IOException: Connection refused by external payment gateway (192.168.1.100:8443)
    at LoggingDemo.simulateDatabaseFailure(LoggingDemo.java:93)
[2026-10-31 15:46:04.149] [INFO   ] [LoggingDemo] Application shutdown initiated. Releasing memory caches and connection pools.
[2026-10-31 15:46:04.149] [INFO   ] [LoggingDemo] Application terminated cleanly.
```
