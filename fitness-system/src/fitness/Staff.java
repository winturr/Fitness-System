package fitness;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Staff {
	public Scanner i = new Scanner(System.in);
	private String staffID;
	private String name;
	private String role;
	private String contactInfo;
	public Staff(String staffID, String name, String role, String contactInfo) {
		this.staffID = staffID;
		this.name = name;
		this.role = role;
		this.contactInfo = contactInfo;
	}
	
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getStaffID() {
		return staffID;
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
			writer.write(staffID + "*" + name + "*" + role + "*" + contactInfo + "*");
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
	public static void add() {
		Scanner i = new Scanner(System.in);
		System.out.print("Enter Staff ID: ");
		String staffID = i.nextLine();
		System.out.print("Enter Staff Name: ");
		String name = i.nextLine();
		System.out.print("Enter Staff Role: ");
		String role = i.nextLine();
		System.out.print("Enter ContactInfo: ");
		String contactInfo = i.nextLine();
		Staff s = new Staff(staffID, name, role, contactInfo);
		s.saveToFile();
	}
	public static void display() {
		List <Staff> staff = (List<Staff>) Staff.getFromFile();
		System.out.println(String.format("%5s %3s %15s %5s %15s %10s %15s","StaffID" ,"|","Name","|","Role","|","ContactInfo"));
		System.out.println(String.format("%s", "----------------------------------------------------------------------------"));
		for(Staff st: staff) {
			System.out.format("%5s %5s %15s %5s %15s %10s %15s", st.getStaffID(), "|", st.getName(),"|", st.getRole(),"|", st.getContactInfo());
			System.out.println();
		}
	}
}