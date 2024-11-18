package fitness;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Role {
     private String roleID = "";
     private String roleName = "";

     public static Scanner i = new Scanner(System.in);

     public Role(String roleID, String roleName) {
         this.roleID = roleID;
         this.roleName = roleName;
     }
     public String getRoleID() {
         return roleID;
     }

     public String getRoleName() {
         return roleName;
     }
     
     public void setRoleName(String roleName) {
    	 this.roleName=roleName;
     }

     //saves record from user input into Member.txt
     public void saveToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Role.txt", true))) {
                writer.write(getRoleID() + "*" + getRoleName()+ "*");
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

     //scans all available records in Member.txt
     public static List<Role> getFromFile() {
            List<Role> roles = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("Role.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split("\\*");
                    String id = data[0];
                    String roleName = data[1];
                    roles.add(new Role(id, roleName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return roles;
        }

     // Adds a role to the record
     public static void add() {
    	 List <Role> roleList = (List<Role>) Role.getFromFile();
    	 boolean isValid = false;
    	 String roleID;

      //Requuires user to input new Role Id    
    	 do {
 			System.out.print("Enter new Role ID: ");
 			roleID = i.nextLine();
 			isValid = true;
 			for (Role role : roleList) {
 				if(role.getRoleID().equals(roleID) || roleID.isEmpty()) {	
 					if (role.getRoleID().equals(roleID)) {
 						System.out.println("Visitor ID already exists. Please try again.");
 					} else if (roleID.isEmpty()){
 						System.out.println("Role ID cannot be empty.");
 					}
 					isValid = false;
 					break;					
 				}
 			}

 		} while(isValid == false);
    	 
 		System.out.print("Enter Role Name: ");
 		String roleName = i.nextLine();
 		System.out.println("Role added Successfully");
 		Role r = new Role(roleID, roleName);
 		r.saveToFile();
 	}

     //Displays role record
     public static void display() {
 		List <Role> roleList = (List<Role>) Role.getFromFile();
 		System.out.println(String.format("%s", "-----------------------------------------"));
 		System.out.println(String.format("%9s %3s %26s","RoleID", "|","Name"));
 		System.out.println(String.format("%s", "-----------------------------------------"));
 		for(Role r: roleList) {
 			System.out.println(String.format("%9s %3s %26s", r.getRoleID(),"|",r.getRoleName()));
 		}
 		System.out.println(String.format("%s", "-----------------------------------------"));
 	}

     //updates role record
     public static void update() throws IOException {
    	 List<Role> roleList = Role.getFromFile();
    	 boolean roleValid = false;
    	 String roleID = null;
    	 do { 		 
    		 System.out.print("Look for Role ID: ");
    		 roleID = i.nextLine();
    		 	for (Role roles : roleList) {
    		 		if(roles.getRoleID().equals(roleID)) {
    		 			roleValid = true;
    		 			System.out.print("Current Role Name: "+roles.getRoleName() +"\nNew Role Name: ");
    		 		    String newName = i.nextLine();
    		 		    roles.setRoleName(newName);
    		    		roles.saveToFile();
    		 		}
    		 	}
    		 if (!roleValid) System.out.println("Role ID not found. Please try again.");
    	 } while (!roleValid);
    	  
    	 BufferedWriter writer = new BufferedWriter(new FileWriter("Role.txt"));
    	 for (Role roles : roleList) {
    		 writer.write(roles.getRoleID()+"*"+roles.getRoleName());
    		 writer.newLine(); 		 
    	 }
    	 System.out.println("Updated Entry");
    	 writer.close();
     }

     //deletes a role record from the txt file
     public static void delete() throws IOException {
    	 List<Role> roleList = Role.getFromFile();
    	 boolean roleValid = false;
    	 boolean isDeleted = true;
    	 String roleID = null;
      //Requires user to input existing role Id
    	 do { 		 
    		 System.out.print("Look for Role ID: ");
    		 roleID = i.nextLine();
    		 	for (Role roles : roleList) {
    		 		if(roles.getRoleID().equals(roleID)) {
    		 			roleValid = true;
    		 		}
    		 	}
    		 if (!roleValid) System.out.println("Role ID doesn't exist.");
    		 if(roleValid) {
    			
    			 System.out.print("Are you sure you want to delete this "+roleID+"?[1]Yes [2]No: ");
    			 String choice = i.nextLine();
        		 if (choice.equals("1")) {
        			 break;      			
        		 } else {
        			 System.out.println("Deletion Canceled");
        			 isDeleted = false;     			 
        		 }
    		 } 
    	 } while (!roleValid);
    	 
    	 if(isDeleted) {
    		 BufferedWriter writer = new BufferedWriter(new FileWriter("Role.txt"));
    		 for (Role roles : roleList) {
    			 if(!roles.getRoleID().equals(roleID)) {
    				 writer.write(roles.getRoleID()+"*"+roles.getRoleName());
    				 writer.newLine();
    				 isDeleted=true;
    			 }
    		 }
    		 writer.close();
    		 System.out.println("Role Deleted Successfully.");
    	 }
     }
     
}
