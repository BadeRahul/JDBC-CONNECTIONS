package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class creatingmediatable {
	public static final String Driver = "com.mysql.cj.jdbc.Driver";
	public static final String Username = "root";
	public static final String Password = "Rahul@08";
	public static Connection conn;
	public static PreparedStatement pmst;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/";
		try {
			Class.forName(Driver);
            System.out.println("Enter the database name to use and create tables in that database:");
            String databaseName = sc.next();
            url = url + databaseName;
            conn = DriverManager.getConnection(url, Username, Password);
            System.out.println("Enter the table name to create:");
            String tableName = sc.next();
            String sql = "create table "+tableName+" (id INT AUTO_INCREMENT PRIMARY KEY,"
            		+ "name VARCHAR(255) NOT NULL,"
            		+ "type VARCHAR(255) NOT NULL,"
            		+ "content LONGBLOB NOT NULL)";
            pmst = conn.prepareStatement(sql);
            int i=pmst.executeUpdate();
            if(i==0) {
            	System.out.println("Success");
            }else {
            	System.out.println("error");
            }
            sc.close();
            conn.close();
            pmst.close();
            } catch (Exception e) {
			// TODO: handle e/xception
			e.printStackTrace();
		}

	}

}
