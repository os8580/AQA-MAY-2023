package org.prog.steps;

import io.cucumber.java.en.Given;
import org.prog.dto.NameDto;
import org.testng.Assert;
import org.util.DataHolder;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SqlSteps {

    private final static Random RANDOM = new Random();

    @Given("Get random user from group {string} as {string}")
    public void pickRandomUserFromGroup(String groupAlias, String userAlias) {
        List<NameDto> groupNames = (List<NameDto>) DataHolder.getInstance().getValue(groupAlias);
        DataHolder.getInstance().putValue(userAlias, randomPerson(groupNames));
    }

    @Given("Get random user from DB as {string}")
    public void readDB(String alias) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(getDBHost(), "user", "password");
            Statement stmt = con.createStatement();

            String query = "select * from Persons";
            ResultSet resultSet = stmt.executeQuery(query);

            List<NameDto> userNames = new ArrayList<>();
            while (resultSet.next()) {
                userNames.add(assemblePerson(resultSet));
            }

            DataHolder.getInstance().putValue(alias, randomPerson(userNames));

            resultSet.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("SQL Error");
        }
    }

    @Given("I store {string} in DB")
    public void writeSQL(String alias) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(getDBHost(), "user", "password");
            Statement stmt = con.createStatement();

            String query = "insert into Persons (Title, FirstName, LastName) VALUES ('%s', '%s', '%s')";
            List<NameDto> randomNames = (List<NameDto>) DataHolder.getInstance().getValue(alias);

            for (NameDto randomUserName : randomNames) {
                try {
                    stmt.execute(
                            String.format(query, randomUserName.getTitle(), randomUserName.getFirst(), randomUserName.getLast()));
                } catch (SQLException e) {
                    System.out.println("Error inserting data: " + randomUserName.getFirst() + " " + randomUserName.getLast());
                }
            }

            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("SQL Error");
        }
    }

    private NameDto assemblePerson(ResultSet resultSet) throws SQLException {
        NameDto nameDto = new NameDto();
        nameDto.setTitle(resultSet.getString("Title"));
        nameDto.setFirst(resultSet.getString("FirstName"));
        nameDto.setLast(resultSet.getString("LastName"));
        return nameDto;
    }

    private NameDto randomPerson(List<NameDto> names) {
        return names.get(RANDOM.nextInt(names.size()));
    }

    private String getDBHost() throws UnknownHostException {
        if (Inet4Address.getLocalHost().getHostName().contains("jenkins")) {
            return "jdbc:mysql://mysql-db-1:3306/db";
        } else {
            return "jdbc:mysql://localhost:3306/db";
        }
    }
}
