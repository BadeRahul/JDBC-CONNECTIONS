package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class addingmediatodb {
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
            sc.nextLine();
            System.out.println("Enter the table name to add:");
            String tableName = sc.nextLine();
            System.out.println("Enter the path to the image or video file: ");
            String filePath = sc.nextLine();
            System.out.println("Enter the MIME type of the file (e.g., [image/jpeg],[video/mp4]): ");
            String fileType = sc.nextLine();
            File file = new File(filePath);
            FileInputStream inputStream = new FileInputStream(file);
            String sql = "INSERT INTO "+tableName+" (name, type, content) VALUES (?, ?, ?)";
            pmst= conn.prepareStatement(sql);
            pmst.setString(1, file.getName());
            pmst.setString(2, fileType);
            pmst.setBlob(3, inputStream);

            int i = pmst.executeUpdate();
            if (i > 0) {
                System.out.println("A new media file was inserted successfully!");
            }

            pmst.close();
            conn.close();
            inputStream.close();
            
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}