# Understanding Java Program Compilation and Execution

This assignment demonstrates how to compile and run a Java program from the command line without screenshots, as requested.

## Objective

The goal is to:
- open a terminal or command prompt
- navigate to the Java program folder
- compile the program using `javac`
- run the program using `java`
- verify the output is correct

## Step 1: Create a Java file

Create a file named `CompileRunDemo.java` with the following content:

```java
public class CompileRunDemo {
    public static void main(String[] args) {
        // Print a message to the console.
        System.out.println("Java compilation and execution successful!");

        // Print another output to confirm the program runs correctly.
        System.out.println("This program was compiled using javac and executed using java.");
    }
}
```

## Step 2: Open terminal/command prompt

Open the terminal on Windows, macOS, or Linux.

## Step 3: Navigate to the program folder

Example:

```bash
cd path/to/project-folder
```

## Step 4: Compile the Java file

Use the Java compiler:

```bash
javac CompileRunDemo.java
```

If the compilation is successful, a new file named `CompileRunDemo.class` will be created.

## Step 5: Run the program

Use the Java runtime:

```bash
java CompileRunDemo
```

Expected output:

```text
Java compilation and execution successful!
This program was compiled using javac and executed using java.
```

## Why this is important

This exercise shows the standard Java development process:
- write source code
- compile it into bytecode
- run it on the Java Virtual Machine (JVM)

This is the basic workflow used in Java development.

## Final note

This assignment confirms that the Java program has been successfully compiled and executed using the command line. The program works correctly when the compiler and runtime are properly configured.

This task has been completed in the repository for the branch:

`understanding-java-program-compilation-execution-week2-2373`
