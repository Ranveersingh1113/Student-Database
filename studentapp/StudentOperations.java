package studentapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates all CRUD operations on Student table.
 */
public class StudentOperations {

    /**
     * Add a new student (INSERT).
     */
    public void addStudent(Scanner scan) {
        System.out.println("\n== Add Student ==");
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO students (prn, name, dob, marks) VALUES (?, ?, ?, ?)")) {

            // gather input
            System.out.print("Enter PRN: ");
            int prn = Integer.parseInt(scan.nextLine());
            System.out.print("Enter Name: ");
            String name = scan.nextLine();
            System.out.print("Enter DoB (YYYY-MM-DD): ");
            String dob = scan.nextLine();
            System.out.print("Enter Marks: ");
            float marks = Float.parseFloat(scan.nextLine());

            // bind parameters
            stmt.setInt(1, prn);
            stmt.setString(2, name);
            stmt.setDate(3, Date.valueOf(dob));
            stmt.setFloat(4, marks);

            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Student added successfully." : "Add failed.");

        } catch (SQLException e) {
            System.err.println("DB error: " + e.getMessage());
        }
    }

    /**
     * Retrieve all students (SELECT) and return as a list.
     */
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("prn"),
                    rs.getString("name"),
                    rs.getDate("dob").toString(),
                    rs.getFloat("marks")
                ));
            }
        } catch (SQLException e) {
            System.err.println("DB error: " + e.getMessage());
        }
        return list;
    }

    /**
     * Display all students.
     */
    public void displayStudents() {
        System.out.println("\n== Display All Students ==");
        List<Student> students = getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No records found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    /**
     * Search by PRN.
     */
    public void searchByPrn(Scanner scan) {
        System.out.println("\n== Search by PRN ==");
        System.out.print("Enter PRN to search: ");
        int prn = Integer.parseInt(scan.nextLine());
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT * FROM students WHERE prn = ?")) {

            stmt.setInt(1, prn);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student(
                        rs.getInt("prn"),
                        rs.getString("name"),
                        rs.getDate("dob").toString(),
                        rs.getFloat("marks")
                    );
                    System.out.println("Found: " + s);
                } else {
                    System.out.println("No student with PRN " + prn);
                }
            }

        } catch (SQLException e) {
            System.err.println("DB error: " + e.getMessage());
        }
    }

    /**
     * Search by Name.
     */
    public void searchByName(Scanner scan) {
        System.out.println("\n== Search by Name ==");
        System.out.print("Enter name to search: ");
        String name = scan.nextLine();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT * FROM students WHERE name LIKE ?")) {

            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    Student s = new Student(
                        rs.getInt("prn"),
                        rs.getString("name"),
                        rs.getDate("dob").toString(),
                        rs.getFloat("marks")
                    );
                    System.out.println(s);
                }
                if (!found) System.out.println("No match for name '" + name + "'");
            }

        } catch (SQLException e) {
            System.err.println("DB error: " + e.getMessage());
        }
    }

    /**
     * Search by position in the retrieved list.
     */
    public void searchByPosition(Scanner scan) {
        System.out.println("\n== Search by Position ==");
        List<Student> list = getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No records to index.");
            return;
        }
        System.out.print("Enter position (0 to " + (list.size()-1) + "): ");
        int idx = Integer.parseInt(scan.nextLine());
        if (idx < 0 || idx >= list.size()) {
            System.out.println("Invalid position.");
        } else {
            System.out.println("At position " + idx + ": " + list.get(idx));
        }
    }

    /**
     * Update/Edit a student record.
     */
    public void updateStudent(Scanner scan) {
        System.out.println("\n== Update Student ==");
        System.out.print("Enter PRN to update: ");
        int prn = Integer.parseInt(scan.nextLine());

        // fetch existing to show current values
        System.out.print("New Name: ");
        String name = scan.nextLine();
        System.out.print("New DoB (YYYY-MM-DD): ");
        String dob = scan.nextLine();
        System.out.print("New Marks: ");
        float marks = Float.parseFloat(scan.nextLine());

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE students SET name = ?, dob = ?, marks = ? WHERE prn = ?")) {

            stmt.setString(1, name);
            stmt.setDate(2, Date.valueOf(dob));
            stmt.setFloat(3, marks);
            stmt.setInt(4, prn);

            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Update successful." : "No record updated.");

        } catch (SQLException e) {
            System.err.println("DB error: " + e.getMessage());
        }
    }

    /**
     * Delete a student record.
     */
    public void deleteStudent(Scanner scan) {
        System.out.println("\n== Delete Student ==");
        System.out.print("Enter PRN to delete: ");
        int prn = Integer.parseInt(scan.nextLine());
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "DELETE FROM students WHERE prn = ?")) {

            stmt.setInt(1, prn);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Delete successful." : "No record deleted.");

        } catch (SQLException e) {
            System.err.println("DB error: " + e.getMessage());
        }
    }
}
