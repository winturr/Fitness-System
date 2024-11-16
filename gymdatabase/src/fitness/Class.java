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
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Class.txt", true))) {
            writer.write(classID + "*" + className +"*" + maxCapacity + "*"+startTime + "*"  + endTime + "*" + staffID );
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
     
    public static void display() {
        List <Class> class1 = getFromFile();
        System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------------------------------"));
        System.out.println(String.format("%9s %3s %26s %5s %15s %10s %15s %9s %3s %26s %5s","Class ID" ,"|","Class Name","|","Max Capacity","|","Start Time","|","End Timr","|","Staff ID"));
        System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------------------------------------------"));
        for(Class c: class1) {
            System.out.format("%9s %3s %26s %5s %15s %10s %15s %9s %3s %26s %5s", c.getClassID(), "|", c.getClassName(),"|", c.getMaxCapacity(),"|", c.getStartTime(),"|", c.getEndTime(),"|",c.getStaffID(),"|");
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
    
    
    public static boolean isStaffIDValid(String staffID) {
        List<Staff> staff = Staff.getFromFile();
        for (Staff staf : staff) {
            if (staf.getStaffID().equals(staffID)) {
                return true;
            }
        }
        return false;
    }

    public static void add() throws IOException {
    boolean isValid = false;
    List<Class> classes = getFromFile();

    
    String classID = "";
    do {
        System.out.print("Enter new Class ID: ");
        classID = inp.nextLine();
        isValid = true;
        for (
                Class class1 : classes) {
            if (class1.getClassID().equals(classID)) {
                System.out.println("Class ID already exists! Please enter a new one.");
                isValid = false;
                break;
            }
        }
        if (isValid && isEmpty(classID)) {
            System.out.println("Class ID cannot be empty.");
            isValid = false;
        }
    } while (!isValid);

    
    System.out.print("Enter Class Name: ");
    String className = inp.nextLine();
    System.out.print("Enter Max Capacity: ");
    String maxCapacity = inp.nextLine();
    System.out.print("Enter Start Time: ");
    String startTime = inp.nextLine();
    System.out.print("Enter End Time: ");
    String endTime = inp.nextLine();

    
    String staffID = "";
    List<Staff> staffs = Staff.getFromFile();
    boolean staffFound = false;
    do {
        System.out.print("Enter valid Staff ID: ");
        staffID = inp.nextLine();
        for (Staff staff : staffs) {
            if (staff.getStaffID().equals(staffID)) {
                staffFound = true;
                break;
            }
        }
        if (!staffFound) {
            System.out.println("Invalid Staff ID! Please enter a valid one.");
        }
    } while (staffID.isEmpty() || !staffFound);

    
    Class newClass = new Class(classID, className, maxCapacity, startTime, endTime, staffID);
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
                System.out.println("Class ID " + class1.getClassID() + " Found.");
                isValid = true;

                System.out.print("Enter new Class Name: ");
                class1.setClassName(inp.nextLine());
                System.out.print("Enter new Max Capacity: ");
                class1.setMaxCapacity(inp.nextLine());
                System.out.print("Enter new Start Time: ");
                class1.setStartTime(inp.nextLine());
                System.out.print("Enter new End Time: ");
                class1.setEndTime(inp.nextLine());

                String staffID = "";
                List<Staff> staffs = Staff.getFromFile();
                boolean staffValid = false;
                do {
                    System.out.print("Enter valid Staff ID: ");
                    staffID = inp.nextLine();
                    for (Staff staff : staffs) {
                        if (staff.getStaffID().equals(staffID)) {
                            staffValid = true;
                            break;
                        }
                    }
                    if (!staffValid) {
                        System.out.println("Invalid Staff ID.");
                    }
                } while (!staffValid);
                class1.setStaffID(staffID);
            }
        }

        if (ctr == 0) {
            System.out.println("Class ID not found.");
        }

    } while (!isValid);

    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Class.txt"))) {
        for (Class class1 : classList) {
            writer.write(class1.getClassID() + "*" + class1.getClassName() + "*" + class1.getMaxCapacity() + "*" + class1.getStartTime() + "*" + class1.getEndTime() + "*" + class1.getStaffID());
            writer.newLine();
        }
        System.out.println("Updated class information saved.");
    }
}

    
    
    
    
    public static void delete() throws IOException {
    List<Class> classList = getFromFile();
    System.out.print("Enter Class ID to delete: ");
    String id = inp.nextLine();
    if(ClassRegistration.isClassIDValid(id)){
        System.out.println("Class is Used by Class Regidtration file, invalid delete");
    }
    else{
        System.out.print("Are you sure you want to delete Class ID " + id + "? [1] Yes [2] No: ");
        String deleteConfirm = inp.nextLine();

        if (deleteConfirm.equals("1")) {

            classList.removeIf(class1 -> class1.getClassID().equals(id));


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Class.txt"))) {
                for (Class class1 : classList) {
                    writer.write(class1.getClassID() + "*" + class1.getClassName() + "*" + class1.getMaxCapacity() + "*" + class1.getStartTime() + "*" + class1.getEndTime() + "*" + class1.getStaffID());
                    writer.newLine();
                }
                System.out.println("Class deleted successfully.");
            }
        } else {
            System.out.println("Deletion canceled.");
        }
}
}

    public static boolean isEmpty(String input) {
		if (input != "") {
			return false;
		}
		return true;
	}
}
