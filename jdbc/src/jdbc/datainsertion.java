package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class datainsertion {
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
			String sql = "insert into employee(empid,empname,empadd) values(?,?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter Employee Id");
			pmst.setInt(1, sc.nextInt());
			System.out.println("Enter Employee Name");
			pmst.setString(2, sc.next());
			System.out.println("Enter Employee Address");
			pmst.setString(3, sc.next());
			int i=pmst.executeUpdate();
			if(i>0) {
				System.out.println("Data Inserted Sucessfully");
			}else {
				System.out.println("Error in insertion");
			}
			pmst.close();
			conn.close();
			sc.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}