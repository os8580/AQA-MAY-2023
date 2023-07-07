package org.prog.steps;

import io.cucumber.java.en.Given;
import org.prog.db.Persons;
import org.prog.db.PersonsJpa;
import org.prog.dto.NameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.prog.util.DataHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SqlSteps {

    private final static Random RANDOM = new Random();

    @Autowired
    private PersonsJpa personsJpa;

    @Autowired
    private DataHolder dataHolder;

    @Given("Test spring db")
    public void smth() {
        personsJpa.findAll().forEach(p -> {
            System.out.println(p.getFirstName());
            System.out.println(p.getLastName());
        });
    }

    @Given("Get random user from group {string} as {string}")
    public void pickRandomUserFromGroup(String groupAlias, String userAlias) {
        List<NameDto> groupNames = (List<NameDto>) dataHolder.getValue(groupAlias);
        dataHolder.putValue(userAlias, randomPerson(groupNames));
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

            dataHolder.putValue(alias, randomPerson(userNames));

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
            List<NameDto> randomNames = (List<NameDto>) dataHolder.getValue(alias);

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

    @Given("SPRING - I store {string} in DB")
    public void storeUser(String alias) {
        ((List<NameDto>) dataHolder.getValue(alias)).stream()
                .forEach(name -> {
                    try {
                        System.out.println("Saving " + name);
                        personsJpa.save(Persons.builder()
                                .firstName(name.getFirst())
                                .lastName(name.getLast())
                                .title(name.getTitle())
                                .build());
                    } catch (Exception e){

                    }
                });
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

    private String getDBHost() {
//        if (Inet4Address.getLocalHost().getHostAddress().startsWith("172")) {
        if (System.getProperty("driver.type", "CHROME_DOCKER").equals("CHROME_DOCKER")) {
            return "jdbc:mysql://mysql-db-1:3306/db";
        } else {
            return "jdbc:mysql://localhost:3306/db";
        }
    }
}
