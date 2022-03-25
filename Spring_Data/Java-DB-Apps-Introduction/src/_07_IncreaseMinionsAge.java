import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _07_IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = scanner.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password default (empty):");
        String password = scanner.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement getAll = connection.prepareStatement("""
                SELECT    
                    id,                                 
                    LOWER(name) as name,
                    age
                 FROM
                    minions;
                 """);


        List<Integer> IDs = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).toList();

        ResultSet rs = getAll.executeQuery();


        while (rs.next()) {
            int age = rs.getInt("age");
            if (IDs.contains(rs.getInt("id"))) {
                System.out.println(rs.getString("name") + " " + ++age);
                continue;
            }
            System.out.println(rs.getString("name") + " " + rs.getInt("age"));
        }

        connection.close();
    }
}
