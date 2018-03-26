/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.control;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Lucian
 */

@Named
@RequestScoped
public class DeleteUser {

    private final String jdbcDriver = "com.mysql.jdbc.Driver";
    private final String dbUrl = "jdbc:mysql://localhost/db_portalumni";
    private final String caller = "root";
    private final String password = "";
    private Connection connection;

    public DeleteUser() throws ClassNotFoundException, SQLException {
        connection = null;
//        try {
            Class.forName(jdbcDriver);
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(this.dbUrl, this.caller, this.password);
//        } catch (SQLException ex) {
//            System.out.println("Could not connect to database.");
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Driver could not be located.");
//        }
    }

  /*  public void fillTable(String table) {
        System.out.println("Filling table...");
        String sql = "INSERT INTO " + table + " VALUES ('Kevin'), ('Adel'), ('Ramin'), ('Lucian');";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    } */

    public void printTable(String table) {
        System.out.println("\nPrinting table " + table + ":\n--------------------");
        String sql = "SELECT * FROM " + table + ";";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            System.out.println("--------------------");
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(String name) {
        System.out.println("Deleting user...");
        String sql = "DELETE FROM benutzer WHERE Vorname = '" + name + "';";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Deleted user \"" + name + "\" from database.");
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void wipeTable(String table) {
        System.out.println("Wiping table " + table);
        String sql = "DROP TABLE IF EXISTS " + table + ";";
        String sql2 = "CREATE TABLE " + table + "(name VARCHAR(50));";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.execute(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
