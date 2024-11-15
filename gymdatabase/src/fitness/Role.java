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


     public void saveToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Role.txt", true))) {
                writer.write(getRoleID() + "*" + getRoleName()+ "*");
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


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


     public static void add() {
    	 List <Role> roleList = (List<Role>) Role.getFromFile();
    	 int ctr = 0;
    	 boolean isValid = false;
    	 String roleID;
    	 do {
 			System.out.print("Enter Role ID: ");
 			roleID = i.nextLine();
 			for (Role role : roleList) {
 				if(role.getRoleID().equals(roleID)) {	
 					ctr++;
 					System.out.println("Visitor ID already exists. Please try again.");
 				}
 			}
 			if (ctr == 0) {
 				isValid = true;
 			}
 			if (isEmpty(roleID)) {
 				isValid = false;
 			}
 			ctr = 0;
 		}
 		while(isValid == false);
 		System.out.print("Enter Role Name: ");
 		String roleName = i.nextLine();
 		System.out.println("Successfully added to Visitor.txt");
 		Role r = new Role(roleID, roleName);
 		r.saveToFile();
 	}
     
     public static void display() {
 		List <Role> roleList = (List<Role>) Role.getFromFile();
 		System.out.println(String.format("%s", "-----------------------------------------"));
 		System.out.println(String.format("%9s %3s %26s","RoleID", "|","Name"));
 		System.out.println(String.format("%s", "-----------------------------------------"));
 		for(Role r: roleList) {
 			System.out.println(String.format("%9s %3s %26s", r.getRoleID(),"|",r.getRoleName()));
 		}
 	}
     public static boolean isEmpty(String input) {
 		if (input != "") {
 			return false;
 		}
 		return true;
 	}
}
