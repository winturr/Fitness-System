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

    //saves record from user input into Member.txt
    public void saveToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Staff.txt", true))) {
            writer.write(staffID + "*" + name +"*" + contactNo + "*"+roleID+"*");
            writer.newLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    //scans all available records in Member.txt
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

    // add staff to the record 
    public static void add() {
    	boolean isValid = false;
    	List<Staff> staffs = Staff.getFromFile();
    	String staffID;
    	String roleID;
    	do {
    	    System.out.print("Enter new Staff ID: ");
    	    staffID = i.nextLine();
    	    isValid = true;
    	    for(Staff staff: staffs) {
    	        if(staff.getStaffID().equals(staffID) || staffID.isEmpty()) {
    	            if (staff.getStaffID().equals(staffID)) {
    	            	System.out.println("Staff ID already exists! Please try again.");	
    	            }
    	            if (staffID.isEmpty()) {
    	            	System.out.println("Staff ID cannot be empty.");
    	            }
    	        	
    	            isValid = false; 
    	            break; 
    	        }
    	    }
    	    
    	    
    	} while(!isValid); 

        System.out.print("Enter Staff Name: ");
        String name = i.nextLine();

        System.out.print("Enter Contact Number: ");
        String contactInfo = i.nextLine();

        List<Role> roles = Role.getFromFile();
        isValid = false;
        do {
            System.out.print("Enter a valid role ID: ");
            roleID = i.nextLine();
            for(Role role: roles) {
                if(role.getRoleID().equals(roleID)) {
                    isValid = true;
                }
            }
        }while(isValid == false);

        System.out.println("Staff added Successfully");
        Staff s = new Staff(staffID, name, contactInfo, roleID);
        s.saveToFile();
    }

    //Displays staff record 
    public static void display() {
        List <Staff> staff = (List<Staff>) Staff.getFromFile();
        System.out.println(String.format("%s", "------------------------------------------------------------------------------"));
        System.out.println(String.format("%9s %3s %15s %5s %15s %10s %15s","StaffID" ,"|","Name","|","ContactNo","|","RoleID"));
        System.out.println(String.format("%s", "------------------------------------------------------------------------------"));
        for(Staff st: staff) {
            System.out.format("%9s %3s %15s %5s %15s %10s %15s", st.getStaffID(), "|", st.getName(),"|", st.getContactNo(),"|", st.getRoleID());
            System.out.println();
        }
        System.out.println(String.format("%s", "------------------------------------------------------------------------------"));
    }
	
    //Update Staff record 
    public static void update()throws IOException {
		List<Staff> staffList = Staff.getFromFile();
		String id;
		int ctr = 0;
		boolean isExisting = false;
	    	//Requires user to input existing Staff id
		do {
			System.out.print("Look for Staff ID: ");
			id = i.nextLine();
			for (Staff staff : staffList) {
				if(staff.getStaffID().equals(id)) {	
					ctr++;
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
	        	System.out.print("Current name: " + staff.getName() + "\nEnter new name: ");
	        	String nn = i.nextLine();
	            staff.setName(nn);
	            System.out.print("Current number: " + staff.getContactNo() + "\nEnter new number: ");
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

    //Deletes staff record
    public static void delete()throws IOException{
    	List<Staff> staffList = Staff.getFromFile();//.txt file into ArrayList
		boolean isValid = false;
		do {
			System.out.print("Look for Staff ID: ");
			String id = i.nextLine();
			for (Staff staff : staffList) {
				if (staff.getStaffID().equals(id)) {
					isValid = true;
					if (recordCheck(id)) {
					System.out.print("Are you sure you want to delete " + id + "?[1]Yes [2]No: ");
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
			        		System.out.println("Staff Deleted Successfully.");
			        		break;
			        	case "2": //case 2 / "NO"
			        		System.out.println("Deletion Canceled.");
			        		break;
			        }
				}
				}
			}
			if(!isValid) {
				System.out.println("Staff ID not found. Please try again.");
			}

			
		}
		while(!isValid);
		
	}
	    
    //Boolean method to check if the staff id is being used in another class
    public static boolean recordCheck(String id)  throws IOException {
		List<Class> classList = Class.getFromFile();
		
		boolean isValid = true;
		for (Class classes : classList) {
			if (classes.getStaffID().equals(id)) {
				System.out.println("Staff is Used by Class Class, Invalid Delete.");
				isValid =false;
				break;
			}
		}
		return isValid;
		
    }
    
}
