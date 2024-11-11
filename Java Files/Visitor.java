package fitness;

public class Visitor {
	private String visitorID;
	private String name;
	private String visitorType;
	
	public String getVisitorID() {
		return visitorID;
	}
	public String getName() {
		return name;
	}
	public String getVisitorType() {
		return visitorType;
	}
	public void setVisitorID(String visitorID) {
		this.visitorID = visitorID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setVisitorType(String visitorType) {
		this.visitorType = visitorType;
	}

	// "*" as a delimiter
	//Writes and save to visitor text file
	public void saveToFile() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("Visitor.txt", true))) {
			writer.write(visitorID + "*" + name + "*" + visitorType + "*");
			writer.newLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//List<Visitor> as a return data type in this method
	/*List is an interface, concrete class ArrayList implements the List interface
	 which allows flexibility*/
	public static List<Visitor> getFromFile() {
		List<Visitor> visitors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Visitor.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\*");
                String id = data[0];
                String name = data[1];
                String type = data[2];
                visitors.add(new Visitor(id, name, type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitors;

}
