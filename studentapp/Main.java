package studentapp;

import java.util.Scanner;

/*
 * Name   : Ranveer Singh
 * PRN    : 23070126102
 * Batch  : 23-27
 */

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StudentOperations ops = new StudentOperations();
        int choice;

        // main menu loop
        do {
            System.out.println("\n=== Student Data Entry Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search by PRN");
            System.out.println("4. Search by Name");
            System.out.println("5. Search by Position");
            System.out.println("6. Update/Edit Student");
            System.out.println("7. Delete Student");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = Integer.parseInt(scan.nextLine());

            switch (choice) {
                case 1 -> ops.addStudent(scan);
                case 2 -> ops.displayStudents();
                case 3 -> ops.searchByPrn(scan);
                case 4 -> ops.searchByName(scan);
                case 5 -> ops.searchByPosition(scan);
                case 6 -> ops.updateStudent(scan);
                case 7 -> ops.deleteStudent(scan);
                case 0 -> System.out.println("Exiting. Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        scan.close();
    }
}
