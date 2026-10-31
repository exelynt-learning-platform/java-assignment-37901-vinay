# File-Based Student Record Management System (Java I/O)

This assignment demonstrates a complete file-based Student Record Management System using Java I/O features:
1. **File Operations Using `File` Class:** File creation (`createNewFile()`), existence check (`exists()`), path querying (`getAbsolutePath()`), and permission checks (`canRead()`, `canWrite()`).
2. **Formatted File Writing:** Chaining `FileWriter` -> `BufferedWriter` -> `PrintWriter` to write structured, formatted student records.
3. **Buffered File Reading:** Chaining `FileReader` -> `BufferedReader` using `readLine()` to read until end-of-file.
4. **Object Serialization & Deserialization:** Serializing `Student` objects implementing `Serializable` using `ObjectOutputStream` and deserializing them back into live objects using `ObjectInputStream`.
5. **Robust Exception Handling:** Applying `try-with-resources` to guarantee stream closure, and catching `IOException`, `FileNotFoundException`, and `ClassNotFoundException`.

---

## Sample `students.txt` Content

```text
=================================================================
                 STUDENT ACADEMIC RECORDS DATABASE               
=================================================================
ID       | Student Name           | Department           | GPA   
-----------------------------------------------------------------
101      | Vinay Madarkhandi      | Computer Science     |   3.95
102      | Alice Johnson          | Data Engineering     |   3.88
103      | Bob Smith              | Information Security |   3.65
104      | Diana Prince           | Artificial Intelligence |   4.00
=================================================================
```

---

## Verified Sample Program Execution Output

```text
=================================================================
   FILE-BASED STUDENT RECORD MANAGEMENT SYSTEM (JAVA I/O)        
=================================================================

--- 1. File Class Validation & Creation ---
File 'students.txt' already exists.
File Name          : students.txt
Absolute Path      : /Users/admin/Desktop/temp/java-assignment-37901-vinay/students.txt
File Exists?       : true
Readable (canRead) : true
Writable (canWrite): true
File Size in Bytes : 612

--- 2. Writing Records via FileWriter, BufferedWriter, & PrintWriter ---
>> Success: Formatted student records written to students.txt

--- 3. Reading Records via FileReader & BufferedReader ---

=================================================================
                 STUDENT ACADEMIC RECORDS DATABASE               
=================================================================
ID       | Student Name           | Department           | GPA   
-----------------------------------------------------------------
101      | Vinay Madarkhandi      | Computer Science     |   3.95
102      | Alice Johnson          | Data Engineering     |   3.88
103      | Bob Smith              | Information Security |   3.65
104      | Diana Prince           | Artificial Intelligence |   4.00
=================================================================

>> Success: End of file reached. All lines read from students.txt

--- 4. Object Serialization (ObjectOutputStream) ---
>> Serialization Success: 4 Student objects written to students.dat

--- 5. Object Deserialization (ObjectInputStream) ---
Reconstructing 4 objects from binary stream:
  [Restored]: Student [ID: 101  | Name: Vinay Madarkhandi    | Dept: Computer Science | GPA: 3.95]
  [Restored]: Student [ID: 102  | Name: Alice Johnson        | Dept: Data Engineering | GPA: 3.88]
  [Restored]: Student [ID: 103  | Name: Bob Smith            | Dept: Information Security | GPA: 3.65]
  [Restored]: Student [ID: 104  | Name: Diana Prince         | Dept: Artificial Intelligence | GPA: 4.00]
>> Deserialization Success: Live objects reconstructed successfully.

=================================================================
All Java I/O file handling operations verified successfully!     
=================================================================
```

> **Note:** As specified in the instructions, the binary `.dat` file (`students.dat`) is excluded from git commits via `.gitignore`, and the verified deserialized object output is documented above.
