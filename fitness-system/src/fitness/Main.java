package fitness;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner i = new Scanner(System.in);
		Visitor.display();
		DayPassUser.display();
		Member.display();
		List<Visitor> visitors = Visitor.getFromFile();
		System.out.print("Look for ID");
		String ni = i.nextLine();
		System.out.print("Enter new name");
		String nn = i.nextLine();
		System.out.print("Enter new type");
		String nt = i.nextLine();
		Visitor.updateVisitor(visitors, ni, nn, nt);
		
	}

}
