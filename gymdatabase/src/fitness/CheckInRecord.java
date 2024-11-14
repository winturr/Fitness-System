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
import java.time.LocalTime; 

public class CheckInRecord {
    private String checkInID,checkInTime,checkOutTime,date,staffID,visitorID;
    public static Scanner inp = new Scanner (System.in);
    LocalDate nowDate = LocalDate.now();
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalTime time = LocalTime.now();
	static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public CheckInRecord(String checkInID, String checkOutTime, String staffID, String visitorID) {
    	this.checkInID=checkInID;
    	this.checkInTime=LocalTime.now().format(timeFormatter);
    	this.checkOutTime=checkOutTime;
    	this.date=LocalDate.now().format(formatter);
    	this.staffID=staffID;
    	this.visitorID=visitorID;
    }
    	
    public CheckInRecord(String checkInID,String checkInTime, String checkOutTime, String date, String staffID, String visitorID) {
    	this.checkInID=checkInID;
    	this.checkInTime=checkInTime;
    	this.checkOutTime=checkOutTime;
    	this.date=date;
    	this.staffID=staffID;
    	this.visitorID=visitorID;
    }
    public String getCheckInID(){
        return checkInID;
    }
    
    public String getCheckInTime(){
        return checkInTime;
    }
    
    public String getCheckOutTime(){
        return checkOutTime;
    }
    
    public String getDate(){
        return date;
    }
    
    public String getStaffID() {
    	return staffID;
    }
    
    public String getVisitor() {
    	return visitorID;
    }
    public void setCheckInID(String checkInID) {
        this.checkInID=checkInID;
    }
    
