package fitness;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner i = new Scanner(System.in);
		System.out.print("Enter Visitor ID: ");
		String visitorID = i.nextLine();
		System.out.print("Enter Visitor Name: ");
		String visitorName = i.nextLine();
		System.out.print("Enter Visitor Type, [M] Member [D] Day Pass: ");
		String visitorType = i.nextLine();
		Visitor v = new Visitor(visitorID, visitorName, visitorType);
		v.saveToFile();
		switch (visitorType) {
		case "D":
			LocalDate date = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String visitDate = date.format(formatter);
	        DayPassUser dp = new DayPassUser(visitorID,visitorName,visitorType,visitDate);
	        dp.saveToFile();
	        System.out.println("Saved to Visitor.txt and DayPassUser.txt");
	        break;
		}
		List <Visitor> visitors = (List<Visitor>) Visitor.getFromFile();
		System.out.format("%-13s%-20s%-15s%n","VisitorID" ," Name" ," VisitorType");
		for(Visitor vi: visitors) {
			System.out.format("%-15s%-20s%-15s%n", vi.getVisitorID() ,vi.getName() , vi.getVisitorType());
		}
		System.out.println();
		List <DayPassUser> daypasser = (List<DayPassUser>) DayPassUser.getsFromFile();
		System.out.format("%-15s%-15s%n","VisitorID" ," VisitDate");
		for(DayPassUser vi: daypasser) {
			System.out.format("%-15s%-15s%n", vi.getVisitorID(), vi.getVisitDate());
		}
		System.out.println();
		System.out.print("Enter Staff ID: ");
		String staffID = i.nextLine();
		System.out.print("Enter Staff Name: ");
		String name = i.nextLine();
		System.out.print("Enter Staff Role: ");
		String role = i.nextLine();
		System.out.print("Enter ContactInfo: ");
		String contactInfo = i.nextLine();
		Staff s = new Staff(staffID, name, role, contactInfo);
		s.saveToFile();
		List <Staff> staff = (List<Staff>) Staff.getFromFile();
		System.out.format("%-15s%-15s%-15s%-15s%n","StaffID" ,"Name","Role","ContactInfo");
		for(Staff st: staff) {
			System.out.format("%-15s%-15s%-15s%-15s%n", st.getStaffID(),st.getName(),st.getRole(),st.getContactInfo());
		}
	}

}
