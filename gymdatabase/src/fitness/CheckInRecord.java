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
    private String checkInID,checkInTime,checkOutTime,date,visitorID;
    public static Scanner inp = new Scanner (System.in);
    LocalDate nowDate = LocalDate.now();
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalTime time = LocalTime.now();
	static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    	
    public CheckInRecord(String checkInID,String checkInTime, String checkOutTime, String date, String visitorID) {
    	this.checkInID=checkInID;
    	this.checkInTime=checkInTime;
    	this.checkOutTime=checkOutTime;
    	this.date=date;
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
    
    public String getVisitorID() {
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
        
    public void setVisitorID(String visitorID) {
    	this.visitorID=visitorID;
    }
    
    // save the record to the CheckInRecord txt file
    public void saveToFile() throws IOException {
		 BufferedWriter writer = new BufferedWriter(new FileWriter("CheckInRecord.txt", true));
		 writer.write(checkInID + "*" + checkInTime +"*" + checkOutTime+"*"+date+"*"+visitorID);
		 writer.newLine();
		 writer.close();
	 }
    // Reading the CheckInRecord file and putting all the records into an array list
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
	                String visitorID = data[4];
	                inRecord.add(new CheckInRecord(inID,inTime, outTime, date,visitorID));
	            }
	        }	        
	    return inRecord;
	 }
    
    public static void add() throws IOException { // Add record to the CheckInRecord file
    	String inID = "";
    	do { //Filter invalid inputs
    		System.out.print("Enter new Check in ID: ");
    		inID= inp.nextLine();
    		if (inID.isEmpty()) System.out.println("Checkin ID cannot be empty.");
    		if (!isCheckInIDValid(inID) ) System.out.println("Checkin ID Already Exist. Please try again.");
    	} while (!isCheckInIDValid(inID) || inID.isEmpty());
    	
    	System.out.println("Check In Time Automatically Inserted");
    	System.out.println("Date Automatically Inserted");
    	String inTime = LocalTime.now().format(timeFormatter);
    	String outTime = "";
    	String inDate = LocalDate.now().format(formatter);
    	
    	String visitorID = null;
    	do { //Filter invalid inputs
	 		System.out.print("Enter a valid Visitor ID: ");
	 		visitorID = inp.nextLine();
	 		if (!isVisitorIDValid(visitorID)) System.out.println("Invalid Visitor ID. Please try again.");
	 		} while (!isVisitorIDValid(visitorID));
	 		
    	CheckInRecord inRecord = new CheckInRecord(inID,inTime,outTime,inDate,visitorID);
    	inRecord.saveToFile();
    	System.out.println("Check In Record added successfully");   	    	
    }
    
    public static void delete() throws IOException { // delete records
    	String recordDel = null;
    	boolean isDeleted = true;
    	do { //Filter invalid inputs
    		System.out.print("Look for Check in ID: ");
    		recordDel = inp.nextLine();
    		if (isCheckInIDValid(recordDel)) System.out.println("Invalid Checkin ID. Please try again.");
    	} while(isCheckInIDValid(recordDel));
    	
    	System.out.print("Are you sure you want to delete "+recordDel+"?[1]Yes or [2]No: "); // for confirmation
		String choice = inp.nextLine();
		if (choice.equals("1")) {
		} else {
			isDeleted = false;
			System.out.println("Deletion Canceled");
		}

    	
    	if(isDeleted) { //rewriting the records without the 
    		List<CheckInRecord> recordList = CheckInRecord.getFromFile();
    		BufferedWriter writer = new BufferedWriter(new FileWriter("CheckInRecord.txt"));
    		for (CheckInRecord inRecord : recordList ) {
    			if (!inRecord.getCheckInID().equals(recordDel)) {
    				writer.write(inRecord.getCheckInID()+"*"+inRecord.getCheckInTime()+"*"+inRecord.getCheckOutTime()+"*"+inRecord.getDate()+"*"+inRecord.getVisitorID());
    				writer.newLine();
    			}
    		}
    		writer.close();
    		System.out.println("Check In Record Deleted Successfully");
    	}
    }
    
    public static void display() throws IOException {
    	List<CheckInRecord> inRecord = (List<CheckInRecord>) CheckInRecord.getFromFile();
    	System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------"));
 		System.out.println(String.format("%5s %2s %11s %3s %11s %6s %12s %3s %12s"," CheckInID","|","  CheckInTime","|","CheckOutTime","|","Date","|","VisitorID"));
 		System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------"));
 		for(CheckInRecord record : inRecord) {
 			System.out.format("%9s %3s %11s %5s %11s %7s %8s %5s %10s", record.getCheckInID(),"|",record.getCheckInTime(),"|",record.getCheckOutTime(),"|",record.getDate(),"|",record.getVisitorID());
 			System.out.println();		 				 		
 		}
 		System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------"));
    }
    
    public static void checkOutTime() throws IOException { //for updating checkoutTime only
    	List<CheckInRecord> inList = CheckInRecord.getFromFile();
    	String inID = null;
    	do {
    	System.out.print("Look for Check in ID: ");
    	inID = inp.nextLine();
    	if (isCheckInIDValid(inID)) System.out.println("Check In ID not found. Please try again.");
    	} while (isCheckInIDValid(inID));
    	    	
    	System.out.println("Check Out Time:  "+ LocalTime.now().format(timeFormatter));
    	String newTime = LocalTime.now().format(timeFormatter);
    			   		    		
    	 BufferedWriter writer = new BufferedWriter(new FileWriter("CheckInRecord.txt"));
    	 for (CheckInRecord inRecord : inList) {
    		 if(inRecord.getCheckInID().equals(inID)) {
    		 inRecord.setCheckOutTime(newTime);
 			 inRecord.saveToFile();
    		 }
    		 writer.write(inRecord.getCheckInID()+"*"+inRecord.getCheckInTime()+"*"+inRecord.getCheckOutTime()+"*"+inRecord.getDate()+"*"+inRecord.getVisitorID());
    		 writer.newLine();
    	 }
    	 System.out.println("Updated Entry.");
    	 writer.close();
    }
     	
    public static boolean isCheckInIDValid(String inID) throws IOException {
    	List<CheckInRecord> inRecord = CheckInRecord.getFromFile();
 		for (CheckInRecord regClass : inRecord) {
 			if (regClass.getCheckInID().equals(inID) || regClass.getCheckInID().equals("")) {	
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
    
}
