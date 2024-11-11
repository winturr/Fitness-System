package fitness;
import java.io.*;
import java.util.Scanner;

public class Staff {
	public Scanner i = new Scanner(System.in);
	private String staffID;
	private String name;
	private String role;
	private String contactInfo;
	
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
	public void add() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("staff.txt",true));
		System.out.print("Enter staff ID: ");
		setStaffID(i.nextLine());
		System.out.print("Enter staff name: ");
		setName(i.nextLine());
		System.out.print("Enter staff role: ");
		setRole(i.nextLine());
		System.out.print("Enter contact info: ");
		setContactInfo(i.nextLine());
		writer.write(staffID + "\t" + name + "\t" + role + "\t" + contactInfo +"\n");
		writer.close();
	}
}