package fitness;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Member {
	public static Scanner i = new Scanner(System.in);
	public Member(String memberID, String membershipStartDate, String membershipEndDate, String contactNo, String status, String visitorID) {
		this.memberID = memberID;
		this.membershipStartDate = membershipStartDate;
		this.membershipEndDate = membershipEndDate;
		this.contactNo = contactNo;
		this.status = status;
		this.visitorID = visitorID;
	}
	private String memberID;
	private String membershipStartDate;
	private String membershipEndDate;
	private String contactNo;
	private String status;
	private String visitorID;
	
	public String getMemberID() {
		return memberID;
	}
	public String getMembershipStartDate() {
		return membershipStartDate;
	}
	public String getMembershipEndDate() {
		return membershipEndDate;
	}
	public String getContactNo() {
		return contactNo;
	}
	public String getStatus() {
		return status;
	}
	public String getVisitorID() {
		return visitorID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public void setMembershipStartDate(String membershipStartDate) {
		this.membershipStartDate = membershipStartDate;
	}
	public void setMembershipEndDate(String membershipEndDate) {
		this.membershipEndDate = membershipEndDate;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//saves record from user input into Member.txt
	public void saveToFile() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("Member.txt", true))) {
			writer.write(memberID + "*" + membershipStartDate + "*" + membershipEndDate + "*" + contactNo + "*" + status + "*" + visitorID + "*");
			writer.newLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//scans all available records in Member.txt
	public static List<Member> getFromFile() throws IOException{
		List<Member> m = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader("Member.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\*");
                String id = data[0];
                String date = data[1];
                String date2 = data[2];
                String contact = data[3];
                String status = data[4];
                String vID = data[5];
                m.add(new Member(id,date,date2,contact,status,vID));
            }
        return m;
	}
	public static void add()throws IOException {
		System.out.print("Enter Member ID: ");
		String memberID = i.nextLine();
		
		LocalDate date = LocalDate.now();
		LocalDate futureDate = date.plusYears(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = date.format(formatter);
        String endingDate = futureDate.format(formatter);
		System.out.print("Enter Contact Number: ");
		String contactNo = i.nextLine();
		System.out.print("Enter Status\n[1]Active\n[2]Inactive: ");
		String status = i.nextLine();
		switch(status) {
		case "1":
			status = "Active";
			break;
		case "2":
			status = "Inactive";
			break;
		}
		List<Visitor> visitorList = Visitor.getFromFile();
		boolean isValid = false;
		int ctr= 0;
		do {
			System.out.print("Enter a valid Visitor ID: ");
			String visitID = i.nextLine();
			for (Visitor visitor: visitorList) {
				if (visitor.getVisitorID().equals(visitID)) {
					isValid = true;
					List<Member> memberList = Member.getFromFile();
					for (Member member: memberList) {
						if (member.getVisitorID().equals(visitID)) {
							ctr++;
						}
					}
					if (ctr != 0) {
						isValid = false;
						System.out.println(ctr);
					}
					else {
						Member m = new Member(memberID, currentDate, endingDate, contactNo, status, visitID);
						m.saveToFile();
						System.out.println("Saved to \"Member.txt\".");
					}
					ctr = 0;
					
				}
			}
		}
		while(isValid == false);
	}
	//displays Member.txt in table form
	//static method to directly access method from main without instantiation
	public static void display() throws IOException {
		List <Member> members = (List<Member>) Member.getFromFile();
		System.out.println(String.format("%s", "-----------------------------------------------------------------------------------------------------"));
		System.out.println(String.format("%9s %3s %15s %1s %5s %1s %5s %5s %10s %5s %5s","MemberID", "|","MembershipStartDate" ,"|","MembershipEndDate", "|", "ContactInfo","|", "Status","|", "VisitorID"));
		System.out.println(String.format("%s", "-----------------------------------------------------------------------------------------------------"));
		for(Member me: members) {
			System.out.println(String.format("%9s %3s %5s %10s %5s %8s %11s %5s %10s %5s %5s", me.getMemberID(),"|",me.getMembershipStartDate() ,"|", me.getMembershipEndDate(),"|",me.getContactNo(),"|",me.getStatus(), "|", me.getVisitorID()));
		}
	}
	public static void update()throws IOException {
		List<Member> memberList = Member.getFromFile();
		System.out.print("Look for ID: ");
		String id = i.nextLine();
		System.out.print("Enter new Contact Info: ");
		String nc = i.nextLine();
		System.out.print("Enter new status, [1]Active [2]Inactive: ");
		String ns = i.nextLine();
		switch (ns) {
		case "1":
			ns = "Active";
			break;
		case "2":
			ns = "Inactive";
		}
	    for (Member member : memberList) {
	        if (member.getMemberID().equals(id)) {
	            member.setContactNo(nc);
	            member.setStatus(ns);
	            member.saveToFile();
	            break;
	        }
	    }
	    BufferedWriter writer = new BufferedWriter(new FileWriter("Member.txt"));
        for (Member member : memberList) {
        	writer.write(member.getMemberID() + "*" + member.getMembershipStartDate() + "*" + member.getMembershipEndDate() + "*" + member.getContactNo() + "*" + member.getStatus());
            writer.newLine();
        }
        System.out.print("Updated Entry.");
        writer.close();
	}
}