    public void setCheckInTime(String checkInTime) {
        this.checkInTime=checkInTime;
    }
    
    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime=checkOutTime;
    }
    
    public void setDate(String date) {
        this.date=date;
    }
    
    public void setStaffID(String staffID) {
    	this.staffID=staffID;
    }
    
    public void setVisitorID(String visitorID) {
    	this.visitorID=visitorID;
    }
    
    
    public void saveToFile() throws IOException {
		 BufferedWriter writer = new BufferedWriter(new FileWriter("CheckInRecord.txt", true));
		 writer.write(checkInID + "*" + checkInTime +"*" + checkOutTime+"*"+date+"*"+staffID+"*"+visitorID);
		 writer.newLine();
		 writer.close();
	 }
    
    public static List<CheckInRecord> getFromFile() throws IOException {
		 List<CheckInRecord> inRecord = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader("CheckInRecord.txt"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] data = line.split("\\*");
	                String inID = data[0];
	                String inTime = data[1];
	                String outTime = data[2];
	                String date = data[3];
	                String staffID = data[4];
	                String visitorID = data[5];
	                inRecord.add(new CheckInRecord(inID,inTime, outTime, date,staffID,visitorID));
	            }
	        }	        
	    return inRecord;
	 }
    
    public static void add() throws IOException {
    	
    	String inID = null;
    	do {
    		System.out.print("Check in ID: ");
    		inID= inp.nextLine();
    		if (!isCheckInIDValid(inID)) System.out.println("Check In ID Already Exist");
    	} while (!isCheckInIDValid(inID));
    	
    	System.out.println("Check In Time Automatically Inserted");
    	System.out.print("Check Out Time: ");
    	String outTime = inp.nextLine();
    	
    	
    	String staffID = null;
    	do {
    		System.out.print("Staff ID: ");
    		staffID = inp.nextLine();
    		if (!isStaffIDValid(staffID)) System.out.println("Staaff ID Doesn't Exist.");
    	} while (!isStaffIDValid(staffID));
    	
    	String visitorID = null;
    	do {
	 		System.out.print("Visitor ID: ");
	 		visitorID = inp.nextLine();
	 		if (!isVisitorIDValid(visitorID)) System.out.println("Visitor ID Doesn't Exist.");
	 		} while (!isVisitorIDValid(visitorID));
	 		
    	CheckInRecord inRecord = new CheckInRecord(inID,outTime,staffID,visitorID);
    	if (isCheckInIDValid(inID) && isStaffIDValid(staffID) && isVisitorIDValid(visitorID)) {
    		inRecord.saveToFile();
    		System.out.println("Data Successfully Added");
    	}
    	
    	
    }
    
    public static void delete() throws IOException {
    	String recordDel = null;
    	boolean isDeleted = true;
    	do {
    		System.out.print("Enter Check In Record ID you want to delete: ");
    		recordDel = inp.nextLine();
    		System.out.print("Are you sure you want to delete this record? (Yes or No): ");
    		String choice = inp.nextLine().toLowerCase();
    		if (choice.equals("no")) isDeleted = false;
    		if (isCheckInIDValid(recordDel)) System.out.println("Check In ID doesn't exist.");
    	} while(isCheckInIDValid(recordDel));
    	
    	if(isDeleted) {
    		List<CheckInRecord> recordList = CheckInRecord.getFromFile();
    		BufferedWriter writer = new BufferedWriter(new FileWriter("CheckInRecord.txt"));
    		for (CheckInRecord inRecord : recordList ) {
    			if (!inRecord.getCheckInID().equals(recordDel)) {
    				writer.write(inRecord.getCheckInID()+"*"+inRecord.getCheckInTime()+"*"+inRecord.getCheckOutTime()+"*"+inRecord.getDate()+"*"+inRecord.getStaffID()+"*"+inRecord.getVisitor());
    				writer.newLine();
    			}
    		}
    		writer.close();
    		System.out.println("Date Deleted");
    	}
    }
    
    public static void display() throws IOException {
    	List<CheckInRecord> inRecord = (List<CheckInRecord>) CheckInRecord.getFromFile();
    	System.out.println(String.format("%s", "-------------------------------------------------------------------------------"));
 		System.out.println(String.format("%5s %2s %15s %3s %11s %6s %12s %3s %12s %3s %12s %3s"," Check In ID","|","  Check In Time","|","Check Out Time","|","Date,","|","Staff ID","|","Visitor ID","|"));
 		System.out.println(String.format("%s", "-------------------------------------------------------------------------------"));
 		for(CheckInRecord record : inRecord) {
 			System.out.format("%10s %8s %15s %7s %8s %9s %8s %7s %10s %5s %10s %5s", record.getCheckInID(),"|",record.getCheckInTime(),"|",record.getCheckOutTime(),"|",record.getDate(),"|",record.getStaffID(),"|",record.getVisitor(),"|");
 			System.out.println();		 				 		
 		}
 		System.out.println(String.format("%s", "-------------------------------------------------------------------------------"));
    }
    
    public static void update() throws IOException { //for updating checkoutTime only
    	List<CheckInRecord> inList = CheckInRecord.getFromFile();
    	System.out.print("Check In ID: ");
    	String inID = inp.nextLine();
    	for (CheckInRecord record : inList) {
    		if (record.getCheckInID().equals(inID)) {
    			System.out.println("new Check Out Time: ");
    			String newTime = inp.nextLine();
    			record.setCheckOutTime(newTime);
    			record.saveToFile();
    		}
    	}
    	 BufferedWriter writer = new BufferedWriter(new FileWriter("CheckInRecord.txt"));
    	 for (CheckInRecord inRecord : inList) {
    		 writer.write(inRecord.getCheckInID()+"*"+inRecord.getCheckInTime()+"*"+inRecord.getCheckOutTime()+"*"+inRecord.getDate()+"*"+inRecord.getStaffID()+"*"+inRecord.getVisitor());
    		 writer.newLine();
    	 }
    	 System.out.println("Data Updated.");
    	 writer.close();
    }
     	
    public static boolean isCheckInIDValid(String inID) throws IOException {
    	List<CheckInRecord> inRecord = CheckInRecord.getFromFile();
 		for (CheckInRecord regClass : inRecord) {
 			if(regClass.getCheckInID().equals(inID)) {	
 				return false;
 			}
 		}
	return true;	
 }
    
    public static boolean isStaffIDValid(String staffID) throws IOException {
    	List<Staff> staffList = Staff.getFromFile();
    	for (Staff staffs : staffList) {
    		if(staffs.getStaffID().equals(staffID)) {
    			return true;
    		}
    	}
    	return false;
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
}
