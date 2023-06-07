package org.jdbc;


import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/addressBook_DB";
        String userName = "root";
        String password = "Masai@321";
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                String createTableQuery = "create table addressbook (name varchar(20), address varchar(50), city varchar(20), state varchar(20), zip int, phoneNumber int)";
                statement.executeUpdate(createTableQuery);
                System.out.println("Table Created Successfully");
            }
            try (Statement statement = connection.createStatement()) {
                String insertQuery = "insert into addressbook (name,address,city,state,zip,phoneNumber)values('Rahul','Banka','Patna','BIhar',813211,993469189)," +
                        "('Kundan','Seohar','Muz','Bihar',812711,979865261),('Shubham','Hazipur','Patna','Bihar',984567,182717211)";
                statement.executeUpdate(insertQuery);
                System.out.println("Inserted Successfully");
            }
            try (Statement statement = connection.createStatement()) {
                String ascendingNameQuery = "select name from addressbook order by name asc ";
                ResultSet resultSet = statement.executeQuery(ascendingNameQuery);

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    System.out.println("Name : " + name);
                }
                resultSet.close();
            }
            try (Statement statement = connection.createStatement()) {
                String selectQuery = "select * from addressbook";
                ResultSet resultSet = statement.executeQuery(selectQuery);

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    int zip = resultSet.getInt("zip");
                    int phoneNumber = resultSet.getInt("phoneNumber");

                    System.out.println("Name : " + name + " , Address : " + address + " , City : " + city + " , State : " + state + " , Zip : " + zip + " , PhoneNumber : " + phoneNumber);
                }
                resultSet.close();
            }
            try (Statement statement = connection.createStatement()) {
                String updateQuery = "update addressbook set city = 'Pune', state = 'Mah' where name = 'Shubham'";
                statement.executeUpdate(updateQuery);
                connection.commit();
                System.out.println("Updated Successfully");

            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                        System.out.println("roll back successfully");

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            try (Statement statement = connection.createStatement()) {
                String selectQuery = "select * from addressbook order by name desc";
                ResultSet resultSet = statement.executeQuery(selectQuery);

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    int zip = resultSet.getInt("zip");
                    int phoneNumber = resultSet.getInt("phoneNumber");

                    System.out.println("Name : " + name + " , Address : " + address + " , City : " + city + " , State : " + state + " , Zip : " + zip + " , PhoneNumber : " + phoneNumber);
                }
                resultSet.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}