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

     public static Scanner sc = new Scanner(System.in);

     public Role(String roleID, String roleName) {
         this.roleID = roleID;
         this.roleName = roleName;
     }
     public Role() {

     }

     public String getRoleId() {
         return roleID;
     }

     public String getRoleName() {
         return roleName;
     }


     public void saveToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Role.txt", true))) {
                writer.write(getRoleId() + "" + getRoleName()+ "");
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


     public void add() {
         System.out.println("Enter Role id: ");
         String roleId = sc.nextLine();
         System.out.println("Enter Role name: ");
         String roleName = sc.nextLine();

         Role r = new Role(roleId, roleName);
         r.saveToFile();

     }

}