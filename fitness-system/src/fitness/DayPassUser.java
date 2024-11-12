package fitness;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayPassUser extends Visitor {
	public DayPassUser(String visitorID, String name, String visitorType, String visitDate) {
		super(visitorID, name, visitorType);
		this.visitDate = visitDate;
	}
	public DayPassUser(String visitorID, String visitDate) {
		super(visitorID);
		this.visitDate = visitDate;
	}
	private String visitDate;
	
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public void saveToFile() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("DayPassUser.txt", true))) {
			writer.write(getVisitorID() + "*" + visitDate + "*");
			writer.newLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static List<DayPassUser> getsFromFile() {
		List<DayPassUser> dp = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("DayPassUser.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\*");
                String id = data[0];
                String date = data[1];
                dp.add(new DayPassUser(id, date));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dp;
	}
	public static void display() {
		List <DayPassUser> daypasser = (List<DayPassUser>) DayPassUser.getsFromFile();
		System.out.println(String.format("%s", "----------------------"));
	    System.out.println(String.format("%5s %1s %5s","VisitorID" ,"|"," VisitDate"));
	    System.out.println(String.format("%s", "----------------------"));
		for(DayPassUser vi: daypasser) {
			System.out.println(String.format("%5s %5s %5s", vi.getVisitorID(),"|", vi.getVisitDate()));
		}
	}
}