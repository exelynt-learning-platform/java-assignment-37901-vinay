# Setting Up a Java Development Environment

This assignment demonstrates how to install Java, configure the environment, install an IDE, create a Java project, write a simple program, and verify that the setup works successfully.

## 1) Install Java JDK

### Windows
1. Download the JDK from Oracle or Adoptium.
2. Run the installer.
3. Accept the default installation path.
4. Complete installation.

### macOS
Use Homebrew:

```bash
brew install openjdk
```

### Linux
Ubuntu/Debian:

```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

## 2) Set PATH variable

After installation, ensure Java is added to the PATH.

### Windows
Check version:

```bash
java -version
javac -version
```

If Java is not recognized, add the JDK `bin` folder to PATH manually:

Example:

```text
C:\Program Files\Java\jdk-17\bin
```

### macOS / Linux
Update the environment variables:

```bash
export JAVA_HOME=/path/to/jdk
export PATH=$JAVA_HOME/bin:$PATH
```

Then verify:

```bash
java -version
javac -version
```

## 3) Install an IDE

Choose one of the following:
- IntelliJ IDEA Community Edition
- Eclipse IDE for Java Developers

### IntelliJ IDEA
Download from:

https://www.jetbrains.com/idea/

### Eclipse
Download from:

https://www.eclipse.org/downloads/

Install the IDE and launch it.

## 4) Create a new Java project

In IntelliJ IDEA or Eclipse:

1. Create a new Java project
2. Name it `JavaSetupDemo`
3. Select the installed JDK as the project SDK
4. Create a new Java class

Example class name:

```text
Main
```

## 5) Write a simple program

Write the following Java code:

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Java Setup Successful");
    }
}
```

This program prints a confirmation message when executed.

## 6) Run the program

### IntelliJ IDEA
1. Click Run
2. Select `Main`
3. Execute the program

### Eclipse
1. Right-click the file
2. Select Run As > Java Application

Expected output:

```text
Java Setup Successful
```

This verifies that Java and the IDE are configured correctly.

## 7) Verification Checklist

You should confirm the following:

- JDK is installed correctly
- `java -version` works
- `javac -version` works
- IDE launches successfully
- Java project is created
- Program compiles without errors
- Output prints `Java Setup Successful`

## 8) Screenshots to Submit

Capture screenshots showing:

1. Java version output (`java -version`)
2. JDK compiler version output (`javac -version`)
3. IDE with the Java project open
4. Source code in the editor
5. Console output showing `Java Setup Successful`

Each screenshot should include a short explanation of what it demonstrates.

## 9) Final Summary

This assignment demonstrates the complete setup process for Java development:

- installing the JDK
- configuring the PATH
- installing an IDE
- creating a Java project
- writing and running a simple Java program
- validating the environment through successful execution

This work is submitted on the branch:

`setting-up-java-development-environment-week2-2373`
