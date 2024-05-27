package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;



public class sqlconnector2 {
	public static final String Driver = "com.mysql.cj.jdbc.Driver";
	public static final String Username = "root";
	public static final String Password = "Rahul@08";
	public static final String Url = "jdbc:mysql://localhost:3306/";
	public static Connection conn;
	public static PreparedStatement pmst;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
//			System.out.println("Enter the Database Name to create : ");
//			String sql = "create database "+sc.next();
			System.out.println("Enter the Database Name to drop : ");
			String sql = "drop database "+sc.next();
			pmst = conn.prepareStatement(sql);
			int i=pmst.executeUpdate();
//			if(i>0) {
//				System.out.println("Successfully Created");   //creating purpose
//			}else {																		
//				System.out.println("Connection Error");
//			}
			if(i==0) {											//Dropping purpose
				System.out.println("Successfully Created");
			}else {
				System.out.println("Connection Error");
			}
			conn.close();
			pmst.close();
			sc.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
