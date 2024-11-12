package fitness;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Visitor {
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
	public static void add() {
		Scanner i =new Scanner(System.in);
		String visitorID = i.nextLine();
		System.out.print("Enter Visitor Name: ");
		String visitorName = i.nextLine();
		System.out.print("Enter Visitor Type, [M] Member [D] Day Pass: ");
		String visitorType = i.nextLine();
		Visitor v = new Visitor(visitorID, visitorName, visitorType);
		v.saveToFile();
		switch (visitorType) {
		case "D":
			LocalDate date = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String visitDate = date.format(formatter);
	        DayPassUser dp = new DayPassUser(visitorID,visitorName,visitorType,visitDate);
	        dp.saveToFile();
	        System.out.println("Saved to Visitor.txt and DayPassUser.txt");
	        break;
		}
	}
	public static void display() {
		List <Visitor> visitors = (List<Visitor>) Visitor.getFromFile();
		System.out.format("%-13s%-20s%-15s%n","VisitorID" ," Name" ," VisitorType");
		for(Visitor vi: visitors) {
			System.out.format("%-15s%-20s%-15s%n", vi.getVisitorID() ,vi.getName() , vi.getVisitorType());
		}
	}
}