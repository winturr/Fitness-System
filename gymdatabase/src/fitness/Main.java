package fitness;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

    	boolean shutdown = false;
    	Scanner sc = new Scanner(System.in);
    	String username = null;
    	String password = null;
      
    	while (!shutdown) {
    	System.out.println("------------");
    	System.out.print("[1]Login\n[2]Shutdown\n------------\nSelect: ");   	
    	String ch = sc.nextLine();

    	if (ch.equals("2")) break;
    	
    	
    	Boolean isValid = false;
    	while(isValid != true) {
    		System.out.print("Enter username: ");
    		String inpUser = sc.nextLine(); 		
    		System.out.print("Enter password: ");
    		String inpPass = sc.nextLine();
    		try (BufferedReader reader = new BufferedReader(new FileReader("Password.txt"))) {
				String line;
				while ((line = reader.readLine()) != null) {
				    String[] data = line.split("\\*");
				    username = data[0];
				    password = data[1];
				}
			}
    		if(inpUser.equals(username) && inpPass.equals(password)) {
    			System.out.println("Welcome!");
    			isValid = true;
    		}else {
    			System.out.println("Wrong Password or Username!");
    		}
    	}
    	
    	
    	
    	int flag=0;
    	while(flag!=1) {
    		System.out.println("----------------------------");
        	System.out.println("LeFitness Visitation System");
        	System.out.println("----------------------------");
        	System.out.print(" [1] Visitor\n [2] Member\n [3] CheckInRecord \n [4] Payment \n [5] Class Registration\n [6] Class\n [7] Staff\n [8] Role\n [9] Logout\n [10] Change Username and Password\n Select: ");
        	
        	String choice = sc.nextLine();
        	
        	switch(choice) {
        	case "1":
        		boolean isVGood = true;
        		do {
        		Visitor.display();
        		System.out.print("\n[1]Add       [4]Create A Member\n[2]Update    [5]Payment\n[3]Delete    [6]Class Registration\n\n[7]Back\n\nSelect: ");
        		String methods = sc.nextLine();
        		if(methods.equals("1")) {
        			Visitor.add();
        		}else if(methods.equals("2")) {
        			Visitor.update();
        		}else if(methods.equals("3")) {
        			Visitor.delete();
        		}else if(methods.equals("4")) {
        			Member.add();
        		}else if(methods.equals("5")) {
        			Payment.add();
        		}else if(methods.equals("6")) {
        			ClassRegistration.add();
        		}else if(methods.equals("7")) {
        			isVGood = false; 	
        			break;
        		} else {
        			System.out.println("Invalid");
        			continue;
        		}
        		} while (isVGood);
        		break;
        	case "2": 
        		boolean isMGood = true;
        		while (isMGood) {
        		Member.display();
        		System.out.print("\n[1]Add     [3]Delete\n[2]Update  [4]Payment\n\n[5]Back\n\nSelect: ");
        		String memberMethods = sc.nextLine();
        		if(memberMethods.equals("1")) {
        			Member.add();
        		}else if(memberMethods.equals("2")) {
        			Member.update();
        		}else if(memberMethods.equals("3")) {
        			Member.delete();
        		}else if(memberMethods.equals("4")) {
        			Payment.add();
        		}else if(memberMethods.equals("5")) {
        			isMGood=false;
        			break;
        		}
        		else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "3":
        		boolean isCIGood= true;
        		while (isCIGood) {
        		CheckInRecord.display();
        		System.out.print("\n[1]CheckIn\n[2]CheckOut\n[3]Delete\n\n[4] Back\n\nSelect: ");
        		String checkInMethods = sc.nextLine();
        		if(checkInMethods.equals("1")) {
        			CheckInRecord.add();
        		}else if(checkInMethods.equals("2")) {
        			CheckInRecord.checkOutTime();
        		}else if(checkInMethods.equals("3")) {
        			CheckInRecord.delete();
        		} else if(checkInMethods.equals("4")) {
        			isCIGood= false;
        			break;
        		}
        		else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "4":
        		boolean isPGood = true;
        		while(isPGood) {
        		Payment.display();
        		System.out.print("\n[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		String paymentMethods = sc.nextLine();
        		if(paymentMethods.equals("1")) {
        			Payment.add();
        		}else if(paymentMethods.equals("2")) {
        			Payment.update();
        		}else if(paymentMethods.equals("3")){
        			Payment.delete();
        		} else if(paymentMethods.equals("4")) {
        			isPGood = false;
        			break;
        		}else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "5":
        		boolean isCRGood = true;
        		while (isCRGood) {
        		ClassRegistration.display();
        		System.out.print("\n[1]Add\n[2]Delete\n\n[3]Back\n\nSelect: ");
        		String classRegMethods = sc.nextLine();
        		if(classRegMethods.equals("1")) {
        			ClassRegistration.add();
        		}else if (classRegMethods.equals("2")){
        			ClassRegistration.delete();
        		} else if (classRegMethods.equals("3")){
        			isCRGood = false;
        			break;
        		}else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "6":
        		boolean isCGood = true;
        		while (isCGood) {
        		Class.display();
        		System.out.print("\n[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		String classMethods = sc.nextLine();
        		if(classMethods.equals("1")) {
        			Class.add();
        		}else if(classMethods.equals("2")) {
        			Class.update();
        		}else if(classMethods.equals("3")) {
        			Class.delete();
        		} else if(classMethods.equals("4")) {
        			isCGood = false;
        			break;
        		} else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "7":
        		boolean isSGood = true;
        		while (isSGood) {
        		Staff.display();
        		System.out.print("[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		String staffMethods = sc.nextLine();
        		if(staffMethods.equals("1")) {
        			Staff.add();
        		}else if(staffMethods.equals("2")) {
        			Staff.update();
        		}else if(staffMethods.equals("3")) {
        			Staff.delete();
        		} else if(staffMethods.equals("4")) {
        			isSGood = false;
        			break;
        		}
        		else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "8":
        		boolean isRGood = true;
        		while (isRGood) {
        		Role.display();
        		System.out.print("[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		String roleMethods = sc.nextLine();
        		if(roleMethods.equals("1")) {
        			Role.add();
        		}else if(roleMethods.equals("2")) {
        			Role.update();
        		}else if (roleMethods.equals("3")) {
        			Role.delete();
        		}else if (roleMethods.equals("4")) {
        			isRGood = false;
        			break;
        		} else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "9":
        		flag = 1;
        		System.out.println("-----------");
        		System.out.println("Logged Out");
        		System.out.println("-----------\n");
        		break;
        	case "10":
        		Boolean isUpdateValid = false;
            	while(isUpdateValid != true) {
            		System.out.print("Enter current username: ");
            		String inpUser = sc.nextLine(); 		
            		System.out.print("Enter current password: ");
            		String inpPass = sc.nextLine();
            		try (BufferedReader reader = new BufferedReader(new FileReader("Password.txt"))) {
						String line;
						while ((line = reader.readLine()) != null) {
						    String[] data = line.split("\\*");
						    username = data[0];
						    password = data[1];
						}
					}
            		if(inpUser.equals(username) && inpPass.equals(password)) {
            			System.out.print("Enter new Username: ");
            			String newUser = sc.nextLine();
            			System.out.print("Enter new Password: ");            			
            			String newPass = sc.nextLine();
            			try (BufferedWriter writer = new BufferedWriter(new FileWriter("Password.txt", false))) {
							writer.write(newUser+"*"+newPass);
						}
            			System.out.println("Username and Password Updated Successfully");
            			isUpdateValid = true;
            		}else {
            			System.out.println("Wrong Password or Username!");
            			System.out.print("[1]Retry\n[2]Back\nSelect: ");
            			String wrong = sc.nextLine();
            			if (wrong.equals("2")) {
                			break;
            			}
            				
            		}
            	}
            	
        		break;
        	default:
        		System.out.println("Invalid!");
        	}
    	}
    	

    }
    	sc.close();
    }
}

