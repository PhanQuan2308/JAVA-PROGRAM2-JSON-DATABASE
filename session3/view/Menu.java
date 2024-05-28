package session3.view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import session3.entity.Students;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import session3.controller.StudentController;

public class Menu {
    private static StudentController studentController = new StudentController();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static Scanner scanner = new Scanner(System.in);

    public static void createStudent() throws ParseException {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter date (dd-MM-yyyy): ");
        String dateString = scanner.nextLine();

        // Convert the date string to java.sql.Date
        java.util.Date utilDate = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(dateString);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        Students newStudent = new Students(id, name, address, email, phone, sqlDate);
        studentController.createStudent(newStudent);
        System.out.println("Student created successfully!");
    }
    public static void readStudent() {
        List<Students> students = studentController.readStudents(); // Đọc dữ liệu từ cơ sở dữ liệu
        System.out.println("====== List students ==========");
        if (students == null || students.isEmpty()) {
            System.out.println("No students in database");
        } else {
            String json = gson.toJson(students);
            System.out.println(json);
        }
    }




    public static void searchByName() {
        List<Students> students = studentController.readStudents();
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        // Call the searchByName method from Menu to search students by name
        List<Students> result = studentController.searchStudentsByName(name, students);
        if (result.isEmpty()) {
            System.out.println("No students found with name '" + name + "'.");
        } else {
            System.out.println("Students found with name '" + name + "':");
            String json = gson.toJson(result);
            System.out.println(json);
        }
    }

    public static void searchByEmail() {
        List<Students> students = studentController.readStudents();
        System.out.print("Enter email to search: ");
        String email = scanner.nextLine();
        // Call the searchStudentsByEmail method from StudentController to search students by email
        List<Students> result = studentController.searchStudentsByEmail(email, students);
        if (result.isEmpty()) {
            System.out.println("No students found with email '" + email + "'.");
        } else {
            System.out.println("Students found with email '" + email + "':");
            String json = gson.toJson(result);
            System.out.println(json);
        }
    }

    public static void main(String[] args) throws ParseException {
        int choice;
        do {
            System.out.println("1. Create Student");
            System.out.println("2. Read Students from JSON file");
            System.out.println("3. Search Students by Name");
            System.out.println("4. Search Students by Email");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    readStudent();
                    break;
                case 3:
                    searchByName();
                    break;
                case 4:
                    searchByEmail();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);
    }
}
