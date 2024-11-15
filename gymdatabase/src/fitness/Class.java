package fitness;
    
import java.io.*;
import java.util.*;

public class Class {
    private String classID, className, startTime,endTime,staffID,maxCapacity;
    public static Scanner inp = new Scanner(System.in);
    public Class(String classID, String className, String maxCapacity, String startTime, String endTime, String staffID) {
        this.classID = classID;
        this.className = className;
        this.maxCapacity = maxCapacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.staffID = staffID;
    }
    
    public void setClassID(String classID) {
        this.classID = classID;
    }
    public void setClassName(String className) {
        this.className = className;
    } 
    public void setMaxCapacity(String maxCapacity) {
        this.maxCapacity = maxCapacity;
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
    public String getMaxCapacity() {
        return maxCapacity;
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
    
    public void saveToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Staff.txt", true))) {
            writer.write(classID + "*" + className +"*" + maxCapacity + "*"+startTime + "*" + "*" + endTime + "*" + staffID);
            writer.newLine();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Class> getFromFile() {
        List<Class> class1 = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Class.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\*");
                String classID = data[0];
                String className = data[1];
                String maxCapacity = data[2];
                String startTime = data[3];
                String endTime = data[4];
                String staffID = data[5];
                class1.add(new Class(classID , className, maxCapacity , startTime , endTime , staffID));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return class1;

    }
     
    public void display() {
        List <Class> class1 = (List<Class>) Class.getFromFile();
        System.out.println(String.format("%s", "------------------------------------------------------------------------------------------"));
        System.out.println(String.format("%9s %3s %26s %5s %15s %10s %15s","Class ID" ,"|","Class Name","|","Max Capacity","|","Start Time","|","End Timr","|","Staff ID"));
        System.out.println(String.format("%s", "------------------------------------------------------------------------------------------"));
        for(Class c: class1) {
            System.out.format("%9s %3s %26s %5s %15s %10s %15s", c.getClassID(), "|", c.getClassName(),"|", c.getMaxCapacity(),"|", c.getStartTime(),"|", c.getEndTime(),"|",c.getStaffID());
            System.out.println();
        }
    }

    public static boolean isClassIDValid(String classID) {
            List<Class> class1 = getFromFile();
            for (Class clas : class1) {
                if (clas.getStaffID().equals(classID)) {
                    return true;
                }
            }
            return false;
        }
    
    // Method to check if staff ID is valid for class creation
    public static boolean isStaffIDValid(String staffID) {
        List<Staff> staff = Staff.getFromFile();
        for (Staff staf : staff) {
            if (staf.getStaffID().equals(staffID)) {
                return true;
            }
        }
        return false;
    }

    public void add() {
    	boolean isValid = false;
    	List<Class> classes = Class.getFromFile();

    	do {
    	    System.out.print("Enter new Class ID: ");
    	    String classID = inp.nextLine();
    	    isValid = true;
    	    for(Class class1: classes) {
    	        if(class1.getClassID().equals(classID)) {
    	            System.out.println("Class ID already exists! Please enter a new one.");
    	            isValid = false; 
    	            break; 
    	        }
    	    }
    	    if (isValid) {
    	        if (isEmpty(classID)) {
    				isValid = false;
    			}
    	    }
    	} while(!isValid); 

        System.out.print("Enter Class Name: ");
        String ClassName = inp.nextLine();

        System.out.print("Enter Max Capacity: ");
        String maxCapacity = inp.nextLine();
        
        System.out.print("Enter Start Time: ");
        String startTime = inp.nextLine();
        
        System.out.print("Enter End Time: ");
        String endTime = inp.nextLine();
        
        List<Staff> staffs = Staff.getFromFile();
        isValid = false;
        do {
            System.out.println("Enter valid Staff ID: ");
            String staffID = inp.nextLine();
            
            for(Staff staff: staffs) {
                if(staff.getStaffID().equals(staffID)) {
                    isValid = true;
                }
            }setStaffID(staffID);
        }
        while(isValid == false);
            Class c = new Class(classID, ClassName, maxCapacity, startTime, endTime, staffID);
            c.saveToFile();
        }
    
    public void update()throws IOException {
		List<Class> classList = Class.getFromFile();//.txt file into ArrayList
		boolean isValid = false;
		int ctr = 0;
		String id;
		do {
			System.out.print("Look for ID: ");
			id = inp.nextLine();
			for (Class class1: classList) {
				if(class1.getClassID().equals(id)) {
					ctr++;
					System.out.println("Class ID "+ class1.getClassID() +" Found.");
				}
			}
			if (ctr != 0) {
				isValid = true;
			}
			else {
				System.out.println("Class ID not found.");
			}
		}
		while(isValid == false);
		System.out.print("Enter new Class Name: ");
		String className = inp.nextLine();
		System.out.print("Enter new Max Capacity: ");
		String maxCapacity = inp.nextLine();
                 System.out.print("Enter Start Time: ");
                String startTime = inp.nextLine();     
                System.out.print("Enter End Time: ");
                String endTime = inp.nextLine();
                List<Staff> staffs = Staff.getFromFile();
                isValid = false;
                do {
                    System.out.println("Enter valid Staff ID: ");
                    String staffID = inp.nextLine();
            
                    for(Staff staff: staffs) {
                        if(staff.getStaffID().equals(staffID)) {
                            isValid = true;
                }
            }setStaffID(staffID);
        }
                while(isValid == false);9
                
	    BufferedWriter writer = new BufferedWriter(new FileWriter("Class.txt"));
        for (Class class1 : classList) {//rewrites the ArrayList with updated record into the .txt file
        	writer.write(class1.getClassID() + "*" + class1.getClassName()+ "*" + class1.getMaxCapacity() + "*" + class1.getStartTime() + "*" + class1.getEndTime() + "*" + class1.getStaffID() + "*");
                writer.newLine();
            }
        System.out.print("Updated Entry.");
        writer.close();
	}
    
    
    
    
    public static void delete()throws IOException{
        List<Class> classList = Class.getFromFile();//.txt file into ArrayList
        System.out.print("Look for ID: ");
        String id = inp.nextLine();
        System.out.print("Are you sure you want to delete " + id + "?\n[1]Yes [2]No --> ");
        String deleteConfirm = inp.nextLine();
        switch(deleteConfirm) {
            case "1"://case 1 / "YES"
                BufferedWriter writer = new BufferedWriter(new FileWriter("Class.txt"));//rewrites .txt file
                for (Class class1: classList) {
                    if(!class1.getStaffID().equals(id)) {//skips VisitorID input from being written
                        writer.write(class1.getClassID() + "*" + class1.getClassName());
                        writer.newLine();
                    }
                }
                writer.close();
                System.out.println("Deleted.");
                break;
            case "2": //case 2 / "NO"
                System.out.println("Deletion cancelled.");
        }
    }
    public static boolean isEmpty(String input) {
		if (input != "") {
			return false;
		}
		return true;
	}
}
