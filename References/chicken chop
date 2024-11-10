package pack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class projtry {

	public static void main(String[] args) throws IOException {
		
		 //CHOICE
		 int flag = 0;
		 Scanner inp = new Scanner(System.in);
		 while (flag == 0) {
		 
		 
		 System.out.print("\n[1]Add\n[2]Update\n[3]Archive\n[4]Search\n[5]Exit\nEnter: ");
		 
		 String ch = inp.nextLine();
		 
		 switch (ch) {
		 	case "1":
		 		add();
		 		break;
		 	case "2":
		 		update();
		 		break;
		 	case "3":
		 		archive();
		 		break;
		 	case "4":
		 		search();
		 		break;
		 	case "5":
		 		flag = 1;
		 		break;
		 }
		
		 }
		 
		
		 
	}
	
	
	
	
	public static void add() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("Newstudent.txt",true));
		Scanner inp = new Scanner(System.in);
		System.out.print("ID: ");
		String id = inp.nextLine();
		System.out.print("NAME: ");
		String name = inp.nextLine();
		System.out.print("[1]st Year\n[2]nd Year\n[3]rd Year\n[4]th Year\nYear: ");
		String chYear = inp.nextLine();
		String year = "";
			switch (chYear) {
			 case "1":
				 year = "1st Year";
				 break;
			 case "2":
				 year = "2nd Year";
				 break;
			 case "3":
				 year = "3rd Year";
				 break;
			 case "4":
				 year = "4th Year";
				 break;				 
			}
		System.out.println("[1]Active\n[2]Inactive");
		System.out.print("STATUS: ");
		String chStatus = inp.nextLine();
		String status = "";
			if (chStatus.equals("1")) {
				status =  "Active";
			} else if (chStatus.equals("2")) {
				status = "Inactive";
			}
		System.out.print(id + "*"+name+"*"+year+"*"+status + "\nStudent Successfully Added");
		writer.write("\n" + id + "*"+name+"*"+year+"*"+status);
		writer.close();
		
		
	}
	
	public static void update() throws IOException {
		
		Scanner inp = new Scanner(System.in);
		System.out.print("Id want to update: ");
		String upid = inp.nextLine();
		
		File tempFile = new File ("tempFile.txt");
		File inputFile = new File ("Newstudent.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));
		
		String line;
		while ((line = reader.readLine()) != null) {
			tempWriter.write(line + "\n");
		}
		
		reader.close();
		tempWriter.close();
		
		BufferedReader tempReader = new BufferedReader(new FileReader(tempFile));
		BufferedWriter inputWriter = new BufferedWriter(new FileWriter(inputFile));	
		
		String[] field = null;
		while ((line = tempReader.readLine()) != null){
			field = line.split("\\*");
				if (field[0].equals(upid) && field.length >= 3) {
					System.out.println("Found: " + line);
					System.out.print("New Name: ");
					String newName = inp.nextLine();
					System.out.print("[1]st Year\n[2]nd Year\n[3]rd Year\n[4]th Year\nNew Year: ");
					String ch2 = inp.nextLine();
					String newYear = "";
						switch (ch2) {
						case "1":
							newYear = "1st Year";
							break;
						case "2":
							newYear = "2nd Year";
							break;
						case "3":
							newYear = "3rd Year";
							break;
						case "4":
							newYear = "4th Year";
							break;
						}
						line = upid + "*"+ newName + "*"+ newYear + "*"+ field[3];
						System.out.print("Updating record to: " + line);
				}
				inputWriter.write(line + "\n");
		}
		tempReader.close();
		inputWriter.close();
		
		tempFile.delete();
	}
	
	public static void archive() throws IOException {
		Scanner inp = new Scanner(System.in);
		System.out.print("Id want to Archive: ");
		String upStatus = inp.nextLine();
		
		File tempFile = new File ("tempFile.txt");
		File inputFile = new File ("Newstudent.txt");
		
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));
		
		String line;
		while ((line = reader.readLine()) != null) {
			tempWriter.write(line + "\n");
		}
		
		reader.close();
		tempWriter.close();
		
		BufferedReader tempReader = new BufferedReader(new FileReader(tempFile));
		BufferedWriter inputWriter = new BufferedWriter(new FileWriter(inputFile));	
	
		String newStatus = "";
		String[] field = null;
		while ((line = tempReader.readLine()) != null){
			field = line.split("\\*");
				if (field[0].equals(upStatus) && field.length >= 3) {
					if (field[3].equals("Active")) {
						newStatus = "Inactive";
						line = field[0]+"*"+field[1]+"*"+field[2]+"*"+newStatus;
							System.out.print(line);
					} else if (field[3].equals("Inactive")) {
						newStatus = "Active";
						line = field[0]+"*"+field[1]+"*"+field[2]+"*"+newStatus;
						System.out.print(line);
					}
				}
			inputWriter.write(line + "\n");
		}
		
		tempReader.close();
		inputWriter.close();
		
		tempFile.delete();
	}
	
	public static void search() throws IOException {
		Scanner inp = new Scanner(System.in);
		System.out.print("\n[1]All\n[2]Name\n[3]Year\n[4]Status\nFilter: ");
		String filter = inp.nextLine();
			switch (filter) {
				case "1":
					all();
					break;
				case "2":
					name();
					break;
				case "3":
					year();
					break;
				case "4":
					status();
					break;
			}
		
	}
	
	public static void all() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("Newstudent.txt"));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] field = line.split("\\*");
			if (field.length >= 3) {
				System.out.println("ID:" + field[0] + " NAME:" + field[1] + " YEAR:" + field[2] + " STATUS:" + field[3]);
			}
		}
		reader.close();
		
	}
	
	public static void name() throws IOException {
		Scanner inp = new Scanner(System.in);
		System.out.print("Name you want to search: ");
		String name = inp.nextLine();
		
		BufferedReader reader = new BufferedReader(new FileReader("Newstudent.txt"));
		String line;
		
		while((line = reader.readLine()) != null ) {
			String[] field = line.split("\\*");
				if (field[1].equals(name) && field.length >=3) {
					System.out.print(field[0]+"*"+field[1]+"*"+field[2]+"*"+field[3]);
				}
		}
		reader.close();
	
	}
	
	public static void year() throws IOException {
		Scanner inp = new Scanner(System.in);
		System.out.println("[1]st Year\n[2]nd Year\n[3]rd Year\n[4]th Year");
		System.out.print("Year you want to search: ");
		String choice = inp.nextLine();
		String year = "";
			switch (choice) {
			case "1":
				year = "1st Year";
				break;
			case "2":
				year = "2nd Year";
				break;
			case "3":
				year = "3rd Year";
				break;
			case "4":
				year ="4th Year";
				break;
			}
			
		
		BufferedReader reader = new BufferedReader(new FileReader("Newstudent.txt"));
		String line;
		
		while((line = reader.readLine()) != null ) {
			String[] field = line.split("\\*");
				if (field[2].equals(year) && field.length >=3) {
					System.out.println(field[0]+"*"+field[1]+"*"+field[2]+"*"+field[3]);
				}
			
		}
		reader.close();
		
	}
	
	public static void status() throws IOException {
		Scanner inp = new Scanner(System.in);
		System.out.println("[1]Active\n[2]Inactive");
		System.out.print("Status you want to search: ");
		String choice = inp.nextLine();
		String status = "";
			switch (choice) {
			case "1":
				status = "Active";
				break;
			case "2":
				status = "Inactive";
				break;
			}
			
		
		BufferedReader reader = new BufferedReader(new FileReader("Newstudent.txt"));
		String line;
		
		while((line = reader.readLine()) != null ) {
			String[] field = line.split("\\*");
				if (field[3].equals(status) && field.length >=3) {
					System.out.println(field[0]+"*"+field[1]+"*"+field[2]+"*"+field[3]);
				}
			
		}
		reader.close();
	}
	
}
		
		
	
	
