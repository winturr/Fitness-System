
    public class classRegistration {
        private String registrationID,registrationDate,status;
        
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
        //PANG ADD
         public void add() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("classRegistration.txt",false)) //create txt file
        ) {
            writer.write(String.format("%-20s%-20s%-15s\n", "Registration ID", "Registration Date", "Status"));
            Scanner inp = new Scanner (System.in);
            System.out.print("Registration ID: ");//registration ID
            setRegistrationID(inp.nextLine());
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            setRegistrationDate(date.format(formatter)); //registration Date            
            boolean isRight;
            do {
                System.out.print("[1]Registered\n[2]Waitlisted\n[3]Attended\nStatus: ");
                int ch = inp.nextInt(); //Status
                isRight =true;
                switch (ch) {
                    case 1:
                        setStatus("Registered");
                        break;
                    case 2:
                        setStatus("Waitlisted");
                        break;
                    case 3:
                        setStatus("Attended");
                        break;
                    default:
                        System.out.println("Enter 1 for Registered 2 for Waitlisted and 3 for Attended");
                        isRight=false;
                       
                }
            } while (!isRight);
                                
            writer.write(String.format("%-20s%-20s%-15s\n", getRegistrationID(), getRegistrationDate(), getStatus()));
        }

            //PANG UPDATE
            public void update() throws IOException {
            System.out.print("Registration ID want to update: ");
            String upid = inp.nextLine(); //update id
		
            File tempFile = new File ("tempFile.txt");
            File inputFile = new File ("classRegistration.txt");
		
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));
		
            String line;
            while ((line = reader.readLine()) != null) {
			tempWriter.write(line + "\n");
            } //lalagay ung info sa tempfile
		
            reader.close();
            tempWriter.close();
		
            BufferedReader tempReader = new BufferedReader(new FileReader(tempFile));
            BufferedWriter inputWriter = new BufferedWriter(new FileWriter(inputFile));
            String[] field = null;
            while ((line = tempReader.readLine()) != null){
		    field = line.split("\\s+"); //hahatiin tapos lalagay siya sa array
                if (field[0].equals(upid) && field.length >=3) { //panghanap ng registration ID
                    System.out.println("Found: " + line);
                    String newStatus="";
                    boolean isRight;
                        do {
                            System.out.print("[1]Registered\n[2]Waitlisted\n[3]Attended\nStatus: ");
                             String ch2 = inp.nextLine(); //Status
                             isRight =true;
                                switch (ch2) {
                                    case "1":
                                        newStatus="Registered";
                                        break;
                                    case "2":
                                        newStatus="Waitlisted";
                                        break;
                                    case "3":
                                        newStatus="Attended";
                                        break;
                                    default:
                                        System.out.print("Enter 1 for Registered 2 for Waitlisted and 3 for Attended");
                                        isRight=false; 
                       
                                }
                        } while (!isRight);
                line = String.format("%-20s%-20s%-15s", field[0], field[1],newStatus);
                System.out.print(line);                    
                }
            inputWriter.write(line + "\n");
            }
        tempReader.close();
        inputWriter.close();
        tempFile.delete();
        }

        
        //PANG DISPLAY NG TXT FILE
        public void displayAll() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("classRegistration.txt"));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] field = line.split("\\s+");
			if (field.length >= 3) {
			System.out.println(String.format("%-20s%-20s%-15s", field[0], field[1],field[2]));
			}
		}
		reader.close();
        }
