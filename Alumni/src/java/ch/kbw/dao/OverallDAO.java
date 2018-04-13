/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author Adel
 */
@Named
@SessionScoped
public class OverallDAO implements Serializable{
    // DATABASE CONNECTION
    private Connection connection;
    //FOR FURTHER INFORMATION, FOR TESTING
    private Logger log = Logger.getLogger(UserDAO.class.getSimpleName());
    
    //GETS INVOKED AFTER CONSTRUCTOR
    @PostConstruct
    public void init() {

        log.info("---------- CONNECTION TESTING ------------");

        try {
            // LOAD DRIVER
            Class.forName("com.mysql.jdbc.Driver");
            log.info("the driver is loaded");
        } catch (ClassNotFoundException e) {
            log.info("No JDBC Driver found ...");
            e.printStackTrace();

        }

        log.info("EVERYTHING OKAY WITH DRIVER");

        try {
            // CONNECT WITH THE DATABASE db_portalumni
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_portalumni", "root", "");

        } catch (SQLException e) {
            log.info("Connection Failed!");
            e.printStackTrace();

        }

        if (connection != null) {
            log.info("DB CONNECTION OKAY");
        } else {
            log.info("Failed to make connection ...");

        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Logger getLog() {
        return log;
    }
    
}
