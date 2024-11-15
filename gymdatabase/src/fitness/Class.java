package fitness;

import java.io.*;
import java.util.*;

public class Class {
    private String classID;
    private String className;
    private int maxCapacity;
    private String startTime;
    private String endTime;
    private String staffID; // Associated staff

    // Constructor
    public Class(String classID, String className, int maxCapacity, String startTime, String endTime, String staffID) {
        this.classID = classID;
        this.className = className;
        this.maxCapacity = maxCapacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.staffID = staffID;
    }

    // Getters and Setters
    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    // Method to load all classes from file
    public static List<Class> loadClassesFromFile() {
        List<Class> classList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("class.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 6) {
                    classList.add(new Class(data[0].trim(), data[1].trim(), Integer.parseInt(data[2].trim()), data[3].trim(), data[4].trim(), data[5].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading classes from file: " + e.getMessage());
        }
        return classList;
    }

    // Method to check if staff ID is valid for class creation
    public static boolean isStaffIDValid(String staffID) {
        List<Staff> staffList = Staff.getFromFile();
        for (Staff staff : staffList) {
            if (staff.getStaffID().equals(staffID)) {
                return true;
            }
        }
        return false;
    }

    // Method to manage class creation, update, and deletion
    public static void manageClassFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an operation for Class:");
        System.out.println("1. Create Class");
        System.out.println("2. Update Class");
        System.out.println("3. Delete Class");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                // Create Class
                System.out.println("Enter Class ID: ");
                String classID = scanner.nextLine();
                System.out.println("Enter Class Name: ");
                String className = scanner.nextLine();
                System.out.println("Enter Max Capacity: ");
                int maxCapacity = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                System.out.println("Enter Start Time: ");
                String startTime = scanner.nextLine();
                System.out.println("Enter End Time: ");
                String endTime = scanner.nextLine();
                System.out.println("Enter Staff ID: ");
                String staffID = scanner.nextLine();

                if (isStaffIDValid(staffID)) {
                    Class newClass = new Class(classID, className, maxCapacity, startTime, endTime, staffID);
                    addClassToFile(newClass);
                    System.out.println("Class created successfully!");
                } else {
                    System.out.println("Error: Invalid Staff ID.");
                }
                break;

            case 2:
                // Update Class
                System.out.println("Enter Class ID to update: ");
                String updateClassID = scanner.nextLine();
                System.out.println("Enter New Class Name: ");
                String newClassName = scanner.nextLine();
                System.out.println("Enter New Max Capacity: ");
                int newMaxCapacity = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                System.out.println("Enter New Start Time: ");
                String newStartTime = scanner.nextLine();
                System.out.println("Enter New End Time: ");
                String newEndTime = scanner.nextLine();
                System.out.println("Enter New Staff ID: ");
                String newStaffID = scanner.nextLine();

                if (isStaffIDValid(newStaffID)) {
                    Class updatedClass = new Class(updateClassID, newClassName, newMaxCapacity, newStartTime, newEndTime, newStaffID);
                    updateClassInFile(updateClassID, updatedClass);
                    System.out.println("Class updated successfully!");
                } else {
                    System.out.println("Error: Invalid Staff ID.");
                }
                break;

            case 3:
                // Delete Class
                System.out.println("Enter Class ID to delete: ");
                String deleteClassID = scanner.nextLine();
                deleteClassFromFile(deleteClassID);
                System.out.println("Class deleted successfully!");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // Method to add class to the file
    public static void addClassToFile(Class fitnessClass) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("class.txt", true))) {
            writer.write(fitnessClass.getClassID() + " | " + fitnessClass.getClassName() + " | " + fitnessClass.getMaxCapacity() + " | "
                    + fitnessClass.getStartTime() + " | " + fitnessClass.getEndTime() + " | " + fitnessClass.getStaffID());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing class to file: " + e.getMessage());
        }
    }

    // Method to update class in the file
    public static void updateClassInFile(String classID, Class updatedClass) {
        List<Class> classList = loadClassesFromFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("class.txt"))) {
            for (Class fitnessClass : classList) {
                if (fitnessClass.getClassID().equals(classID)) {
                    fitnessClass = updatedClass;  // Update class
                }
                writer.write(fitnessClass.getClassID() + " | " + fitnessClass.getClassName() + " | " + fitnessClass.getMaxCapacity() + " | "
                        + fitnessClass.getStartTime() + " | " + fitnessClass.getEndTime() + " | " + fitnessClass.getStaffID());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating class file: " + e.getMessage());
        }
    }

    // Method to delete class from the file
    public static void deleteClassFromFile(String classID) {
        List<Class> classList = loadClassesFromFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("class.txt"))) {
            for (Class fitnessClass : classList) {
                if (!fitnessClass.getClassID().equals(classID)) {
                    writer.write(fitnessClass.getClassID() + " | " + fitnessClass.getClassName() + " | " + fitnessClass.getMaxCapacity() + " | "
                            + fitnessClass.getStartTime() + " | " + fitnessClass.getEndTime() + " | " + fitnessClass.getStaffID());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting class from file: " + e.getMessage());
        }
    }
}
