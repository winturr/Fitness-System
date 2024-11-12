package fitness;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner i = new Scanner(System.in);
		Visitor v = new Visitor();
		Member m = new Member();
		DayPassUser d = new DayPassUser();
		//
		System.out.println("--------------LOGIN--------------");
		System.out.println("------------MOCK ONLY------------");
		System.out.print("Enter Username: ");
		String username = i.nextLine();
		System.out.print("Enter Password: ");
		String password = i.nextLine();
		System.out.println();
		v.display();	
		d.display();
		m.display();
		v.update();
	}

}
