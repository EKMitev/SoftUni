import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _06_PrintAllMinionNames {
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
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement getNames = connection.prepareStatement("""
                SELECT
                	name
                FROM
                	minions_db.minions;
                """);

        List<String> names = new ArrayList<>();

        ResultSet nameSet = getNames.executeQuery();
        while (nameSet.next()) {
            names.add(nameSet.getString("name"));
        }

        int j = names.size();
        int k = 0;
        for (int i = 0; i < names.size(); i++) {
            if (i % 2 == 0) {
                System.out.println(names.get(k++));
            } else {
                System.out.println(names.get(--j));
            }
        }

        connection.close();
    }
}
