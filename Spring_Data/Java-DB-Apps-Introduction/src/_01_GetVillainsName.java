import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _01_GetVillainsName {
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

        PreparedStatement statement = connection.prepareStatement("""
                 SELECT
                	v.name,
                	count(DISTINCT mv.minion_id) AS count_minions
                FROM
                	villains AS v
                JOIN
                	minions_villains mv on
                	v.id = mv.villain_id
                GROUP by
                	v.id
                HAVING
                	count_minions > 15
                ORDER BY
                	count_minions DESC;""");

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("name") + " " + rs.getString("count_minions"));
        }

        connection.close();
    }
}
