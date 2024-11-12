package fitness;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Visitor {
	public static Scanner i =new Scanner(System.in);
	private String visitorID;
	private String name;
	private String visitorType;
	public Visitor(String visitorID, String name, String visitorType) {
		this.visitorID = visitorID;
		this.name = name;
		this.visitorType = visitorType;
	}
	
	public Visitor(String visitorID) {
		this.visitorID = visitorID;
	}

	public String getVisitorID() {
		return visitorID;
	}
	public String getName() {
		return name;
	}
	public String getVisitorType() {
		return visitorType;
	}
	public void setVisitorID(String visitorID) {
		this.visitorID = visitorID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setVisitorType(String visitorType) {
		this.visitorType = visitorType;
	}
	//saves record from user input into Visitor.txt
	public void saveToFile() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("Visitor.txt", true))) {
			writer.write(visitorID + "*" + name + "*" + visitorType + "*");
			writer.newLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
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
                String type = data[2];
                visitors.add(new Visitor(id, name, type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitors;
	}
	//adds a Visitor record to "Visitor.txt"
	public static void add() {
		LocalDate date = LocalDate.now();
		LocalDate futureDate = date.plusYears(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = date.format(formatter);
        String endingDate = futureDate.format(formatter);
		System.out.print("Enter Visitor ID: ");
		String visitorID = i.nextLine();
		System.out.print("Enter Visitor Name: ");
		String visitorName = i.nextLine();
		System.out.print("Enter Visitor Type, [M] Member [D] Day Pass: ");
		String visitorType = i.nextLine();
		Visitor v = new Visitor(visitorID, visitorName, visitorType);
		v.saveToFile();
		switch (visitorType) {// places Visitor on either subtype (DayPassUser or Member)
		case "D":
	        DayPassUser dp = new DayPassUser(visitorID,visitorName,visitorType,currentDate);
	        dp.saveToFile();
	        System.out.println("Saved to Visitor.txt and DayPassUser.txt");
	        break;
		case "M":
			System.out.print("Enter Contact Number: ");
			String contactInfo = i.nextLine();
			System.out.print("Enter status, [1]Active [2]Inactive: ");
			String status = i.nextLine();
			switch (status) {
			case "1":
				status = "Active";
				break;
			case "2":
				status = "Inactive";
			}
			Member m = new Member(visitorID,visitorName,visitorType,currentDate,endingDate,contactInfo,status);
			m.saveToFile();
			System.out.println("Saved to Visitor.txt and Member.txt");
			break;
		}
	}
	//displays all Visitor entries from "Visitor.txt"
	//static method to directly access method from main without instantiation
	public static void display() {
		List <Visitor> visitors = (List<Visitor>) Visitor.getFromFile();
		System.out.println(String.format("%s", "------------------------------------------------"));
		System.out.println(String.format("%5s %3s %15s %5s %5s","VisitorID", "|","Name" ,"|","VisitorType"));
		System.out.println(String.format("%s", "------------------------------------------------"));
		for(Visitor vi: visitors) {
			System.out.println(String.format("%9s %3s %15s %5s %5s", vi.getVisitorID(),"|",vi.getName() ,"|", vi.getVisitorType()));
		}
	}
	public static void updateVisitor(List<Visitor> visitors, String id, String newName, String newType) {
	    for (Visitor visitor : visitors) {
	        if (visitor.getVisitorID().equals(id)) {
	            visitor.setName(newName);
	            visitor.saveToFile();
	            break;
	        }
	    }
	    saveUpdate(visitors);
	}
	public static void saveUpdate(List<Visitor> visitors) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Visitor.txt"))) {
            for (Visitor visitor : visitors) {
                writer.write(visitor.getVisitorID() + "*" + visitor.getName() + "*" + visitor.getVisitorType());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}