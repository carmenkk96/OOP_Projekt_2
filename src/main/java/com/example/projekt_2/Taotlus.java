package com.example.projekt_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Taotlus {
    private String taotlejaNimi;
    private String tooteNimi;
    private LocalDate taotluseKuupäev;

    public Taotlus(String taotlejaNimi, String tooteNimi) {
        this.taotlejaNimi = taotlejaNimi;
        this.tooteNimi = tooteNimi;
        taotluseKuupäev = LocalDate.now();
    }

    public String getTaotlejaNimi() {
        return taotlejaNimi;
    }

    public String getTooteNimi() {
        return tooteNimi;
    }

    public LocalDate getTaotluseKuupäev() {
        return taotluseKuupäev;
    }

    public void taotluseKirjutamineAndmebaasi(String id) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");

            // Prepare the SQL statement
            String insertQuery = "INSERT INTO taotlus (taotlejaNimi, tooteNimi, taotluseKuupaev, generatedID) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);

            // Set the parameter values
            preparedStatement.setString(1, this.taotlejaNimi); // Set the taotlejaNimi value
            preparedStatement.setString(2, this.tooteNimi); // Set the tooteNimi value
            preparedStatement.setString(3, this.taotluseKuupäev.toString()); // Set the taotluseKuupaev value
            preparedStatement.setString(4, id); // Set the id value


            // Execute the SQL statement
            preparedStatement.executeUpdate();

            System.out.println("Andmed lisatud andmebaasi");
        } finally {
            // Close the prepared statement and connection
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}


