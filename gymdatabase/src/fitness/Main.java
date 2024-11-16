package fitness;
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
    		System.out.print("Enter username: ");
    		String inpUser = sc.nextLine();
    		System.out.print("Enter password: ");
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
        	System.out.print(" [1] Visitor\n [2] Member\n [3] CheckInRecord \n [4] Payment \n [5] Class Registration\n [6] Class\n [7] Staff\n [8] Role\n Select:");
        	
        	int choice = sc.nextInt();
        	
        	switch(choice) {
        	case 1:
        		boolean isVGood = true;
        		while (isVGood) {
        		Visitor.display();
        		System.out.print("\n[1]Add       [4]Create A Member\n[2]Update    [5]Payment\n[3]Delete    [6]Class Registration\n\n[7]Back\n\nSelect: ");
        		int methods = sc.nextInt();
        		if(methods == 1) {
        			Visitor.add();
        		}else if(methods == 2) {
        			Visitor.update();
        		}else if(methods == 3) {
        			Visitor.delete();
        		}else if(methods == 4) {
        			Member.add();
        		}else if(methods ==5) {
        			Payment.add();
        		}else if(methods == 6) {
        			ClassRegistration.add();
        		} else if(methods ==7) {
        			isVGood = false;
        		}
        		else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case 2: 
        		boolean isMGood = true;
        		while (isMGood) {
        		Member.display();
        		System.out.print("\n[1]Add     [3]Delete\n[2]Update  [4]Payment\n\n[5]Back\n\nSelect: ");
        		int memberMethods = sc.nextInt();
        		if(memberMethods == 1) {
        			Member.add();
        		}else if(memberMethods == 2) {
        			Member.update();
        		}else if(memberMethods == 3) {
        			Member.delete();
        		}else if(memberMethods == 4) {
        			Payment.add();
        		}else if(memberMethods ==5) {
        			isMGood=false;
        		}
        		else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case 3:
        		boolean isCIGood= true;
        		while (isCIGood) {
        		CheckInRecord.display();
        		System.out.print("\n[1]CheckIn\n[2]CheckOut\n[3]Delete\n\n[4] Back\n\nSelect: ");
        		int checkInMethods = sc.nextInt();
        		if(checkInMethods == 1) {
        			CheckInRecord.add();
        		}else if(checkInMethods == 2) {
        			CheckInRecord.checkOutTime();
        		}else if(checkInMethods ==3) {
        			//delete checkinrecord method
        		} else if(checkInMethods==4) {
        			isCIGood= false;
        		}
        		else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case 4:
        		boolean isPGood = true;
        		while(isPGood) {
        		Payment.display();
        		System.out.print("\n[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		int paymentMethods = sc.nextInt();
        		if(paymentMethods == 1) {
        			Payment.add();
        			break;
        		}else if(paymentMethods == 2) {
        			// update payment
        		}else if(paymentMethods == 3){
        			// delete payment
        		} else if(paymentMethods ==4) {
        			isPGood = false;
        		}else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case 5:
        		boolean isCRGood = true;
        		while (isCRGood) {
        		ClassRegistration.display();
        		System.out.print("\n[1]Add\n[2]Delete\n\n[3]Back\n\nSelect: ");
        		int classRegMethods = sc.nextInt();
        		if(classRegMethods == 1) {
        			ClassRegistration.add();
        		}else if (classRegMethods ==2){
        			ClassRegistration.delete();
        		} else if (classRegMethods ==3){
        			isCRGood = false;
        		}else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case 6:
        		boolean isCGood = true;
        		while (isCGood) {
        		Class.display();
        		System.out.print("\n[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		int classMethods = sc.nextInt();
        		if(classMethods == 1) {
        			Class.add();
        		}else if(classMethods == 2) {
        			Class.update();
        		}else if(classMethods == 3) {
        			Class.delete();
        		} else if(classMethods ==4) {
        			isCGood = false;
        		} else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case 7:
        		boolean isSGood = true;
        		while (isSGood) {
        		Staff.display();
        		System.out.print("[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		int staffMethods = sc.nextInt();
        		if(staffMethods == 1) {
        			Staff.add();
        		}else if(staffMethods == 2) {
        			//Staff.update();
        		}else if(staffMethods == 3) {
        			Staff.delete();
        		} else if(staffMethods ==4) {
        			isSGood = false;
        		}
        		else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case 8:
        		boolean isRGood = true;
        		while (isRGood) {
        		Role.display();
        		System.out.print("[1]Add\n[2]Update\n[3]Delete\n\n[4]Back\n\nSelect: ");
        		int roleMethods = sc.nextInt();
        		if(roleMethods == 1) {
        			Role.add();
        		}else if(roleMethods == 2) {
        			Role.update();
        		}else if (roleMethods ==3) {
        			// Delete role method
        		}else if (roleMethods==4) {
        			isRGood = false;
        		} else {
        			System.out.println("Invalid");
        		}
        		}
        		break;
        	case 9:
        		flag = 1;
        		break;
        	default:
        		System.out.println("Invalid!");
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
