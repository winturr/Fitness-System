public class ClassRegistration {
    private String registrationID,registrationDate,status;
    LocalDate date = LocalDate.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public boolean isGood = true;
    public static Scanner inp = new Scanner(System.in);
    
	public ClassRegistration(String registrationID, String status) {
    	this.registrationID=registrationID;
    	this.registrationDate = LocalDate.now().format(formatter);
    	this.status=status;
    }
	public ClassRegistration(String regID, String date2, String status2) {
		registrationID = regID;
		registrationDate = date2;
		status = status2;
	}

	public String getRegistrationID(){
    return registrationID;
    }
   
    public String getRegistrationDate(){
    return registrationDate;
    }
    
    public String getStatus(){
    return status;
      }
    
    public void setRegistrationID(String registrationID) {
        this.registrationID=registrationID;
    }
    
     public void setRegistrationDate(String registrationDate) {
        this.registrationDate=registrationDate;
    }
     
    public void setStatus(String status) {
        this.status=status;
    }
    
    public void saveToFile() { // pang write txt
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("ClassRegistration.txt", true))) {
			
			writer.write(registrationID + "*" + registrationDate + "*" + status + "*");
			writer.newLine();															
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
    
    public static List<ClassRegistration> getFromFile() { //read the txt file and put it into array to array list
		List<ClassRegistration> classreg = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ClassRegistration.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\*");
                String regID = data[0];
                String date = data[1];
                String status = data[2];
                classreg.add(new ClassRegistration(regID,date,status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classreg;		     
	}
    
    public void checkDuplicateID(String inputID) throws IOException {    	    	
    		boolean isDuplicate = false;
			try (BufferedReader reader = new BufferedReader(new FileReader("ClassRegistration.txt"))) {
				String line;
				
					while((line = reader.readLine()) != null ) {
					String[]data=line.split("\\*");
						if (data[0].equals(inputID) && data.length >=2) {
						System.out.println("Registration ID "+data[0]+" already exists.");
						isDuplicate=true;
						isGood=false;
						return;								
						}
					}
			}
				if(!isDuplicate) {
						saveToFile();
				}												
	}
    
    
    public static void updateStatus() throws IOException {
    	List<ClassRegistration> ClassRegistrationList = ClassRegistration.getFromFile();
	    	System.out.print("Look for ID: ");
			String id = inp.nextLine();
			System.out.println("New Status");
			boolean isRight = true;
			
			
			String newStatus=null;
			 do { // new status with specs pag tanga si user
	                System.out.print("[1]Registered\n[2]Waitlisted\n[3]Attended\nStatus: ");
	                String ch = inp.nextLine(); //Status
	                isRight =true;
	                switch (ch) {
	                    case "1":
	                        newStatus ="Registered";
	                        break;
	                    case "2":
	                        newStatus="Waitlisted";
	                        break;
	                    case "3":
	                        newStatus="Attended";
	                        break;
	                    default:
	                        System.out.println("Enter 1 for Registered 2 for Waitlisted and 3 for Attended");
	                        isRight=false;                       
	                }
	            } while (!isRight);
			
			 boolean isExist = false;
			for (ClassRegistration classreg: ClassRegistrationList) {
				if (classreg.getRegistrationID().equals(id)) {
					classreg.setStatus(newStatus);
					classreg.saveToFile();	
					isExist = true;
					break;
				} 
	        }
			
			
			if (isExist) {
			BufferedWriter writer = new BufferedWriter(new FileWriter("ClassRegistration.txt"));
			 for (ClassRegistration classReg : ClassRegistrationList) {
		        	writer.write(classReg.getRegistrationID() + "*" + classReg.getRegistrationDate() + "*" + classReg.getStatus());
		            writer.newLine();
		        }
			writer.close();
			System.out.println("Data Successfully Deleted");
			} else {
				System.out.println("Data doesn't Exist");
			}
	    
    }
    
    
public static void display() {
	List <ClassRegistration> classreg = (List<ClassRegistration>) ClassRegistration.getFromFile();
	System.out.println(String.format("%5s %2s %15s %3s %11s"," Reigstration ID","|","  Registration Date","|","Status"));
	System.out.println(String.format("%s", "-------------------------------------------------------------"));
	for(ClassRegistration cl: classreg) {
		System.out.format("%10s %8s %15s %7s %13s", cl.getRegistrationID(), "|", cl.getRegistrationDate(),"|", cl.getStatus());
		System.out.println();
	}
}

public static void add() throws IOException {
	System.out.print("Registration ID: ");  //registration ID
    String id = inp.nextLine();
    boolean isRight;
    String status = "";
            do {
                System.out.print("[1]Registered\n[2]Waitlisted\n[3]Attended\nNew Status: ");
                String ch = inp.nextLine(); //Status
                isRight =true;
                switch (ch) {
                    case "1":
                        status ="Registered";
                        break;
                    case "2":
                        status="Waitlisted";
                        break;
                    case "3":
                        status="Attended";
                        break;
                    default:
                        System.out.println("Enter 1 for Registered 2 for Waitlisted and 3 for Attended");
                        isRight=false;                       
                }
            } while (!isRight);

            ClassRegistration classreg = new ClassRegistration(id,status);
			classreg.checkDuplicateID(id);
			if (classreg.isGood) {
			System.out.println("Registration ID: "+id+" Date: "+classreg.getRegistrationDate()+" Status: "+status);
			System.out.println("Data Successfully Added");
			}
	}

public static void deleteRecord() throws IOException {
	List<ClassRegistration> classRegistrationList = ClassRegistration.getFromFile();
    System.out.print("Enter Registration ID to delete: ");
    String deleteID = inp.nextLine();
    System.out.print("Are you sure you want to delete this record?(Yes or No): ");
    String choice = inp.nextLine().toLowerCase();
    boolean isExist = false;
    if(choice.equals("yes")) {
    	for (ClassRegistration classReg : classRegistrationList) {
    		if (classReg.getRegistrationID().equals(deleteID)) {
    			classRegistrationList.remove(classReg);
    			isExist = true;
    			break;
    		}
    	}
    }
    
    if (isExist) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ClassRegistration.txt"))) {
            for (ClassRegistration classReg : classRegistrationList) {
                writer.write(classReg.getRegistrationID() + "*" + classReg.getRegistrationDate() + "*" + classReg.getStatus());
                writer.newLine();
            }
        }
        System.out.println("Data Successfully Deleted");
    } else {
        System.out.println("Data doesn't Exist");
    }
}
