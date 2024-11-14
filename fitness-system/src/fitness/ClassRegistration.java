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
	
	public ClassRegistration(String registrationID, String classID, String visitorID) {
		this.registrationID=registrationID;
		this.registrationDate=LocalDate.now().format(formatter);;
		this.classID=classID;
		this.visitorID=visitorID;
	}
	
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
	 
	 public static void ClassRegistrationUser() throws IOException {
		 System.out.print("[1]Add Record\n[2]Delete Record\n[3]Display Record\n[4]Exit\nSelect an Operation for Class Registration: ");
		 String userChoice = inp.nextLine();
		 switch (userChoice) {
		 	case "1":
		 		String regID = null;
		 		do {
		 		System.out.print("Registration ID: ");
		 		regID = inp.nextLine();
		 		if (!isRegistrationIDValid(regID)) System.out.println("Registration ID Already Exist.");
		 		} while (!isRegistrationIDValid(regID));
		 		
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
		 		ClassRegistration classReg = new ClassRegistration(regID,classID,visitorID);		 		
		 		if (isRegistrationIDValid(regID) && isVisitorIDValid(visitorID) && isClassIDValid(classID)) {		 			
		 			classReg.saveToFile();
		 			System.out.println("Data Successfully Added");
		 		} 
		 		
		 		
		 		break;
		 	case "2":
		 		String classDel = null;
		 		do {
		 		System.out.print("Enter CLass Registration ID you want to delete: ");
		 		classDel = inp.nextLine();
		 		} while(isRegistrationIDValid(classDel));
		 		
		 		List<ClassRegistration> classRegistrationList = ClassRegistration.getFromFile();
		 		BufferedWriter writer = new BufferedWriter(new FileWriter("ClassRegistration.txt"));
		 		for (ClassRegistration classRegis : classRegistrationList) {
		 			if (!classRegis.getRegistrationID().equals(classDel)) {
		 				writer.write(classRegis.getRegistrationID()+"*"+classRegis.getRegistrationDate()+"*"+classRegis.getClassID()+"*"+classRegis.getVisitorID());
		 				writer.newLine();
		 			}
		 			
		 		}
		 		writer.close();
		 		System.out.print("Data Deleted");
		 		
		 		break;
		 	case "3":
		 		List <ClassRegistration> classreg = (List<ClassRegistration>) ClassRegistration.getFromFile();
		 		System.out.println(String.format("%5s %2s %15s %3s %11s %6s %12s %3s"," Reigstration ID","|","  Registration Date","|","Class ID","|","VisitorID","|"));
		 		System.out.println(String.format("%s", "----------------------------------------------------------------------------------"));
		 		for(ClassRegistration cl: classreg) {
		 			System.out.format("%10s %8s %15s %7s %8s %9s %8s %7s", cl.getRegistrationID(), "|", cl.getRegistrationDate(),"|",cl.getClassID(),"|",cl.getVisitorID(),"|");
		 			System.out.println();		 				 		
		 		}
		 		
		 		break;
		 	case "4":
		 		//for exit
		 		break;
		 }
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
		 List<Class> classList = Class.loadClassesFromFile();
	 		for (Class classClass : classList) {
	 			if (classClass.getClassID().equals(classID)) {
	 				return true;
	 			}
	 		}
	 	return false;
	 }
	 
	 
}

