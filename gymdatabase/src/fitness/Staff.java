package fitness;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Staff {
    public static Scanner i = new Scanner(System.in);
    private String staffID;
    private String name;
    private String contactNo;
    private String roleID;
    public Staff(String staffID, String name, String contactNo, String roleID) {
        this.staffID = staffID;
        this.name = name;
        this.contactNo = contactNo;
        this.roleID= roleID;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public String getStaffID() {
        return staffID;
    }

    public String getRoleID() {
        return roleID;
    }
    public String getName() {
        return name;
    }
    public String getContactNo() {
        return contactNo;
    }
    public void saveToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Staff.txt", true))) {
            writer.write(staffID + "*" + name +"*" + contactNo + "*"+roleID+"*");
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
                String no = data[2];
                String role = data[3];
                staff.add(new Staff(id, name, no, role));
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
    	    isValid = true;
    	    for(Staff staff: staffs) {
    	        if(staff.getStaffID().equals(staffId)) {
    	        	System.out.println(staff.getStaffID());
    	            System.out.println("Staff ID already exists! Please enter a new one.");
    	            isValid = false; 
    	            break; 
    	        }
    	    }
    	    if (isValid) {
    	        setStaffID(staffId);
    	    }
    	} while(!isValid); 

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
    public static void display() {
        List <Staff> staff = (List<Staff>) Staff.getFromFile();
        System.out.println(String.format("%s", "------------------------------------------------------------------------------"));
        System.out.println(String.format("%9s %3s %15s %5s %15s %10s %15s","StaffID" ,"|","Name","|","ContactNo","|","RoleID"));
        System.out.println(String.format("%s", "------------------------------------------------------------------------------"));
        for(Staff st: staff) {
            System.out.format("%9s %3s %15s %5s %15s %10s %15s", st.getStaffID(), "|", st.getName(),"|", st.getContactNo(),"|", st.getRoleID());
            System.out.println();
        }
    }
    public static void update()throws IOException {
		List<Staff> staffList = Staff.getFromFile();
		String id;
		int ctr = 0;
		boolean isExisting = false;
		do {
			System.out.print("Look for ID -->> ");
			id = i.nextLine();
			for (Staff staff : staffList) {
				if(staff.getStaffID().equals(id)) {	
					ctr++;
					System.out.println("Staff ID found.");
				}
			}
			if (ctr != 0) {
				isExisting = true;
			}
			else {
				System.out.println("Staff ID not found. Please try again.");
			}
			ctr = 0;
		}
		while(isExisting == false);
		for (Staff staff : staffList) {
	        if (staff.getStaffID().equals(id)) {
	        	System.out.print("Current name: " + staff.getName() + "\nEnter new name -->> ");
	        	String nn = i.nextLine();
	            staff.setName(nn);
	            System.out.print("Current number: " + staff.getContactNo() + "\nEnter new number -->> ");
	        	String nno = i.nextLine();
	            staff.setContactNo(nno);
	            staff.saveToFile();
	        }
	    }
	    BufferedWriter writer = new BufferedWriter(new FileWriter("Staff.txt"));
        for (Staff staff : staffList) {
        	writer.write(staff.getStaffID() + "*" + staff.getName() + "*" + staff.getContactNo() + "*" + staff.getRoleID() + "*");
            writer.newLine();
        }
        System.out.println("Updated Entry.");
        writer.close();
	}

    public static void delete()throws IOException{
    	List<Staff> staffList = Staff.getFromFile();//.txt file into ArrayList
		int ctr = 0;
		boolean isValid = false;
		do {
			System.out.print("Look for ID: ");
			String id = i.nextLine();
			for (Staff staff : staffList) {
				if (staff.getStaffID().equals(id)) {
					ctr++;
					isValid = true;
					System.out.println("Staff ID " + staff.getStaffID() + " || " + staff.getName() + " Found.");
					System.out.print("Are you sure you want to delete " + id + "?\n[1]Yes [2]No --> ");
			        String deleteConfirm = i.nextLine();
			        switch(deleteConfirm) {
			        	case "1"://case 1 / "YES"
			        		BufferedWriter writer = new BufferedWriter(new FileWriter("Staff.txt"));//rewrites .txt file
			        		for (Staff staff1: staffList) {
			        			if(!staff1.getStaffID().equals(id)) {//skips StaffID input from being written
			        				writer.write(staff1.getStaffID() + "*" + staff1.getName() + "*" + staff1.getContactNo() + "*" + staff1.getRoleID());
			        				writer.newLine();
			        			}
			        		}
			        		writer.close();
			        		System.out.println("Deleted.");
			        		break;
			        	case "2": //case 2 / "NO"
			        		System.out.println("Deletion cancelled.");
			        		break;
			        }
				}
			}		
		}
		while(!isValid);
		
	}
    public static boolean isEmpty(String input) {
		if (input != "") {
			return false;
		}
		return true;
	}
}
