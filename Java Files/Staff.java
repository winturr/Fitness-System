package fitness;

import java.io.*;
import java.util.*;

public class Staff {
    private String staffID,staffName,role,contactInfo;

    // Constructor
    public Staff(String staffID, String staffName, String role, String contactInfo) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.role = role;
        this.contactInfo = contactInfo;
    }

    
    public String getStaffID() {
        return staffID;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Method to load all staff from file
    public static List<Staff> loadStaffFromFile() {
        List<Staff> staffList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("staff.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length == 4) {
                    staffList.add(new Staff(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading staff from file: " + e.getMessage());
        }
        return staffList;
    }

    // Method to check if the staffID exists
    public static boolean isStaffIDValid(String staffID) {
        List<Staff> staffList = loadStaffFromFile();
        for (Staff staff : staffList) {
            if (staff.getStaffID().equals(staffID)) {
                return true;
            }
        }
        return false;
    }

    // Method to add, update, or delete staff based on user input
    public static void manageStaffFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an operation for Staff:");
        System.out.println("1. Add Staff");
        System.out.println("2. Update Staff");
        System.out.println("3. Delete Staff");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1:
                // Add Staff
                System.out.println("Enter Staff ID: ");
                String staffID = scanner.nextLine();
                System.out.println("Enter Staff Name: ");
                String staffName = scanner.nextLine();
                System.out.println("Enter Role: ");
                String role = scanner.nextLine();
                System.out.println("Enter Contact Info: ");
                String contactInfo = scanner.nextLine();
                Staff newStaff = new Staff(staffID, staffName, role, contactInfo);
                addStaffToFile(newStaff);
                System.out.println("Staff added successfully!");
                break;

            case 2:
                // Update Staff
                System.out.println("Enter Staff ID to update: ");
                String updateID = scanner.nextLine();
                if (isStaffIDValid(updateID)) {
                    System.out.println("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.println("Enter New Role: ");
                    String newRole = scanner.nextLine();
                    System.out.println("Enter New Contact Info: ");
                    String newContactInfo = scanner.nextLine();
                    Staff updatedStaff = new Staff(updateID, newName, newRole, newContactInfo);
                    updateStaffInFile(updateID, updatedStaff);
                    System.out.println("Staff updated successfully!");
                } else {
                    System.out.println("Staff ID does not exist.");
                }
                break;

            case 3:
                // Delete Staff
                System.out.println("Enter Staff ID to delete: ");
                String deleteID = scanner.nextLine();
                deleteStaffFromFile(deleteID);
                System.out.println("Staff deleted successfully!");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    // Method to add staff to file
    public static void addStaffToFile(Staff staff) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("staff.txt", true))) {
            writer.write(staff.getStaffID() + " | " + staff.getStaffName() + " | " + staff.getRole() + " | " + staff.getContactInfo());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing staff to file: " + e.getMessage());
        }
    }

    // Method to update staff in the file
    public static void updateStaffInFile(String staffID, Staff updatedStaff) {
        List<Staff> staffList = loadStaffFromFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("staff.txt"))) {
            for (Staff staff : staffList) {
                if (staff.getStaffID().equals(staffID)) {
                    staff = updatedStaff;  // Update staff
                }
                writer.write(staff.getStaffID() + " | " + staff.getStaffName() + " | " + staff.getRole() + " | " + staff.getContactInfo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating staff file: " + e.getMessage());
        }
    }

    // Method to delete staff from the file
    public static void deleteStaffFromFile(String staffID) {
        List<Staff> staffList = loadStaffFromFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("staff.txt"))) {
            for (Staff staff : staffList) {
                if (!staff.getStaffID().equals(staffID)) {
                    writer.write(staff.getStaffID() + " | " + staff.getStaffName() + " | " + staff.getRole() + " | " + staff.getContactInfo());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting staff from file: " + e.getMessage());
        }
    }
    public static void displayStafffile()throws IOException{
        try (BufferedReader writer = new BufferedReader(new FileReader("staff.txt"))) {
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
