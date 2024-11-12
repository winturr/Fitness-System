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
		Staff s = new Staff();
		v.display();	
		d.display();
		m.display();
		s.display();
		
	}

}
