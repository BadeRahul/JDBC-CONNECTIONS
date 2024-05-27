package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ALLOPERATIONS {
	public static final String Driver = "com.mysql.cj.jdbc.Driver";
	public static final String Username = "root";
	public static final String Password = "Rahul@08";
	public static Connection conn;
	public static PreparedStatement pmst;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int ch;
		do {
			display();
				System.out.println("Enter your Choice to Execute Queries");
				ch = Integer.parseInt(sc.nextLine());
				switch(ch) {
					case 1 : showDB();
					 		 break;
					case 2 : createDB();
							 break;
					case 3 : showTables();
							 break;
					case 4 : createTable();
							 break;
					case 5 : insertData();
							 break;
					case 6 : delete();
							 break;
					case 7 : getAll();
							 break;
					case 8 : getbyId();
							 break;
					case 9 : update();
					 		 break;		 
					case 10 : dropTable();
							 break;
					case 11 : dropDB();
							 break;
					case 12 :
						System.out.println("Thank You!!!!!");
						System.exit(0);
						break;
				    default : 
				    	System.out.println("Invalid Operations");
				}
		}while(ch > 0);

	}

	

	private static void update() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
	     String url = "jdbc:mysql://localhost:3306/";

	     try {
	    	 Class.forName(Driver);
	         System.out.println("Enter the database name to use:");
	         String databaseName = sc.next();
	         url = url + databaseName;
	         conn = DriverManager.getConnection(url, Username, Password);
	         
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
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		
	}



	private static void dropDB() {
		System.out.println("You Choosed Drop Database operation");
		Scanner sc = new Scanner(System.in);
		String Url = "jdbc:mysql://localhost:3306/";
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Enter DataBase name to Delete");
			String tablename = sc.next();
			String sql = "drop database "+tablename;
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i==0) {
				System.out.println(tablename+" Database Sucessfully Deleted");
			}else {
				System.out.println("Error in Deletion of DataBase");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void dropTable() {
		System.out.println("You Choosed Drop Table operation");
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(Driver);
			System.out.println("Enter the database name:");
			String databaseName = sc.next();
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter the table name:");
			String tablename = sc.next();
			String sql = "drop table "+tablename+" ;";
			pmst = conn.prepareStatement(sql);
			int i= pmst.executeUpdate();
			if(i==0) {
				System.out.println(tablename+" Deleted Successfully");
			}else {
				System.out.println("Error in deleting table");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void getbyId() {
		System.out.println("You Choosed Show Data By Id(getbyId) operation");
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(Driver);
			System.out.println("Enter the database name:");
			String databaseName = sc.next();
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter the table name:");
			String tablename = sc.next();
			System.out.println("Enter the ID column of the table:");
			String id = sc.next();
			String sql = "Select * from "+tablename+" where "+id+" = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter the id");
            pmst.setInt(1, sc.nextInt());
            ResultSet rs = pmst.executeQuery();
            while(rs.next()) {
				int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
			}
            
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void getAll() {
		System.out.println("You Choosed Show Data(getALL) operation");
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(Driver);
        	System.out.println("Enter the database name:");
            String databaseName = sc.next();
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            conn = DriverManager.getConnection(url, Username, Password);
            System.out.println("Enter the table name to fetch all the data:");
            String tableName = sc.next();
            String sql = "Select * from "+tableName+";";
            pmst = conn.prepareStatement(sql);
            ResultSet rs = pmst.executeQuery();
			while(rs.next()) {
				int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void delete() {
		System.out.println("You Choosed Delete Data operation");
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName(Driver);
        	System.out.println("Enter the database name:");
            String databaseName = sc.next();
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            conn = DriverManager.getConnection(url, Username, Password);
            System.out.println("Enter the table name:");
			String tablename = sc.next();
			System.out.println("Enter the ID column of the table:");
			String id = sc.next();
			String sql = "delete from "+tablename+" where "+id+" = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("Enter the id");
            pmst.setInt(1, sc.nextInt());
            int i=pmst.executeUpdate();
			if(i>0) {
				System.out.println("Data Deleted Sucessfully");
			}else {
				System.out.println("Error in Deletion");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void insertData() {
		System.out.println("You Choosed Insert Data operation");
		Scanner sc = new Scanner(System.in);
        try {
        	Class.forName(Driver);
        	System.out.println("Enter the database name:");
            String databaseName = sc.next();
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            conn = DriverManager.getConnection(url, Username, Password);
            System.out.println("Enter the table name to insert data into:");
            String tableName = sc.next();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getColumns(null, null, tableName, null);
            List<String> columns = new ArrayList<>();
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                columns.add(columnName);
            }
            rs.close();
            List<String> values = new ArrayList<>();
            sc.nextLine();
            for (String column : columns) {
                System.out.println("Enter value for " + column + ":");
                values.add(sc.nextLine());
            }
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

	private static void createTable() {
		System.out.println("You Choosed Create Table operation");
		String url = "jdbc:mysql://localhost:3306/";
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName(Driver);
            System.out.println("Enter the database name to use and create tables in that database:");
            String databaseName = sc.next();
            url = url + databaseName;
            conn = DriverManager.getConnection(url, Username, Password);
            System.out.println("Enter the table name to create:");
            String tableName = sc.next();
            List<String> columns = new ArrayList<>();
            System.out.println("Enter the columns (type 'done' to finish):");
            sc.nextLine();  
            while (true) {
                System.out.println("Enter column definition (e.g., 'id INT AUTO_INCREMENT PRIMARY KEY') or type 'done' to finish:");
                String column = sc.nextLine();
                if (column.equalsIgnoreCase("done")) {
                    break;
                }
                columns.add(column);
            }
            StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
            for (int i = 0; i < columns.size(); i++) {
                sql.append(columns.get(i));
                if (i < columns.size() - 1) {
                    sql.append(", ");
                }
            }
            sql.append(");");
            pmst = conn.prepareStatement(sql.toString());
            int i = pmst.executeUpdate();
            if (i == 0) {
                System.out.println("Table '" + tableName + "' created successfully.");
            } else {
                System.out.println("Table creation Error");
            }
        }catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void showTables() {
		System.out.println("You Choosed Show Tables operation");
		String Url = "jdbc:mysql://localhost:3306/";
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Database name:");
			String databasename = sc.next();
			Url = Url+databasename;
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Showing all DataBases");
			String sql = "show tables";
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			int i=0;
			while(rs.next()) {
				System.out.println("\t"+(i+1)+" "+rs.getString(1));
				i++;
			}
			if(i==0) {
				System.out.println("No tables are present in the "+databasename+" Database");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void createDB() {
		System.out.println("You Choosed Create Databases operation");
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/";
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, Username, Password);
			System.out.println("Enter DataBase name to create");
			String tablename = sc.next();
			String sql = "create database "+tablename;
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println(tablename+" Database Sucessfully created");
			}else {
				System.out.println("Error in Creation of DataBase");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void showDB() {
		// TODO Auto-generated method stub
		System.out.println("You Choosed Show Databases operation");
		String Url = "jdbc:mysql://localhost:3306/";
		try {
			Class.forName(Driver);
			conn = DriverManager.getConnection(Url, Username, Password);
			System.out.println("Showing all DataBases");
			String sql = "show databases";
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			int i=1;
			while(rs.next()) {
				System.out.println("\t"+i+" "+rs.getString(1));
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private static void display() {
		// TODO Auto-generated method stub
		System.out.println("==================================");
		System.out.println("Select DataBase Operations");
		System.out.println("\t1. Show Databases");
		System.out.println("\t2. Create Database");
		System.out.println("\t3. Show Tables");
		System.out.println("\t4. Create Table");
		System.out.println("\t5. Insert Data");
		System.out.println("\t6. Delete Data");
		System.out.println("\t7. Fetch All Data");
		System.out.println("\t8. Fetch Data By Id");
		System.out.println("\t9. Update Table");
		System.out.println("\t10. Drop Table");
		System.out.println("\t11. Drop Database");
		System.out.println("\t12. Terminate the Process");
		System.out.println("==================================");
		
		
		
	}

}
