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
		System.out.print("Enter Visitor ID: ");
		String visitorID = i.nextLine();
		System.out.print("Enter Visitor Name: ");
		String visitorName = i.nextLine();
		Visitor v = new Visitor(visitorID, visitorName);
		v.saveToFile();
	}
	//displays all Visitor entries from "Visitor.txt"
	//static method to directly access method from main without instantiation
	public static void display() {
		List <Visitor> visitors = (List<Visitor>) Visitor.getFromFile();
		System.out.println(String.format("%s", "-----------------------------------------"));
		System.out.println(String.format("%5s %3s %26s","VisitorID", "|","Name"));
		System.out.println(String.format("%s", "-----------------------------------------"));
		for(Visitor vi: visitors) {
			System.out.println(String.format("%9s %3s %26s", vi.getVisitorID(),"|",vi.getName()));
		}
	}
	public static void update()throws IOException {
		List<Visitor> visitorList = Visitor.getFromFile();
		System.out.print("Look for ID -->> ");
		String id = i.nextLine();
		for (Visitor visitor : visitorList) {
	        if (visitor.getVisitorID().equals(id)) {
		System.out.print("Enter new name -->> ");
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
}