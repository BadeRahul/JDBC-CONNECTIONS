package jdbc; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateTableDynamic {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = "Rahul@08"; 
    private static Connection conn = null;
    private static PreparedStatement pmst = null;

    public static void main(String[] args) {
        createTable();
    }

    private static void createTable() {
        String url = "jdbc:mysql://localhost:3306/";
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName(DRIVER);
            System.out.println("Enter the database name to use and create tables in that database:");
            String databaseName = sc.next();
            url = url + databaseName;
            conn = DriverManager.getConnection(url, USERNAME, PASSWORD);
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
            pmst.close();
            conn.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
