

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Staff {
    public static Scanner i = new Scanner(System.in);
    private String staffID;
    private String name;
    private String role;
    private String contactInfo;
    private String roleID;
    public Staff(String staffID, String name, String contactInfo, String roleID) {
        this.staffID = staffID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.roleID= roleID;
    }
    public Staff() {

    }



    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRoleId(String roleID) {
        this.roleID = roleID;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    public String getStaffID() {
        return staffID;
    }

    public String getRoleId() {
        return roleID;
    }
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    public void saveToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Staff.txt", true))) {
            writer.write(staffID + "*" + name +"*" + contactInfo + "*"+roleID+"*");
            writer.newLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Staff> getFromFile() {
        List<Staff> staff = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Staff.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\*");
                String id = data[0];
                String name = data[1];
                String role = data[2];
                String info = data[3];
                staff.add(new Staff(id, name, role, info));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return staff;

    }
    public void add() {
        boolean isValid = false;
        List<Staff> staffs = Staff.getFromFile();
        do {
            System.out.print("Enter new Staff ID: ");
            String staffId = i.nextLine();
            for(Staff staff: staffs) {
                if(staff.getStaffID().equals(staffId)) {
                    System.out.println("Staff Id already exist! Please enter new one!");
                }else {
                	isValid = true;
                }
            }setStaffID(staffId);
        }while(isValid == false);

        System.out.print("Enter Staff Name: ");
        String name = i.nextLine();

        System.out.print("Enter Contact Number: ");
        String contactInfo = i.nextLine();

        List<Role> roles = Role.getFromFile();
        isValid = false;
        do {
            System.out.println("Enter valid role ID: ");
            String roleId = i.nextLine();
            for(Role role: roles) {
                if(role.getRoleId().equals(roleId)) {
                    isValid = true;
                }
            }setRoleId(roleId);
        }while(isValid == false);


        Staff s = new Staff(staffID, name, contactInfo, roleID);
        s.saveToFile();
    }
    public void display() {
        List <Staff> staff = (List<Staff>) Staff.getFromFile();
        System.out.println(String.format("%s", "------------------------------------------------------------------------------------------"));
        System.out.println(String.format("%9s %3s %26s %5s %15s %10s %15s","StaffID" ,"|","Name","|","Role","|","ContactInfo"));
        System.out.println(String.format("%s", "------------------------------------------------------------------------------------------"));
        for(Staff st: staff) {
            System.out.format("%9s %3s %26s %5s %15s %10s %15s", st.getStaffID(), "|", st.getName(),"|", st.getRole(),"|", st.getContactInfo());
            System.out.println();
        }
    }

    public static void delete()throws IOException{
        List<Staff> staffList = Staff.getFromFile();//.txt file into ArrayList
        System.out.print("Look for ID: ");
        String id = i.nextLine();
        System.out.print("Are you sure you want to delete " + id + "?\n[1]Yes [2]No --> ");
        String deleteConfirm = i.nextLine();
        switch(deleteConfirm) {
            case "1"://case 1 / "YES"
                BufferedWriter writer = new BufferedWriter(new FileWriter("Staff.txt"));//rewrites .txt file
                for (Staff staff: staffList) {
                    if(!staff.getStaffID().equals(id)) {//skips VisitorID input from being written
                        writer.write(staff.getStaffID() + "*" + staff.getName());
                        writer.newLine();
                    }
                }
                writer.close();
                System.out.println("Deleted.");
                break;
            case "2": //case 2 / "NO"
                System.out.println("Deletion cancelled.");
        }
    }

}
