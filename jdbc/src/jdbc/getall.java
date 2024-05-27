package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class getall {
	public static final String Driver = "com.mysql.cj.jdbc.Driver";
	public static final String Username = "root";
	public static final String Password = "Rahul@08";
	public static final String Url = "jdbc:mysql://localhost:3306/rahul_db";
	public static Connection conn;
	public static PreparedStatement pmst;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter the tablename to fetch the data");
			String sql = "select * from "+ sc.next();
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while(rs.next()) {
				System.out.println("Emp Id:"+rs.getInt("empid"));
				System.out.println("Emp Name:"+rs.getString("empname"));
				System.out.println("Emp Address:"+rs.getString("empadd"));	
			}
			conn.close();
			pmst.close();
			sc.close();
		}
		catch (Exception e) {
			
			e.printStackTrace();// TODO: handle exception
		}
	}
}
