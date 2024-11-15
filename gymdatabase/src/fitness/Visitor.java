package fitness;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Visitor {
	public static Scanner i =new Scanner(System.in);
	private String visitorID;
	private String name;
	public Visitor(String visitorID, String name) {
		this.visitorID = visitorID;
		this.name = name;
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
	public void saveToFile()throws IOException {
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
	public static void add()throws IOException {
		String visitorID;
		List <Visitor> visitorList = (List<Visitor>) Visitor.getFromFile();
		boolean isValid = false;
		int ctr = 0;
		do {
			System.out.print("Enter Visitor ID: ");
			visitorID = i.nextLine();
			for (Visitor visitor : visitorList) {
				if(visitor.getVisitorID().equals(visitorID)) {	
					ctr++;
					System.out.println("Visitor ID already exists. Please try again.");
				}
			}
			if (ctr == 0) {
				isValid = true;
			}
			if (isEmpty(visitorID)) {
				isValid = false;
			}
			ctr = 0;
		}
		while(isValid == false);
		System.out.print("Enter Visitor Name: ");
		String visitorName = i.nextLine();
		System.out.println("Successfully added to Visitor.txt");
		Visitor v = new Visitor(visitorID, visitorName);
		v.saveToFile();
	}
	//displays all Visitor entries from "Visitor.txt"
	//static method to directly access method from main without instantiation
	public static void display() {
		List <Visitor> visitorList = (List<Visitor>) Visitor.getFromFile();
		System.out.println(String.format("%s", "----------------------------------"));
		System.out.println(String.format("%5s %3s %20s","VisitorID", "|","Name"));
		System.out.println(String.format("%s", "----------------------------------"));
		for(Visitor vi: visitorList) {
			System.out.println(String.format("%9s %3s %20s", vi.getVisitorID(),"|",vi.getName()));
		}
	}
	public static void update()throws IOException {
		List<Visitor> visitorList = Visitor.getFromFile();
		String id;
		int ctr = 0;
		boolean isExisting = false;
		do {
			System.out.print("Look for ID -->> ");
			id = i.nextLine();
			for (Visitor visitor : visitorList) {
				if(visitor.getVisitorID().equals(id)) {	
					ctr++;
					System.out.println("Visitor ID found.");
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
		System.out.print("Current name: " + visitor.getName() + "\nEnter new name -->> ");
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
	public static void delete()throws IOException{
		List<Visitor> visitorList = Visitor.getFromFile();//.txt file into ArrayList
		int ctr = 0;
		boolean isValid = false;
		do {
			System.out.print("Look for ID: ");
			String id = i.nextLine();
			for (Visitor visitor : visitorList) {
				if (visitor.getVisitorID().equals(id)&& recordCheck(id)) {
					ctr++;
					isValid = true;
					System.out.println("Visitor ID " + visitor.getVisitorID() + " || " + visitor.getName() + " Found.");
					System.out.print("Are you sure you want to delete " + id + "?\n[1]Yes [2]No --> ");
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
	public static boolean recordCheck(String id)  throws IOException {
		List<Member> memberList = Member.getFromFile();
		int ctr = 0;
		boolean isValid = false;
		for (Member member : memberList) {
			if (member.getVisitorID().equals(id)) {
				ctr++;
			}
		}
		if (ctr == 0) {
			isValid = true;
		}
		List<Payment> paymentList = Payment.getFromFile();
		ctr = 0;
		isValid = false;
		for (Payment payment : paymentList) {
			if (payment.getVisitorID().equals(id)) {
				ctr++;
			}
		}
		if (ctr != 0) {
			System.out.println("Unable to delete while in another record.");
		}
		else {
			isValid = true;
		}
		return isValid;
	}
	public static boolean isEmpty(String input) {
		if (input != "") {
			return false;
		}
		return true;
	}
}