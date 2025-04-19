# Student-Database


This repository contains a Java console application for managing student records using MySQL and JDBC. It demonstrates basic CRUD operations (Create, Read, Update, Delete) with a menu-driven interface.

## Table of Contents

- [Description](#description)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Database Initialization](#database-initialization)
- [Project Structure](#project-structure)
- [Class and Method Overview](#class-and-method-overview)
- [Usage](#usage)

## Description

The application consists of four main Java files:

1. **Student.java**: Defines the `Student` class with its attributes and methods.
2. **DBConnection.java**: Provides a utility for obtaining a JDBC connection to the MySQL database.
3. **StudentOperations.java**: Encapsulates all database operations (insert, select, update, delete, and search).
4. **Main.java**: Contains the entry-point (`main`) and the menu-driven interface for user interaction.

## Prerequisites

- Java Development Kit (JDK) 8 or higher installed.
- MySQL Server installed and running.
- MySQL Connector/J JDBC driver added to the classpath.
- An IDE or text editor (e.g., IntelliJ IDEA, Eclipse, VS Code).



## Database Initialization

Execute the following SQL commands to create the database and table:

```sql
CREATE DATABASE student_db;
USE student_db;

CREATE TABLE students (
  prn   INT PRIMARY KEY,
  name  VARCHAR(100) NOT NULL,
  dob   DATE,
  marks FLOAT
);
```

## Project Structure

```
src/
└── studentapp/
    ├── Student.java           # Student model class
    ├── DBConnection.java      # JDBC connection utility
    ├── StudentOperations.java # CRUD and search methods
    └── Main.java              # Menu-driven console interface
```

## Class and Method Overview

### Student.java

- **Attributes**:
  - `int prn` — unique student identifier.
  - `String name` — student’s full name.
  - `String dob` — date of birth (YYYY-MM-DD).
  - `float marks` — student’s marks or CGPA.

- **Constructor**:
  - `Student(int prn, String name, String dob, float marks)` — initializes all fields.

- **Getters/Setters**:
  - `getPrn()`, `setPrn(int)`
  - `getName()`, `setName(String)`
  - `getDob()`, `setDob(String)`
  - `getMarks()`, `setMarks(float)`

- **toString()**:
  - Returns a formatted string representation of the student.

### DBConnection.java

- **getConnection()**:
  - Static method that returns a `Connection` object to the MySQL database.

### StudentOperations.java

- **addStudent(Scanner)**:
  - Prompts user for student details and inserts a new record.

- **getAllStudents()**:
  - Retrieves all student records from the database and returns a `List<Student>`.

- **displayStudents()**:
  - Prints all students to the console.

- **searchByPrn(Scanner)**:
  - Prompts for a PRN and displays the matching student record.

- **searchByName(Scanner)**:
  - Prompts for a name (or partial) and lists matching records.

- **searchByPosition(Scanner)**:
  - Prompts for an index and shows the student at that position in the full list.

- **updateStudent(Scanner)**:
  - Prompts for a PRN and new details, then updates the record.

- **deleteStudent(Scanner)**:
  - Prompts for a PRN and deletes the corresponding record.

### Main.java

- Contains the `main(String[] args)` method.
- Displays a menu with options:
  1. Add Student
  2. Display All Students
  3. Search by PRN
  4. Search by Name
  5. Search by Position
  6. Update/Edit Student
  7. Delete Student
  0. Exit
- Uses a `Scanner` to read user input and invokes the appropriate method on `StudentOperations`.



