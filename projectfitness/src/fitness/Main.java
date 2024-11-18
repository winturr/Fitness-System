package fitness;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
    	Member m = new Member();
    	Visitor v = new Visitor();
    	CheckInRecord c = new CheckInRecord();
    	Class cl = new Class();
    	ClassRegistration clr = new ClassRegistration();
    	Payment p = new Payment();
    	Role r = new Role();
    	Staff s = new Staff();
    	Member.refreshMember();
    	boolean shutdown = false;
    	Scanner sc = new Scanner(System.in);
    	String username = null;
    	String password = null;
      
    	while (!shutdown) { 
    	System.out.println("------------");
    	System.out.print("[1]Login\n[2]Shutdown\n------------\nSelect: ");   	
    	String ch = sc.nextLine();

    	if (ch.equals("2")) { System.out.println("Shutdown successful.");break;} // for ending the shutdown loop
    	
    	
    	boolean isValid = false;
    	while(isValid != true) { // login
    		System.out.print("Enter username: ");
    		String inpUser = sc.nextLine(); 		
    		System.out.print("Enter password: ");
    		String inpPass = sc.nextLine();
    		try (BufferedReader reader = new BufferedReader(new FileReader("Password.txt"))) { //reading the saved password
				String line;
				while ((line = reader.readLine()) != null) {
				    String[] data = line.split("\\*");
				    username = data[0];
				    password = data[1];
				}
			}
    		if(inpUser.equals(username) && inpPass.equals(password)) { //if username and password is correct
    			System.out.println("Welcome!");
    			isValid = true;
    		}else {
    			System.out.println("Wrong Password or Username!");
    		}
    	}
    	
    	int flag=0;
    	while(flag!=1) { 
    		System.out.println("---------------------------");
        	System.out.println(" DXTREME Visitation System");
        	System.out.println("---------------------------");
        	System.out.print(" [1] Visitor\n [2] Member\n [3] CheckInRecord \n [4] Payment \n [5] Class Registration\n [6] Class\n [7] Staff\n [8] Role\n [9] Logout\n [10] Change Username and Password\n Select: ");
        	
        	String choice = sc.nextLine();
        	
        	switch(choice) {
        	case "1": // menu
        		boolean isVGood = true;
        		do {
        		v.display();
        		System.out.print("\n[1]Add       [4]Create A Member\n[2]Update    [5]Payment\n[3]Delete    [6]Class Registration\n\n[7]Back\n\nSelect: ");
        		String methods = sc.nextLine();
        		if(methods.equals("1")) {
        			v.add();
        		}else if(methods.equals("2")) {
        			Visitor.update();
        		}else if(methods.equals("3")) {
        			v.delete();
        		}else if(methods.equals("4")) {
        			m.add();
        		}else if(methods.equals("5")) {
        			p.add();
        		}else if(methods.equals("6")) {
        			clr.add();
        		}else if(methods.equals("7")) {
        			isVGood = false; 	
        			break;
        		} else {
        			System.out.println("Invalid");
        			continue;
        		}
        		} while (isVGood);
        		break;
        	case "2":  // member
        		boolean isMGood = true;
        		while (isMGood) {
        		m.display();
        		System.out.print("\n[1]Add     [4]Payment\n[2]Update  [5]Renew\n[3]Delete\n\n[6]Back\n\nSelect: ");
        		String memberMethods = sc.nextLine();
        		if(memberMethods.equals("1")) {
        			m.add();
        		}else if(memberMethods.equals("2")) {
        			Member.update();
        		}else if(memberMethods.equals("3")) {
        			m.delete();
        		}else if(memberMethods.equals("4")) {
        			p.add();
        		}else if(memberMethods.equals("5")) {
        			Member.renew();
        		}else if (memberMethods.equals("6")) {
        			isMGood=false;
        			break;
        		}else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "3": // check in records
        		boolean isCIGood= true;
        		while (isCIGood) {
        		c.display();
        		System.out.print("\n[1]CheckIn\n[2]CheckOut\n[3]Delete\n\n[4] Back\n\nSelect: ");
        		String checkInMethods = sc.nextLine();
        		if(checkInMethods.equals("1")) {
        			c.add();
        		}else if(checkInMethods.equals("2")) {
        			CheckInRecord.checkOutTime();
        		}else if(checkInMethods.equals("3")) {
        			c.delete();
        		} else if(checkInMethods.equals("4")) {
        			isCIGood= false;
        			break;
        		}
        		else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "4": // payment
        		boolean isPGood = true;
        		while(isPGood) {
        		p.display();
        		System.out.print("\n[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		String paymentMethods = sc.nextLine();
        		if(paymentMethods.equals("1")) {
        			p.add();
        		}else if(paymentMethods.equals("2")) {
        			Payment.update();
        		}else if(paymentMethods.equals("3")){
        			p.delete();
        		} else if(paymentMethods.equals("4")) {
        			isPGood = false;
        			break;
        		}else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "5": // class registration
        		boolean isCRGood = true;
        		while (isCRGood) {
        			clr.display();
        		System.out.print("\n[1]Add\n[2]Delete\n\n[3]Back\n\nSelect: ");
        		String classRegMethods = sc.nextLine();
        		if(classRegMethods.equals("1")) {
        			clr.add();
        		}else if (classRegMethods.equals("2")){
        			clr.delete();
        		} else if (classRegMethods.equals("3")){
        			isCRGood = false;
        			break;
        		}else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "6": // class
        		boolean isCGood = true;
        		while (isCGood) {
        		cl.display();
        		System.out.print("\n[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		String classMethods = sc.nextLine();
        		if(classMethods.equals("1")) {
        			cl.add();
        		}else if(classMethods.equals("2")) {
        			Class.update();
        		}else if(classMethods.equals("3")) {
        			cl.delete();
        		} else if(classMethods.equals("4")) {
        			isCGood = false;
        			break;
        		} else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "7": // staff
        		boolean isSGood = true;
        		while (isSGood) {
        		s.display();
        		System.out.print("[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		String staffMethods = sc.nextLine();
        		if(staffMethods.equals("1")) {
        			s.add();
        		}else if(staffMethods.equals("2")) {
        			Staff.update();
        		}else if(staffMethods.equals("3")) {
        			s.delete();
        		} else if(staffMethods.equals("4")) {
        			isSGood = false;
        			break;
        		}
        		else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "8": // role
        		boolean isRGood = true;
        		while (isRGood) {
        		r.display();
        		System.out.print("[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		String roleMethods = sc.nextLine();
        		if(roleMethods.equals("1")) {
        			r.add();
        		}else if(roleMethods.equals("2")) {
        			Role.update();
        		}else if (roleMethods.equals("3")) {
        			r.delete();
        		}else if (roleMethods.equals("4")) {
        			isRGood = false;
        			break;
        		} else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case "9": // logout
        		flag = 1;
        		System.out.println("-----------");
        		System.out.println("Logged Out");
        		System.out.println("-----------\n");
        		break;
        	case "10": // change username and password 
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
            		if(inpUser.equals(username) && inpPass.equals(password)) {// check if the password is correct
            			System.out.print("Enter new Username: ");
            			String newUser = sc.nextLine();
            			System.out.print("Enter new Password: ");            			
            			String newPass = sc.nextLine();
            			try (BufferedWriter writer = new BufferedWriter(new FileWriter("Password.txt", false))) { //rewriting with new password
							writer.write(newUser+"*"+newPass);
						}
            			System.out.println("Username and Password Updated Successfully");
            			isUpdateValid = true;
            		}else {
            			System.out.println("Wrong Password or Username!"); // if the user doesnt want to change username or password anymore
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
