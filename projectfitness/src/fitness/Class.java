package fitness;
import java.io.*;
import java.util.*;

public class Class extends CRD {
    private String classID, className, startTime,endTime,staffID;
    public static Scanner inp = new Scanner(System.in);
    public Class(String classID, String className, String startTime, String endTime, String staffID) {
        this.classID = classID;
        this.className = className;     
        this.startTime = startTime;
        this.endTime = endTime;
        this.staffID = staffID;
    }
    public Class() {
    	
    }
    
    public void setClassID(String classID) {
        this.classID = classID;
    }
    public void setClassName(String className) {
        this.className = className;
    } 
   
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    public String getClassID() {
        return classID;
    }
    public String getClassName() {
        return className;
    }
    
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getStaffID() {
        return staffID;
    }
    
    public void saveToFile() {  // save the record to the Class txt file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Class.txt", true))) {
            writer.write(classID + "*" + className +"*" +startTime + "*"  + endTime + "*" + staffID+"*");
            writer.newLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    // Reading the Class file and putting all the records into an array list
    public static List<Class> getFromFile() {
        List<Class> class1 = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Class.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\*");
                String classID = data[0];
                String className = data[1];
                String startTime = data[2];
                String endTime = data[3];
                String staffID = data[4];
                class1.add(new Class(classID , className, startTime , endTime , staffID));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return class1;

    }
     
    public void display() {
        List <Class> class1 = getFromFile();
        System.out.println(String.format("%s", "-------------------------------------------------------------------------------------------------------------------------"));
        System.out.println(String.format("%9s %3s %15s %5s %12s %10s %12s %9s %7s","Class ID" ,"|","Class Name","|","Start Time","|","End Time","|","Staff ID"));
        System.out.println(String.format("%s", "-------------------------------------------------------------------------------------------------------------------------"));
        for(Class c: class1) {
            System.out.format("%9s %3s %15s %5s %12s %10s %12s %9s %8s", c.getClassID(), "|", c.getClassName(),"|", c.getStartTime(),"|", c.getEndTime(),"|",c.getStaffID(),"|");
            System.out.println();
        }
        System.out.println(String.format("%s", "-------------------------------------------------------------------------------------------------------------------------"));
    }

    
    
    
    public static boolean isStaffIDValid(String staffID) { //boolean with built-in filter for certain cases
        List<Staff> staff = Staff.getFromFile();
        for (Staff staf : staff) {
            if (staf.getStaffID().equals(staffID)) {
                return true;
            }
        }
        return false;
    }

    public void add() throws IOException { // Add record to the CheckInRecord file
    boolean isValid = false;
    List<Class> classes = getFromFile();

    
    String classID = "";
    do { //Filter invalid inputs
        System.out.print("Enter new Class ID: ");
        classID = inp.nextLine();
        isValid = true;
        for (
                Class class1 : classes) {
            if (class1.getClassID().equals(classID)) {
                System.out.println("Class ID already exists. Please try again.");
                isValid = false;
                break;
            }
        }
        if (isValid && classID.isEmpty()) {
            System.out.println("Class ID cannot be empty.");
            isValid = false;
        }
    } while (!isValid);

    
    System.out.print("Enter Class Name: ");
    String className = inp.nextLine();
    System.out.print("Enter Start Time: ");
    String startTime = inp.nextLine().toUpperCase();
    System.out.print("Enter End Time: ");
    String endTime = inp.nextLine().toUpperCase();

    
    String staffID = "";
    List<Staff> staffs = Staff.getFromFile();
    boolean staffFound = false;
    do {//Filter invalid inputs
        System.out.print("Enter a valid Staff ID: ");
        staffID = inp.nextLine();
        for (Staff staff : staffs) {
            if (staff.getStaffID().equals(staffID)) {
                staffFound = true;
                break;
            }
        }

    } while (staffID.isEmpty() || !staffFound);

    
    Class newClass = new Class(classID, className, startTime, endTime, staffID);
    newClass.saveToFile();
    System.out.println("Class added successfully.");
}

    
    public static void update() throws IOException {
    List<Class> classList = getFromFile();
    boolean isValid = false;
    int ctr = 0;
    String id;

    
    do {
        System.out.print("Look for Class ID: ");
        id = inp.nextLine();
        for (Class class1 : classList) {
            if (class1.getClassID().equals(id)) {
                ctr++;
                isValid = true;

                System.out.print("Cuurent Name: "+class1.getClassName()+"\nEnter new Class Name: ");
                class1.setClassName(inp.nextLine());
                System.out.print("Cuurent Start Time: "+class1.getStartTime()+"\nEnter new Start Time: ");
                class1.setStartTime(inp.nextLine().toUpperCase());
                System.out.print("Cuurent End Time: "+class1.getEndTime()+"\nEnter new End Time: ");
                class1.setEndTime(inp.nextLine().toUpperCase());

            }
        }

        if (ctr == 0) {
            System.out.println("Class ID not found. Please try again.");
        }

    } while (!isValid);

    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Class.txt"))) {
        for (Class class1 : classList) {
            writer.write(class1.getClassID() + "*" + class1.getClassName() + "*" + class1.getStartTime() + "*" + class1.getEndTime() + "*" + class1.getStaffID());
            writer.newLine();
        }
        System.out.println("Updated Entry.");
    }
}

    
    
    public static boolean isClassRegIDValid (String classID) throws IOException {
		 List<ClassRegistration> classGet = ClassRegistration.getFromFile();
	 		for (ClassRegistration class1 : classGet) {
	 			if (class1.getClassID().equals(classID)) {
	 				return true;
	 			}
	 		}
	 	return false;
	 }
    
    public static boolean isClassIDValid (String classID) throws IOException {
		 List<Class> classGet = Class.getFromFile();
	 		for (Class class1 : classGet) {
	 			if (class1.getClassID().equals(classID)) {
	 				return true;
	 			}
	 		}
	 	return false;
	 }
    
    public void delete() throws IOException {
    List<Class> classList = getFromFile();
    int ctr =1;
    while(ctr==1){
        System.out.print("Look for Class ID: ");
        String classid = inp.nextLine();

        if(isClassRegIDValid(classid)){
            System.out.println("Class is Used by Class Registration file, invalid delete");
        } else if (classid.isEmpty()) {
        	System.out.println("Class ID cannot be empty.");
        } 
        else if (isClassIDValid(classid)){
            System.out.print("Are you sure you want to delete Class ID " + classid + "?[1] Yes [2] No: ");
            String deleteConfirm = inp.nextLine();

            if (deleteConfirm.equals("1")) {

                classList.removeIf(class1 -> class1.getClassID().equals(classid));


                try (BufferedWriter writer = new BufferedWriter(new FileWriter("Class.txt"))) {
                    for (Class class1 : classList) {
                        writer.write(class1.getClassID() + "*" + class1.getClassName() + "*" + class1.getStartTime() + "*" + class1.getEndTime() + "*" + class1.getStaffID());
                        writer.newLine();
                    }
                    System.out.println("Class deleted successfully.");
                    ctr = 0;
                }
            } else {
                System.out.println("Deletion Canceled.");
                ctr = 0;
            }
    } else {
    	System.out.println("Invalid Class Registration ID. Please try again.");
    }
        
}
}

   
}
