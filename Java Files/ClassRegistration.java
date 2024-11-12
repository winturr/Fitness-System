public class ClassRegistration {
	public Scanner inp = new Scanner(System.in);
    private String registrationID,registrationDate,status;
    LocalDate date = LocalDate.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public boolean isGood = true;
    
	public ClassRegistration(String registrationID, String status) {
    	this.registrationID=registrationID;
    	this.registrationDate = LocalDate.now().format(formatter);
    	this.status=status;
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
                String status = data[2];
                classreg.add(new ClassRegistration(regID,status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classreg;		     
	}
    
    public void checkDuplicateID(String inputID) {
    	
    	try {
    		boolean isDuplicate = false;
			try (BufferedReader reader = new BufferedReader(new FileReader("ClassRegistration.txt"))) {
				String line;
				try {
					while((line = reader.readLine()) != null ) {
					String[]data=line.split("\\*");
					if (data[0].equals(inputID) && data.length >=2) {
						System.out.println("Registration ID "+data[0]+" already exists.");
						isDuplicate=true;
						isGood=false;
						return;					
					}
					
					}
					if(!isDuplicate) {
						saveToFile();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
    	
    }
    
    // Palagay sa main yung [1]Registered [2]Waitlisted [3]Attended para sa paginput ng status
    //Sample Main
    /*System.out.print("Registration ID: ");  //registration ID
			    String id = inp.nextLine();
			    boolean isRight;
			    String status = "";
			            do {
			                System.out.print("[1]Registered\n[2]Waitlisted\n[3]Attended\nStatus: ");
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
    
	*/
    
   
}
