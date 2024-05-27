package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.sql.DatabaseMetaData;

public class dynamicinsertion {
	 private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	    private static final String USERNAME = "root"; 
	    private static final String PASSWORD = "Rahul@08"; 
	    private static Connection conn = null;
	    private static PreparedStatement pmst = null;

	    public static void main(String[] args) {
	        createTable();
	    }

		private static void createTable() {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
	        try {
	        	Class.forName(DRIVER);
	        	System.out.println("Enter the database name:");
	            String databaseName = sc.next();
	            String url = "jdbc:mysql://localhost:3306/" + databaseName;
	            conn = DriverManager.getConnection(url, USERNAME, PASSWORD);

	            System.out.println("Enter the table name to insert data into:");
	            String tableName = sc.next();

	            // Fetch column names
	            DatabaseMetaData metaData = conn.getMetaData();
	            ResultSet rs = metaData.getColumns(null, null, tableName, null);
	            List<String> columns = new ArrayList<>();
	            while (rs.next()) {
	                String columnName = rs.getString("COLUMN_NAME");
	                columns.add(columnName);
	            }
	            rs.close();

	            // Prompt user to enter data for each column
	            List<String> values = new ArrayList<>();
	            sc.nextLine(); // Consume newline
	            for (String column : columns) {
	                System.out.println("Enter value for " + column + ":");
	                values.add(sc.nextLine());
	            }

	            // Construct the INSERT INTO SQL statement
	            StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
	            for (int i = 0; i < columns.size(); i++) {
	                sql.append(columns.get(i));
	                if (i < columns.size() - 1) {
	                    sql.append(", ");
	                }
	            }
	            sql.append(") VALUES (");
	            for (int i = 0; i < values.size(); i++) {
	                sql.append("?");
	                if (i < values.size() - 1) {
	                    sql.append(", ");
	                }
	            }
	            sql.append(");");

	            // Prepare and execute the SQL statement
	            pmst = conn.prepareStatement(sql.toString());
	            for (int i = 0; i < values.size(); i++) {
	                pmst.setString(i + 1, values.get(i));
	            }
	            int i = pmst.executeUpdate();
	            if (i > 0) {
	                System.out.println("Data inserted successfully.");
	            } else {
	                System.out.println("Error inserting data.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
		}

}
