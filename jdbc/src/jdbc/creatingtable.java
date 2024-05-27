package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class creatingtable {
	public static final String Driver = "com.mysql.cj.jdbc.Driver";
	public static final String Username = "root";
	public static final String Password = "Rahul@08";
	public static String Url = "jdbc:mysql://localhost:3306/";
	public static Connection conn;
	public static PreparedStatement pmst;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Database name to Use and Create tables in that DataBase");
			Url = Url+sc.next();
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter the table name to create");
			String sql = "create table "+sc.next()+"(id int,name varchar(20),email varchar(40))";
			pmst = conn.prepareStatement(sql);
			int i=pmst.executeUpdate();
			if(i==0) {
				System.out.println("Table Created Sucessfully");
			}else {
				System.out.println("Error in Table Creation");
			}
			pmst.close();
			conn.close();
			sc.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
