package fitness;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Member extends Visitor {
	public Member(String visitorID, String name, String visitorType, String membershipStartDate, String membershipEndDate, String contactInfo, String status) {
		super(visitorID, name, visitorType);
		this.membershipStartDate = membershipStartDate;
		this.membershipEndDate = membershipEndDate;
		this.contactInfo = contactInfo;
		this.status = status;
	}
	//constructor specifically used for scanning Member.txt records
	public Member(String visitorID, String memStartDate, String memEndDate, String contactInfo, String status) {
		super(visitorID);
		this.membershipStartDate = memStartDate;
		this.membershipEndDate = memEndDate;
		this.contactInfo = contactInfo;
		this.status = status;
	}
	public Member(){
		
	}
	private String membershipStartDate;
	private String membershipEndDate;
	private String contactInfo;
	private String status;
	
	public String getMembershipStartDate() {
		return membershipStartDate;
	}
	public String getMembershipEndDate() {
		return membershipEndDate;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public String getStatus() {
		return status;
	}
	public void setMembershipStartDate(String membershipStartDate) {
		this.membershipStartDate = membershipStartDate;
	}
	public void setMembershipEndDate(String membershipEndDate) {
		this.membershipEndDate = membershipEndDate;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//saves record from user input into Member.txt
	public void saveToFile() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("Member.txt", true))) {
			writer.write(getVisitorID() + "*" + membershipStartDate + "*" + membershipEndDate + "*" + contactInfo + "*" + status + "*");
			writer.newLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	//scans all available records in Member.txt
	public static List<Member> getFromFiles() {
		List<Member> m = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Member.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\*");
                String id = data[0];
                String date = data[1];
                String date2 = data[2];
                String contact = data[3];
                String status = data[4];
                m.add(new Member(id,date,date2,contact,status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return m;
	}
	//displays Member.txt in table form
	//static method to directly access method from main without instantiation
	public void display() {
		List <Member> members = (List<Member>) Member.getFromFiles();
		System.out.println(String.format("%s", "-------------------------------------------------------------------------------------"));
		System.out.println(String.format("%9s %3s %15s %1s %5s %1s %5s %5s %10s","VisitorID", "|","MembershipStartDate" ,"|","MembershipEndDate", "|", "ContactInfo","|", "Status"));
		System.out.println(String.format("%s", "-------------------------------------------------------------------------------------"));
		for(Member me: members) {
			System.out.println(String.format("%9s %3s %5s %10s %5s %8s %11s %5s %10s", me.getVisitorID(),"|",me.getMembershipStartDate() ,"|", me.getMembershipEndDate(),"|",me.getContactInfo(),"|",me.getStatus()));
		}
	}
	public void update()throws IOException {
		List<Member> memberList = Member.getFromFiles();
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
	        if (member.getVisitorID().equals(id)) {
	            member.setContactInfo(nc);
	            member.setStatus(ns);
	            member.saveToFile();
	            break;
	        }
	    }
	    BufferedWriter writer = new BufferedWriter(new FileWriter("Member.txt"));
        for (Member member : memberList) {
        	writer.write(member.getVisitorID() + "*" + member.getMembershipStartDate() + "*" + member.getMembershipEndDate() + "*" + member.getContactInfo() + "*" + member.getStatus());
            writer.newLine();
        }
        System.out.print("Updated Entry.");
        writer.close();
	}
}
