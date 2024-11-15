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
        
        public static Scanner i = new Scanner(System.in);
        
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
  
        public String getVisitorID() {
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
         
        public void setVisitorID(String visitorID) {
        	this.visitorID = visitorID;
        }
        
        public void saveToFile() {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Payment.txt", true))) {
	            writer.write(getPaymentID() + "*" + getAmount()+ "*"+getPaymentDate()+"*"+getVisitorID()+"*");
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
        
        
        public static void add() {
        	System.out.print("Enter payment ID: ");
        	String paymentID = i.nextLine();
        	System.out.print("Enter Amount: ");
        	String amount = i.nextLine();
        	LocalDate date = LocalDate.now();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     
        	List<Visitor> visitors = Visitor.getFromFile();
        	boolean isValid = false;
        	String visitorID;
        	do {
        		System.out.print("Enter valid visitor ID: ");
            	visitorID = i.nextLine();
        		for(Visitor visitor: visitors) {
        			if(visitor.getVisitorID().equals(visitorID)) {
        				isValid = true;
        			}
        		}
        	}while(isValid == false);
        	
        	Payment p = new Payment(paymentID, amount, date.format(formatter), visitorID);
        	p.saveToFile();
        	
        }
        public static void display() {
        	List <Payment> paymentList = (List<Payment>) Payment.getFromFile();
            System.out.println(String.format("%s", "---------------------------------------------------------"));
            System.out.println(String.format("%5s %3s %5s %5s %10s %7s %10s","PaymentID" ,"|","Amount","|","PaymentDate","|","VisitorID"));
            System.out.println(String.format("%s", "---------------------------------------------------------"));
            for(Payment p: paymentList) {
                System.out.println(String.format("%9s %3s %6s %5s %10s %8s %10s", p.getPaymentID(), "|", p.getAmount(),"|", p.getPaymentDate(),"|", p.getVisitorID()));

            }
        }
        public static void delete()throws IOException{
            List<Payment> paymentList = Payment.getFromFile();//.txt file into ArrayList
            System.out.print("Look for ID: ");
            String id = i.nextLine();
            System.out.print("Are you sure you want to delete " + id + "?\n[1]Yes [2]No --> ");
            String deleteConfirm = i.nextLine();
            switch(deleteConfirm) {
                case "1"://case 1 / "YES"
                    BufferedWriter writer = new BufferedWriter(new FileWriter("Payment.txt"));//rewrites .txt file
                    for (Payment payment : paymentList) {
                        if(!payment.getPaymentID().equals(id)) {//skips VisitorID input from being written
                            writer.write(payment.getPaymentID() + "*" + payment.getAmount() + "*" + payment.getPaymentDate()+ "*" + payment.getVisitorID() + "*");
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
}
