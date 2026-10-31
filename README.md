# Java Editions and Platform Architecture

This document explains the Java platform architecture and the role of JDK, JRE, and JVM in making Java portable and platform-independent.

## 1) Java Architecture Diagram

```text
                         +--------------------------------------------------+
                         |                  User/Developer                  |
                         |    Writes Java source code (.java)               |
                         +---------------------^----------------------------+
                                               |
                                               |
                                               v
                         +--------------------------------------------------+
                         |                    Java Compiler                  |
                         |  Converts .java into bytecode (.class)          |
                         +---------------------^----------------------------+
                                               |
                                               v
                         +--------------------------------------------------+
                         |                     JVM (Java Virtual Machine)    |
                         |  Loads and executes bytecode                    |
                         |  Provides memory management, security, runtime   |
                         +---------------------^----------------------------+
                                               |
                         +---------------------+----------------------------+
                         |                                               |
                         v                                               v
          +----------------------------+               +-------------------------------+
          |     JRE (Java Runtime Env)  |               |         JDK (Java Dev Kit)    |
          | - JVM                       |               | - JDK includes JRE + tools    |
          | - Core libraries            |               | - javac, java, javadoc, etc.|
          | - Supporting classes       |               | - Used for development       |
          +----------------------------+               +-------------------------------+
                                               |
                                               v
                         +--------------------------------------------------+
                         |                Operating System / Hardware       |
                         |  Windows / Linux / macOS / other platform        |
                         +--------------------------------------------------+
```

## 2) Explanation of the Components

### JDK (Java Development Kit)
The JDK is the complete toolset used by developers to write, compile, and run Java programs.

It includes:
- JRE
- Java compiler (`javac`)
- Java runtime tools
- Java documentation tools
- Other development utilities

The JDK is used during software development.

### JRE (Java Runtime Environment)
The JRE is the environment required to run Java applications. It includes:
- the JVM
- Java class libraries
- supporting runtime files

The JRE is used when you only need to execute Java programs, not develop them.

### JVM (Java Virtual Machine)
The JVM is the runtime engine that executes Java bytecode.

It is responsible for:
- loading class files
- verifying code safety
- managing memory
- handling garbage collection
- executing programs
- providing platform-specific runtime behavior

The JVM is the key reason Java can work on many operating systems.

## 3) How Java Achieves Platform Independence

Java achieves platform independence through the following process:

1. Java source code is written by the developer.
2. The Java compiler converts the source code into bytecode.
3. The bytecode is not machine-specific.
4. The JVM interprets or compiles the bytecode for the actual operating system.
5. The same Java program can run on different devices without changing the source code.

This is often summarized as:

Write once, run anywhere (WORA)

The JVM acts as the bridge between Java bytecode and the host operating system.

## 4) Java Editions

Java is available in several editions depending on the type of application being developed:

### Java SE (Standard Edition)
Used for general-purpose desktop and standalone applications.

### Java EE (Enterprise Edition)
Used for large-scale enterprise applications and web-based systems.

### Java ME (Micro Edition)
Used for mobile and embedded devices.

These editions are built on the same Java core but target different types of applications.

## 5) Summary

Java architecture is built around the relationship between:

- JDK: used for development
- JRE: used for execution
- JVM: executes bytecode and enables portability

Together, these components make Java a powerful, portable, and widely-used programming language for enterprise, web, mobile, and embedded systems.

This document is submitted on the branch:
`java-editions-platform-architecture-week2-2373`
