import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _03_AddMinion {
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

        String[] minionData = scanner.nextLine().split(" ");
        String[] villainData = scanner.nextLine().split(" ");

        //check if the given town exist in the DB
        PreparedStatement townStatement = connection
                .prepareStatement("select * from towns where name = ?");
        townStatement.setString(1, minionData[3]);
        ResultSet rsTowns = townStatement.executeQuery();

        int townId;
        if (rsTowns.next()) {
            townId = rsTowns.getInt("id");
        } else {
            PreparedStatement townSt = connection
                    .prepareStatement("insert into towns (name)\n" +
                            "values(?);");
            townSt.setString(1, minionData[3]);
            townSt.executeUpdate();
            ResultSet newTownSet = townStatement.executeQuery();
            newTownSet.next();
            townId = newTownSet.getInt("id");
            System.out.println("Town " + minionData[3] + " was added to the database.");
        }

        //adding minion
        PreparedStatement minionStatement = connection
                .prepareStatement("""
                        insert
                        	into
                        	minions (name,
                        	age,
                        	town_id)
                        values(?, ?, ?);""");

        minionStatement.setString(1, minionData[1]);
        minionStatement.setInt(2, Integer.parseInt(minionData[2]));
        minionStatement.setInt(3, townId);

        minionStatement.executeUpdate();

        //add villain
        PreparedStatement selectVillain = connection
                .prepareStatement("select * from villains where name = ?");
        selectVillain.setString(1, villainData[1]);

        ResultSet villainSet = selectVillain.executeQuery();
        if (!villainSet.next()) {
            PreparedStatement createVillain = connection
                    .prepareStatement("insert into villains(name, evilness_factor) values(?, 'evil')");
            createVillain.setString(1, villainData[1]);
            createVillain.executeUpdate();
            System.out.println("Villain " + villainData[1] + " was added to the database.");
        }

        //add minion and villain to mapping table
        ResultSet resultSetVillain = selectVillain.executeQuery();
        resultSetVillain.next();
        int villainId = resultSetVillain.getInt("id");

        PreparedStatement getMinionId = connection
                .prepareStatement("select * from minions where name = ?");
        getMinionId.setString(1, minionData[1]);

        ResultSet minionSet = getMinionId.executeQuery();
        minionSet.next();
        int minionId = minionSet.getInt("id");

        PreparedStatement insertToMap = connection
                .prepareStatement("insert into minions_villains(minion_id, villain_id) values(?, ?)");
        insertToMap.setInt(1, minionId);
        insertToMap.setInt(2, villainId);
        System.out.printf("Successfully added %s to be minion of %s.", minionData[1], villainData[1]);

        connection.close();
    }
}