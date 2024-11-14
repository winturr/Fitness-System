package fitness;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Payment{

        private String paymentID;
        private String amount;
        private String paymentDate;
        private String visitorID;
        
        public static Scanner sc = new Scanner(System.in);
        
        public Payment(String paymentID, String amount, String paymentDate, String visitorID) {
        	this.paymentID = paymentID;
        	this.amount = amount;
        	this.paymentDate = paymentDate;
        	this.visitorID = visitorID;
        }
        
        public Payment() {
        	
        }

        public String getPaymentID() {
            return paymentID;
        }
        public String getAmount() {
            return amount;
        }
        public String getPaymentDate() {
            return paymentDate;
        }
  
        public String getVisitorId() {
        	return visitorID;
        }

        public void setPaymentID (String paymentID) {
            this.paymentID = paymentID;
        }

        public void setAmount (String amount) {
            this.amount = amount;
        }

        public void setPaymentDate (String paymentDate) {
            this.paymentDate = paymentDate;
        }
         
        public void setVisitorId(String visitorID) {
        	this.visitorID = visitorID;
        }
        
        public void saveToFile() {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Payment.txt", true))) {
	            writer.write(getPaymentID() + "*" + getAmount()+ "*"+getPaymentDate()+"*"+getVisitorId());
	            writer.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
        
        
        public static List<Payment> getFromFile() {
			List<Payment> payments = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader("Payment.txt"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] data = line.split("\\*");
	                String id = data[0];
	                String amount = data[1];
	                String paymentDate = data[2];
	                String visitorId = data[3];
	                payments.add(new Payment(id, amount, paymentDate, visitorId));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return payments;
		}
        
        
        public void add() {
        	
        	System.out.println("Enter payment ID: ");
        	String paymentId = sc.nextLine();
        	setPaymentID(paymentId);
        	System.out.println("Enter Amount: ");
        	String amount = sc.nextLine();
        	setAmount(amount);
        	LocalDate date = LocalDate.now();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        	setPaymentDate(date.format(formatter));
        	
        	
        	List<? extends Visitor> visitors = Visitor.getFromFile();
        	boolean isValid = false;
        	do {
        		System.out.println("Enter valid visitor ID: ");
            	String visitorId = sc.nextLine();
        		for(Visitor visitor: visitors) {
        			if(visitor.getVisitorID().equals(visitorId)) {
        				isValid = true;
        			}
        		}setVisitorId(visitorId);
        	}while(isValid == false);
        	
        	Payment p = new Payment(paymentID, amount, paymentDate, visitorID);
        	p.saveToFile();
        	
        }

        
}