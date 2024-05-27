package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class update {
	  private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	    private static final String USERNAME = "root"; 
	    private static final String PASSWORD = "Rahul@08"; 
	    private static Connection conn = null;
	    private static PreparedStatement pmst = null;


	public static void main(String[] args) {
	 Scanner sc = new Scanner(System.in);
     String url = "jdbc:mysql://localhost:3306/";

     try {
    	 Class.forName(DRIVER);
         System.out.println("Enter the database name to use:");
         String databaseName = sc.next();
         url = url + databaseName;
         conn = DriverManager.getConnection(url, USERNAME, PASSWORD);
         
         System.out.println("Enter the table name to update:");
         String tableName = sc.next();
         
         System.out.println("Enter the column name to update:");
         String columnName = sc.next();
         
         System.out.println("Enter the new value:");
         String newValue = sc.next();
         
         System.out.println("Enter the condition for updating (e.g., id=1):");
         String condition = sc.next();
         
         String sql = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE " + condition;
         pmst = conn.prepareStatement(sql);
         pmst.setString(1, newValue);
         
         int i = pmst.executeUpdate();
         if (i > 0) {
             System.out.println("Record updated successfully.");
         } else {
             System.out.println("Error in updating record.");
         }
         conn.close();
         pmst.close();
     } catch (Exception e) {
         e.printStackTrace();
     }
     sc.close();
     
 }

}
