package fitness;
import java.io.IOException;
import java.util.Scanner;


import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
    	/*Staff 			ADD DISPLAY UPDATE DELETE
    	  Role				ADD DISPLAY UPDATE
    	  Visitor 			ADD DISPLAY UPDATE
    	  Member 			ADD DISPLAY UPDATE DELETE
    	  CheckInRecord 	ADD DISPLAY UPDATE
    	  Payment 			ADD DISPLAY UPDATE
    	  Class 			ADD DISPLAY UPDATE DELETE
    	  ClassRegistration ADD DISPLAY UPDATE		 */
        /*Staff.display();
        Visitor.display():
        Member.display();
        CheckInRecord.display(); 
        Payment.display();
        Member.add();*/
    	
    	Scanner sc = new Scanner(System.in);
    	String username = "admin";
    	String password = "admin1234";
    	
    	Boolean isValid = false;
    	while(isValid != true) {
    		System.out.println("Enter username: ");
    		String inpUser = sc.nextLine();
    		System.out.println("Enter password");
    		String inpPass = sc.nextLine();
    		if(inpUser.equals(username) && inpPass.equals(password)) {
    			System.out.println("Welcome!");
    			isValid = true;
    		}else {
    			System.out.println("Error!");
    		}
    		
    	}
    	
    	
    	
    	int flag=0;
    	while(flag!=1) {
    		System.out.println("----------------------------");
        	System.out.println("LeFitness Visitation System");
        	System.out.println("----------------------------");
        	System.out.println(" [1] Visitor"
        						+"\n"+" [2] Member"
        						+"\n"+" [3] CheckInRecord"
        						+"\n"+" [4] Payment"
        						+"\n"+" [5] Class Registration"
        						+"\n"+" [6] Class"
        						+"\n"+" [7] Staff"
        						+"\n"+" [8] Role");
        	
        	int choice = sc.nextInt();
        	
        	switch(choice) {
        	case 1:
        		Visitor.display();
        		System.out.println("Add[1], Update[2], Delete[3]");
        		System.out.println("Create A member[4], Payment[5], Class Registration[6]");
        		int methods = sc.nextInt();
        		if(methods == 1) {
        			Visitor.add();
        			break;
        		}else if(methods == 2) {
        			Visitor.update();
        			break;
        		}else if(methods == 3) {
        			Visitor.delete();
        			break;
        		}else if(methods == 4) {
        			Member.add();
        			break;
        		}else if(methods ==5) {
        			Payment.add();
        			break;
        		}else if(methods == 6) {
        			ClassRegistration.add();
        			break;
        		}
        		else {
        			System.out.println("Invalid");
        			continue;
        		}
        	case 2: 
        		Member.display();
        		System.out.println("Add[1], Update[2], Delete[3]"
        							+"\n"+"Payment[4]");
        		int memberMethods = sc.nextInt();
        		if(memberMethods == 1) {
        			Member.add();
        			break;
        		}else if(memberMethods == 2) {
        			Member.update();
        			break;
        		}else if(memberMethods == 3) {
        			Member.delete();
        			break;
        		}else if(memberMethods == 4) {
        			Payment.add();
        			break;
        		}	
        		else {
        			System.out.println("Invalid");
        			continue;
        		}
        	case 3:
        		CheckInRecord.display();
        		System.out.println("CheckIn[1], CheckOut[2]");
        		int checkInMethods = sc.nextInt();
        		if(checkInMethods == 1) {
        			CheckInRecord.add();
        			break;
        		}else if(checkInMethods == 2) {
        			CheckInRecord.checkOutTime();
        			break;
        		}	
        		else {
        			System.out.println("Invalid");
        			continue;
        		}
        	case 4:
        		Payment.display();
        		System.out.println("Add[1], Update[2]");
        		int paymentMethods = sc.nextInt();
        		if(paymentMethods == 1) {
        			Payment.add();
        			break;
        		}else if(paymentMethods == 2) {
        			//
        			break;
        		}else {
        			System.out.println("Invalid");
        			continue;
        		}
        	case 5:
        		ClassRegistration.display();
        		System.out.println("Add[1], Update[2]");
        		int classRegMethods = sc.nextInt();
        		if(classRegMethods == 1) {
        			ClassRegistration.add();
        		}else {
        			System.out.println("Invalid");
        		}
        	case 6:
        		Class.display();
        		System.out.println("Add[1], Update[2], Delete[3]");
        		int classMethods = sc.nextInt();
        		if(classMethods == 1) {
        			Class.add();
        			break;
        		}else if(classMethods == 2) {
        			Class.update();
        			break;
        		}else if(classMethods == 3) {
        			Class.delete();
        			break;
        		}
        		else {
        			System.out.println("Invalid");
        			continue;
        		}
        	case 7:
        		Staff.display();
        		System.out.println("Add[1], Update[2], Delete[3]");
        		int staffMethods = sc.nextInt();
        		if(staffMethods == 1) {
        			Staff.add();
        			break;
        		}else if(staffMethods == 2) {
        			//Staff.update();
        			break;
        		}else if(staffMethods == 3) {
        			Staff.delete();
        			break;
        		}
        		else {
        			System.out.println("Invalid");
        			continue;
        		}
        		
        	case 8:
        		Role.display();
        		System.out.println("Add[1], Update[2]");
        		int roleMethods = sc.nextInt();
        		if(roleMethods == 1) {
        			Role.add();
        		}else if(roleMethods == 2) {
        			Role.update();
        			break;
        		}
        		else {
        			System.out.println("Invalid");
        			break;
        		}
        	case 9:
        		flag = 1;
        		break;
        	default:
        		System.out.println("INvalid!");
        	}
    	}
    	sc.close();

    }
}

/*Staff 			ADD DISPLAY UPDATE DELETE //
Role				ADD DISPLAY UPDATE //
Visitor 			ADD DISPLAY UPDATE //
Member 			ADD DISPLAY UPDATE DELETE//
CheckInRecord 	ADD DISPLAY UPDATE //
Payment 			ADD DISPLAY UPDATE
Class 			ADD DISPLAY UPDATE DELETE //
ClassRegistration ADD DISPLAY	//	         */