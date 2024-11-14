package fitness;
    
import java.io.*;
import java.util.*;

public class Class {
    private String classID, className, startTime,endTime,staffID,maxCapacity;
    
    public Class(String classID, String className, String maxCapacity, String startTime, String endTime, String staffID) {
        this.classID = classID;
        this.className = className;
        this.maxCapacity = maxCapacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.staffID = staffID;
    }
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
    public String getMaxCapacity() {
        return maxCapacity;
    }
    public void setMaxCapacity(String maxCapacity) {
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
                    classList.add(new Class(data[0].trim(), data[1].trim(),(data[2].trim()), data[3].trim(), data[4].trim(), data[5].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading classes from file: " + e.getMessage());
        }
        return classList;
    }

    // Method to check if staff ID is valid for class creation
    public static boolean isStaffIDValid(String staffID) {
        List<Staff> staffList = Staff.loadStaffFromFile();
        for (Staff staff : staffList) {
            if (staff.getStaffID().equals(staffID)) {
                return true;
            }
        }
        return false;
    }

    // Method to manage class creation, update, and deletion
    public static void manageClassFromUserInput() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Select an operation for Class:");
        System.out.println("1. Create Class");
        System.out.println("2. Update Class");
        System.out.println("3. Delete Class");
        int choice = inp.nextInt();
        inp.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                // Create Class
                System.out.println("Enter Class ID: ");
                String classID = inp.nextLine();
                System.out.println("Enter Class Name: ");
                String className = inp.nextLine();
                System.out.println("Enter Max Capacity: ");
                String maxCapacity = inp.nextLine();
                System.out.println("Enter Start Time: ");
                String startTime = inp.nextLine();
                System.out.println("Enter End Time: ");
                String endTime = inp.nextLine();
                try{Staff.displayStafffile();}
                catch(IOException e){}
                System.out.println("Enter a Valid Staff ID: ");
                String staffID = inp.nextLine();
                
                if (isStaffIDValid(staffID)) {
                    Class newClass = new Class(classID, className, maxCapacity, startTime, endTime, staffID);
                    addClassToFile(newClass);
                    System.out.println("Class created successfully!");
                } 
                else {
                    System.out.println("Error: Invalid Staff ID.");
                }
                break;

            case 2:
                // Update Class
                System.out.println("Enter Class ID to update: ");
                String updateClassID = inp.nextLine();
                System.out.println("Enter New Class Name: ");
                String newClassName = inp.nextLine();
                System.out.println("Enter New Max Capacity: ");
                String newMaxCapacity = inp.nextLine();
                inp.nextLine();  // Consume newline
                System.out.println("Enter New Start Time: ");
                String newStartTime = inp.nextLine();
                System.out.println("Enter New End Time: ");
                String newEndTime = inp.nextLine();
                System.out.println("Enter New Staff ID: ");
                String newStaffID = inp.nextLine();

                if (isStaffIDValid(newStaffID)) {
                    Class updatedClass = new Class(updateClassID, newClassName, newMaxCapacity, newStartTime, newEndTime, newStaffID);
                    updateClassInFile(updateClassID, updatedClass);
                    System.out.println("Class updated successfully!");
                } 
                else {
                    System.out.println("Error: Invalid Staff ID.");
                }
                break;

            case 3:
                // Delete Class
                System.out.println("Enter Class ID to delete: ");
                String deleteClassID = inp.nextLine();
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
    public static void displayClassFile()throws IOException{
        try (BufferedReader writer = new BufferedReader(new FileReader("class.txt"))) {
            String line;
            while((line = writer.readLine()) != null)
            {
                System.out.println(line);
            }   }
        catch(IOException e){ 
            System.out.println("Error dispalying file: " +e.getMessage());
        }
    }
}
