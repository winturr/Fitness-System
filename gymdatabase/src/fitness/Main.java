package fitness;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
    	/*Staff 			ADD DISPLAY UPDATE DELETE
    	  Role				ADD DISPLAY
    	  Visitor 			ADD DISPLAY
    	  Member 			ADD DISPLAY UPDATE DELETE
    	  CheckInRecord 	ADD DISPLAY
    	  Payment 			ADD DISPLAY
    	  Class 			ADD DISPLAY UPDATE DELETE
    	  ClassRegistration ADD DISPLAY 				*/
        Staff.display();
        Visitor.display();
        Member.display();
        CheckInRecord.display(); 
        Payment.display();
        Member.add();
    }
}