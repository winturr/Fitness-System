
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
        
