package fitness;
import java.io.*;
import java.util.*;

public class Visitor extends CRD {
	public static Scanner i =new Scanner(System.in);
	private String visitorID;
	private String name;
	public Visitor(String visitorID, String name) {
		this.visitorID = visitorID;
		this.name = name;
	}
	public Visitor() {
		
	}
	public String getVisitorID() {
		return visitorID;
	}
	public String getName() {
		return name;
	}
	public void setVisitorID(String visitorID) {
		this.visitorID = visitorID;
	}
	public void setName(String name) {
		this.name = name;
	}
	//saves record from user input into Visitor.txt
	public void saveToFile() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("Visitor.txt", true));
			writer.write(visitorID + "*" + name + "*");
			writer.newLine();
			writer.close();
	}
	//List<Visitor> as a return data type in this method
	/*List is an interface, concrete class ArrayList implements the List interface
	 which allows flexibility*/
	public static List<Visitor> getFromFile() {
		List<Visitor> visitors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Visitor.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\*");
                String id = data[0];
                String name = data[1];
                visitors.add(new Visitor(id, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitors;
	}
	//adds a Visitor record to "Visitor.txt"
	public void add()throws IOException {
		String visitorID;
		List <Visitor> visitorList = (List<Visitor>) Visitor.getFromFile();
		boolean isValid = false;

		do {
			System.out.print("Enter new Visitor ID: ");
			visitorID = i.nextLine();
			isValid = true;
			for (Visitor visitor : visitorList) {
				if(visitor.getVisitorID().equals(visitorID) || visitorID.isEmpty()) {	
					if(visitor.getVisitorID().equals(visitorID)) {
						System.out.println("Visitor ID already exists! Please enter a new one.");
					} else if (visitorID.isEmpty()) {
						System.out.println("Visitor ID cannot be empty.");
					}
					isValid = false;
					break;
				}
			}	
		}
		while(!isValid);
		System.out.print("Enter Visitor Name: ");
		String visitorName = i.nextLine();
		System.out.println("Visitor added Successfully");
		Visitor v = new Visitor(visitorID, visitorName);
		v.saveToFile();
	}
	//displays all Visitor entries from "Visitor.txt"
	//static method to directly access method from main without instantiation
	public void display() {
		List <Visitor> visitorList = (List<Visitor>) Visitor.getFromFile();
		System.out.println(String.format("%s", "----------------------------------"));
		System.out.println(String.format("%5s %3s %20s","VisitorID", "|","Name"));
		System.out.println(String.format("%s", "----------------------------------"));
		for(Visitor vi: visitorList) {
			System.out.println(String.format("%9s %3s %20s", vi.getVisitorID(),"|",vi.getName()));
		}
		System.out.println(String.format("%s", "----------------------------------"));
	}

	//Updates the visitor name
	public static void update()throws IOException {
		List<Visitor> visitorList = Visitor.getFromFile();
		String id;
		int ctr = 0;
		boolean isExisting = false;
		do {
			System.out.print("Look for Visitor ID:  ");
			id = i.nextLine();
			for (Visitor visitor : visitorList) {
				if(visitor.getVisitorID().equals(id)) {	
					ctr++;
				}
			}
			if (ctr != 0) {
				isExisting = true;
			}
			else {
				System.out.println("Visitor ID not found. Please try again.");
			}
			ctr = 0;
		}
		while(isExisting == false);
		for (Visitor visitor : visitorList) {
	        if (visitor.getVisitorID().equals(id)) {
		System.out.print("Current name: " + visitor.getName() + "\nEnter new name: ");
		String nn = i.nextLine();
	            visitor.setName(nn);
	            visitor.saveToFile();
	        }
	    }
	    BufferedWriter writer = new BufferedWriter(new FileWriter("Visitor.txt"));
        for (Visitor visitor : visitorList) {
        	writer.write(visitor.getVisitorID() + "*" + visitor.getName());
            writer.newLine();
        }
        System.out.println("Updated Entry.");
        writer.close();
	}

	//delete visitor record
	public void delete()throws IOException{
		List<Visitor> visitorList = Visitor.getFromFile();//.txt file into ArrayList
		boolean isValid = false;
		//Valid visitor id must be input 
		do {
			System.out.print("Look for Visitor ID: ");
			String id = i.nextLine();
			for (Visitor visitor : visitorList) {
				if (visitor.getVisitorID().equals(id)) {
					isValid =true;
					if (recordCheck(id)) {
						System.out.print("Are you sure you want to delete " + id + "?[1]Yes [2]No: ");
				        String deleteConfirm = i.nextLine();
				        switch(deleteConfirm) {
				        	case "1"://case 1 / "YES"
				        		BufferedWriter writer = new BufferedWriter(new FileWriter("Visitor.txt"));//rewrites .txt file
				        		for (Visitor visitor1: visitorList) {
				        			if(!visitor1.getVisitorID().equals(id)) {//skips VisitorID input from being written
				        				writer.write(visitor1.getVisitorID() + "*" + visitor1.getName());
				        				writer.newLine();
				        			}
				        		}
				        		writer.close();
				        		System.out.println("Visitor Deleted Successfully.");
				        		break;
				        	case "2": //case 2 / "NO"
				        		System.out.println("Deletion Canceled.");
				        		break;
				        }
					} 
				}
				
				
			}
			if (!isValid) {
				System.out.println("Visitor ID not found. Please try again.");
			}
			
			
		}
		while(!isValid);
		
	}
	//Checks if the visitor id is being used in other records
	public static boolean recordCheck(String id)  throws IOException {
		List<Member> memberList = Member.getFromFile();
		
		boolean isValid = true;
		for (Member member : memberList) {
			if (member.getVisitorID().equals(id)) {
				System.out.println("Visitor is Used by Member Class, Invalid Delete.");
				isValid =false;
				break;
			}
		}
		
		List<Payment> paymentList = Payment.getFromFile();
		for (Payment payment : paymentList) {
			if (payment.getVisitorID().equals(id)) {
				System.out.println("Visitor is used by Payment Class, Invalid Delete.");
				isValid =false;
				break;
			}
		}
		
		List<CheckInRecord> recordList = CheckInRecord.getFromFile();
		for (CheckInRecord records:recordList) {
			if(records.getVisitorID().equals(id)) {
				System.out.println("Visitor is used in CheckInRecord Class, Invalid Delete.");
				isValid =false;
				break;
			}
		}
		
		List<ClassRegistration> regList = ClassRegistration.getFromFile();
		for (ClassRegistration reg : regList) {
			if(reg.getVisitorID().equals(id)) {
				System.out.println("Visitor is used in ClassRegistrationClass, Invalid Delete.");
				isValid =false;
				break;
			}
		}
		
		return isValid;
	}
	
	
}