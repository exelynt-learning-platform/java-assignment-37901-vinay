# Java Application Packaging (JAR & WAR)

Documentation and step-by-step instructions for compiling, packaging, and executing a standalone runnable Java application inside an executable JAR file with a `Main-Class` manifest entry.

---

## Packaging Steps

### Step 1: Compile the Java Source Code
Compile `App.java` into Java bytecode (`.class`):

```bash
javac App.java
```

### Step 2: Package into an Executable JAR File
Use the standard Java archive tool (`jar`) with options `c` (create), `f` (specify output archive file name), and `e` (specify the main entrypoint class):

```bash
jar cfe app.jar App App.class
```

> **Manifest Note**: The `e` flag automatically generates a `META-INF/MANIFEST.MF` inside the archive with the entry:
> `Main-Class: App`

### Step 3: Run the Executable JAR
Execute the packaged archive directly using the `-jar` flag:

```bash
java -jar app.jar "StudentManagement" "Production"
```

> **Repository Note**: Per assignment guidelines, binary `.jar` and `.class` files are excluded from git via `.gitignore` and are not committed to source control.

---

## Command Execution and Console Output

```bash
$ javac App.java
$ jar cfe app.jar App App.class
$ java -jar app.jar "StudentManagement" "Production"
```

```text
==========================================================================
      STANDALONE EXECUTABLE APPLICATION (PACKAGED IN JAR)                 
==========================================================================

Application Status: RUNNING SUCCESSFULLY FROM EXECUTABLE JAR
Execution Timestamp : 2026-10-31 15:51:58

--- Runtime Environment Diagnostic ---
Java Runtime Version : 17.0.19
Java Vendor          : Homebrew
Operating System     : Mac OS X (aarch64)
Available Processors : 8
Free Memory in JVM   : 256.09 MB

--- Command-Line Arguments ---
  Argument [0]: StudentManagement
  Argument [1]: Production

==========================================================================
JAR execution demo finished cleanly.
==========================================================================
```
