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

public class ClassRegistration {
	private String registrationID, registrationDate, classID,visitorID;
	public static Scanner inp = new Scanner(System.in);
	LocalDate date = LocalDate.now();
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public ClassRegistration(String registrationID,String registrationDate, String classID, String visitorID) {
		this.registrationID=registrationID;
		this.registrationDate=registrationDate;
		this.classID=classID;
		this.visitorID=visitorID;
	}
	
	public void setRegistrationID(String registrationID) {
		this.registrationID=registrationID;
	}
	
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate=registrationDate;
	}
	
	public void setClassID(String classID) {
		this.classID=classID;
	}
	
	public void setVisitorID(String visitorID) {
		this.visitorID=visitorID;
	}
	
	public String getRegistrationID() {
		return registrationID;
	}
	
	public String getRegistrationDate() {
		return registrationDate;
	}
	
	public String getClassID() {
		return classID;
	}
	
	public String getVisitorID() {
		return visitorID;
	}
	 // save the record to the ClassRegistration txt file
	 public void saveToFile() throws IOException {
		 BufferedWriter writer = new BufferedWriter(new FileWriter("ClassRegistration.txt", true));
		 writer.write(registrationID + "*" + registrationDate +"*" + classID+"*"+visitorID);
		 writer.newLine();
		 writer.close();
	 }
	 // Reading the ClassRegistration file and putting all the records into an array list
	 public static List<ClassRegistration> getFromFile() throws IOException {
		 List<ClassRegistration> classreg = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader("ClassRegistration.txt"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] data = line.split("\\*");
	                String regID = data[0];
	                String regDate = data[1];
	                String classID = data[2];
	                String visitorID = data[3];
	                classreg.add(new ClassRegistration(regID,regDate, classID, visitorID));
	            }
	        }	        
	    return classreg;
	 }
	 
	 public static void add() throws IOException { // for adding records
		 String regID = null;
	 		do { //Filter invalid inputs
	 		System.out.print("Enter new Registration ID: ");
	 		regID = inp.nextLine();
	 		if (regID.isEmpty()) System.out.println("Registration ID cannot be empty.");
	 		if (!isRegistrationIDValid(regID)) System.out.println("Registration ID already exist. Please try again.");
	 		} while (!isRegistrationIDValid(regID) || regID.isEmpty());
	 		
	 		System.out.println("Date Automatically inserted");
	 		String inDate = LocalDate.now().format(formatter);
	 		String classID = null;
	 		do { //Filter invalid inputs
	 		System.out.print("Enter a valid Class ID: ");
	 		classID = inp.nextLine();
	 		} while (!isClassIDValid(classID));
	 		
	 		String visitorID = null;
	 		do { //Filter invalid inputs
	 		System.out.print("Enter a valid Visitor ID: ");
	 		visitorID = inp.nextLine();
	 		} while (!isVisitorIDValid(visitorID));
	
	 		ClassRegistration classReg = new ClassRegistration(regID,inDate,classID,visitorID);		 			 			 			
	 		classReg.saveToFile();
	 		System.out.println("Class Registration added Successfully");
	 		
	 }
	 
	 public static void delete() throws IOException { //delete records
		 String classDel = null;
	 		boolean isDeleted = true;
	 		do { //Filter invalid inputs
	 		isDeleted = true;
	 		System.out.print("Look for Class Registration ID: ");
	 		classDel = inp.nextLine();	 		
	 		if (isRegistrationIDValid(classDel)) System.out.println("Invalid Class Registration ID. Please try again.");
	 		} while(isRegistrationIDValid(classDel));
	 		
	 		System.out.print("Are you sure you want to delete "+ classDel+ "?[1] Yes [2]No: "); // for confirmation
	 		String choice = inp.nextLine();
	 		if (choice.equals("1")) {
	 		} else {
	 			isDeleted = false;
	 			System.out.println("Deletion Canceled");
	 		}
	 		if(isDeleted) { // rewrite all the records to the txt file, except for the class registration ID record that the user input
	 		List<ClassRegistration> classRegistrationList = ClassRegistration.getFromFile();
	 		BufferedWriter writer = new BufferedWriter(new FileWriter("ClassRegistration.txt"));
	 		for (ClassRegistration classRegis : classRegistrationList) {
	 			if (!classRegis.getRegistrationID().equals(classDel)) {
	 				writer.write(classRegis.getRegistrationID()+"*"+classRegis.getRegistrationDate()+"*"+classRegis.getClassID()+"*"+classRegis.getVisitorID());
	 				writer.newLine();
	 				isDeleted = true;
	 			}
	 			
	 		}
	 		writer.close();
	 		System.out.println("Class Registration Deleted Successfully");
	 		}
	 }
	 
	 public static void display() throws IOException { // for displaying all the records
		 List <ClassRegistration> classreg = (List<ClassRegistration>) ClassRegistration.getFromFile();
	 		System.out.println(String.format("%s", "--------------------------------------------------------------------------"));
	 		System.out.println(String.format("%5s %2s %15s %3s %11s %6s %12s", "Reigstration ID","|","  Registration Date","|","Class ID","|","VisitorID"));
	 		System.out.println(String.format("%s", "--------------------------------------------------------------------------"));
	 		for(ClassRegistration cl: classreg) {
	 			System.out.format("%10s %7s %15s %7s %8s %9s %8s", cl.getRegistrationID(), "|", cl.getRegistrationDate(),"|",cl.getClassID(),"|",cl.getVisitorID());
	 			System.out.println();		 				 		
	 		}
	 		System.out.println(String.format("%s", "--------------------------------------------------------------------------"));
	 }
	 
	 public static boolean isRegistrationIDValid (String regID) throws IOException { // for checking if the registration id is valid
		 List<ClassRegistration> classGet = ClassRegistration.getFromFile();
	 		for (ClassRegistration regClass : classGet) {
	 			if(regClass.getRegistrationID().equals(regID)) {	
	 				return false; // if it equals it will return a false
	 			}
	 		}
		return true; // if not, it will return a true
	 }
	 
	 public static boolean isVisitorIDValid (String visitID) { // for checking if the visitor id is valid
		 List<Visitor> visitors = Visitor.getFromFile();
	 		for (Visitor visitor : visitors) {
             			if (visitor.getVisitorID().equals(visitID)) {                	                	
                 			return true; // if it equals it will return a true   
             			}
         		}
		 return false; // if not, it will return a false
	 }
	 
	 public static boolean isClassIDValid (String classID) { // for checking if the registration id is valid
		 List<Class> classList = Class.getFromFile();
	 		for (Class classClass : classList) {
	 			if (classClass.getClassID().equals(classID)) {
	 				return true;  if it equals it will return a true
	 			}
	 		}
	 	return false; // if not, it will return a false
	 }
	 
	 
}
