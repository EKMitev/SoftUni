import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _02_GetMinionsNames {
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

        PreparedStatement villainStatement = connection.prepareStatement(
                "SELECT" +
                        " id," +
                        " name " +
                        " FROM" +
                        " villains" +
                        " WHERE id = ?");

        int villainID = scanner.nextInt();
        villainStatement.setInt(1, villainID);

        ResultSet villainSet = villainStatement.executeQuery();

        if (!villainSet.next()) {
            System.out.printf("No villain with ID %d exists in the database.", villainID);
            return;
        }

        String vName = villainSet.getString("name");
        System.out.println("Villain: " + vName);

        PreparedStatement minionStatement = connection.prepareStatement("""
                SELECT
                	m.name,
                	m.age
                from
                	minions m
                join minions_villains mv on
                	m.id = mv.minion_id
                WHERE
                	mv.villain_id = ?;""");
        minionStatement.setInt(1, villainID);

        ResultSet minionSet = minionStatement.executeQuery();
        if (!minionSet.next()) {
            System.out.println("No minions for that villain");
            return;
        }

        for (int i = 1; minionSet.next(); i++) {
            System.out.println(i + ". " + minionSet.getString("name"));
        }

        connection.close();
    }
}
