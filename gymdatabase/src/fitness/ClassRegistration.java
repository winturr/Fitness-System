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
	
	 public void saveToFile() throws IOException {
		 BufferedWriter writer = new BufferedWriter(new FileWriter("ClassRegistration.txt", true));
		 writer.write(registrationID + "*" + registrationDate +"*" + classID+"*"+visitorID);
		 writer.newLine();
		 writer.close();
	 }
	 
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
	 
	 public static void add() throws IOException {
		 String regID = null;
	 		do {
	 		System.out.print("Registration ID: ");
	 		regID = inp.nextLine();
	 		if (isExist(regID)) System.out.println("Enter Valid Registration ID");
	 		if (!isRegistrationIDValid(regID)) System.out.println("Registration ID Already Exist.");
	 		} while (!isRegistrationIDValid(regID) || isExist(regID));
	 		
	 		
	 		System.out.println("Date Automatically inserted");
	 		String inDate = LocalDate.now().format(formatter);
	 		String classID = null;
	 		do {
	 		System.out.print("Class ID: ");
	 		classID = inp.nextLine();
	 		if (!isClassIDValid(classID)) System.out.println("Class ID Doesn't Exist.");;
	 		} while (!isClassIDValid(classID));
	 		
	 		String visitorID = null;
	 		do {
	 		System.out.print("Visitor ID: ");
	 		visitorID = inp.nextLine();
	 		if (!isVisitorIDValid(visitorID)) System.out.println("Visitor ID Doesn't Exist.");
	 		} while (!isVisitorIDValid(visitorID));
	 		
	 		
	 		
	 		//check if there is an existing regID
	 		ClassRegistration classReg = new ClassRegistration(regID,inDate,classID,visitorID);		 		
	 		if (isRegistrationIDValid(regID) && isVisitorIDValid(visitorID) && isClassIDValid(classID)) {		 			
	 			classReg.saveToFile();
	 			System.out.println("Data Successfully Added");
	 		} 
	 }
	 
	 public static void delete() throws IOException {
		 String classDel = null;
	 		boolean isDeleted = true;
	 		do {
	 		System.out.print("Enter CLass Registration ID you want to delete: ");
	 		classDel = inp.nextLine();
	 		System.out.print("Are you sure you want to delete this record? (Yes or No): ");
	 		String choice = inp.nextLine().toLowerCase();
	 		if (choice.equals("no")) isDeleted = false;
	 		if (isRegistrationIDValid(classDel)) System.out.println("Registration ID doesnt Exist");
	 		} while(isRegistrationIDValid(classDel));
	 		
	 		if(isDeleted) {
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
	 		System.out.println("Data Deleted");
	 		}
	 }
	 
	 public static void display() throws IOException {
		 List <ClassRegistration> classreg = (List<ClassRegistration>) ClassRegistration.getFromFile();
	 		System.out.println(String.format("%s", "-------------------------------------------------------------------------------"));
	 		System.out.println(String.format("%5s %2s %15s %3s %11s %6s %12s %3s"," Reigstration ID","|","  Registration Date","|","Class ID","|","VisitorID","|"));
	 		System.out.println(String.format("%s", "-------------------------------------------------------------------------------"));
	 		for(ClassRegistration cl: classreg) {
	 			System.out.format("%10s %8s %15s %7s %8s %9s %8s %7s", cl.getRegistrationID(), "|", cl.getRegistrationDate(),"|",cl.getClassID(),"|",cl.getVisitorID(),"|");
	 			System.out.println();		 				 		
	 		}
	 		System.out.println(String.format("%s", "-------------------------------------------------------------------------------"));
	 }
	 
	 public static boolean isRegistrationIDValid (String regID) throws IOException {
		 List<ClassRegistration> classGet = ClassRegistration.getFromFile();
	 		for (ClassRegistration regClass : classGet) {
	 			if(regClass.getRegistrationID().equals(regID)) {	
	 				return false;
	 			}
	 		}
		return true;	
	 }
	 
	 public static boolean isVisitorIDValid (String visitID) {
		 List<Visitor> visitors = Visitor.getFromFile();
	 		for (Visitor visitor : visitors) {
             if (visitor.getVisitorID().equals(visitID)) {                	                	
                 return true;    
             }
         }
		 return false;
	 }
	 
	 public static boolean isClassIDValid (String classID) {
		 List<Class> classList = Class.getFromFile();
	 		for (Class classClass : classList) {
	 			if (classClass.getClassID().equals(classID)) {
	 				return true;
	 			}
	 		}
	 	return false;
	 }
	 
	 public static boolean isExist(String regID) {
	    	if (regID != "") {
	    		return false;
	    	}
	    	return true;
	    }
	 
}
