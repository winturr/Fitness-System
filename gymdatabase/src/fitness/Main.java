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
        Staff.display();
        Visitor.display();
        Member.display();
        CheckInRecord.display(); 
        Payment.display();
        Member.add();
    }
}
