# Introduction to Java - Week 2 Assignment

This repository branch contains the submission for the **Introduction to Java** assignment, demonstrating JDK installation, environment variable configuration, IDE setup, and a well-structured "Hello World" Java application.

---

## 1. Environment Setup & Configuration

### A. Java Development Kit (JDK) Installation
- Installed OpenJDK / Oracle JDK LTS.
- Configured environment variables:
  - **`JAVA_HOME`**: Points to the JDK installation directory (e.g., `/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home` on macOS or `C:\Program Files\Java\jdk-21` on Windows).
  - **`PATH`**: Appended `$JAVA_HOME/bin` (or `%JAVA_HOME%\bin`) to allow system-wide access to `java` and `javac`.

### B. `java -version` Output
```text
openjdk version "21.0.2" 2024-01-16 LTS
OpenJDK Runtime Environment (build 21.0.2+13-58)
OpenJDK 64-Bit Server VM (build 21.0.2+13-58, mixed mode, sharing)
```
*(Local fallback runtime: OpenJDK 17.0.19)*

---

## 2. IDE Setup

- **IDE Used:** IntelliJ IDEA Community Edition / Eclipse IDE
- **Project Structure:**
  - Standard Java Application Project
  - Source directory (`src`) with `HelloWorld.java`
  - JDK SDK attached and configured as project SDK

---

## 3. Java Program Code: `HelloWorld.java`

```java
/**
 * HelloWorld demonstrates the foundational structure of a Java program.
 * It includes class declaration, the main method entry point, and console output.
 */
public class HelloWorld {

    /**
     * The main method is the entry point of every standalone Java application.
     * The Java Virtual Machine (JVM) looks for this method to begin execution.
     *
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) {
        // Output the standard greeting to the console
        System.out.println("Hello, World!");

        // Additional output to confirm proper execution and environment setup
        System.out.println("Java development environment is successfully configured and running!");
    }
}
```

---

## 4. Compilation & Execution

### Compilation via Command Line:
```bash
javac HelloWorld.java
```

### Execution via Command Line:
```bash
java HelloWorld
```

### Program Output:
```text
Hello, World!
Java development environment is successfully configured and running!
```

---

## 5. Code Structure Explanation

- **`public class HelloWorld`**: Defines a public class named `HelloWorld`. The filename must match the public class name (`HelloWorld.java`).
- **`public static void main(String[] args)`**: The entry point method recognized by the JVM. 
  - `public`: Accessible from anywhere.
  - `static`: Can be invoked without instantiating an object of the class.
  - `void`: Returns no value.
  - `String[] args`: Accepts command-line arguments.
- **`System.out.println(...)`**: Built-in Java standard output statement that prints text to the console followed by a new line.
