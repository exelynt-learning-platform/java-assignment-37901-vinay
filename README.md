# Object Serialization and Deserialization in Java

This assignment demonstrates Java object serialization and deserialization using `Serializable`, `ObjectOutputStream`, and `ObjectInputStream`.

---

## What is Serialization & Deserialization?

- **Serialization:** The process of translating an in-memory Java object graph into a sequential stream of binary bytes (`.dat`), allowing objects to be saved to persistent storage or transferred across a network.
- **Deserialization:** The reverse process of reading the serialized byte stream from the `.dat` file and reconstructing the identical live Java objects on the JVM heap.
- **`java.io.Serializable`:** A marker interface implemented by the `Student` class to enable Java's built-in serialization mechanism.
- **`serialVersionUID`:** A unique identifier that ensures version compatibility between the serialized class definition and the class loaded during deserialization.

---

## Program Console Output

Below is the verified console output from executing `SerializationDemo.java`:

```text
=================================================================
     JAVA OBJECT SERIALIZATION & DESERIALIZATION DEMO            
=================================================================

--- 1. Serializing Student Objects to 'students.dat' ---
  [Serialized Object]: Student [ID: 101 | Name: Vinay Madarkhandi  | GPA: 3.92]
  [Serialized Object]: Student [ID: 102 | Name: Alice Johnson      | GPA: 3.85]
  [Serialized Object]: Student [ID: 103 | Name: Bob Smith          | GPA: 3.45]
  [Serialized Object]: Student [ID: 104 | Name: Diana Prince       | GPA: 4.00]
>> Serialization successfully completed! Byte stream stored in students.dat.

--- 2. Deserializing Student Objects from 'students.dat' ---
Reconstructing 4 objects from binary stream:
  [Deserialized Object]: Student [ID: 101 | Name: Vinay Madarkhandi  | GPA: 3.92]
  [Deserialized Object]: Student [ID: 102 | Name: Alice Johnson      | GPA: 3.85]
  [Deserialized Object]: Student [ID: 103 | Name: Bob Smith          | GPA: 3.45]
  [Deserialized Object]: Student [ID: 104 | Name: Diana Prince       | GPA: 4.00]
>> Deserialization successfully completed! Objects restored in heap memory.

=================================================================
            VERIFICATION OF RESTORED OBJECTS IN MEMORY           
=================================================================
Verified: ID=101  | Name=Vinay Madarkhandi    | GPA=3.92
Verified: ID=102  | Name=Alice Johnson        | GPA=3.85
Verified: ID=103  | Name=Bob Smith            | GPA=3.45
Verified: ID=104  | Name=Diana Prince         | GPA=4.00
=================================================================
Serialization & Deserialization cycle verified without errors!
```

> **Note:** As specified in the assignment requirements, the binary `.dat` file (`students.dat`) is omitted from the repository via `.gitignore` and its execution output is captured in this README.
