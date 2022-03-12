import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _04_ChangeTownNames {
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

        PreparedStatement updateTowns = connection.prepareStatement("""
                UPDATE
                	towns
                Set
                	name = UPPER(name)
                where
                	country = ?;""");

        String name = new Scanner(System.in).nextLine();
        updateTowns.setString(1, name);

        int i = updateTowns.executeUpdate();


        if (i == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        PreparedStatement getTowns = connection.prepareStatement("""
                SELECT
                	*
                from
                	towns
                where
                	country = ?;""");
        getTowns.setString(1, name);

        ResultSet townSet = getTowns.executeQuery();
        List<String> towns = new ArrayList<>();
        while (townSet.next()){
           towns.add(townSet.getString("name"));
        }

        System.out.println(i + " town names were affected.");
        System.out.println(towns);

        connection.close();
    }
}
