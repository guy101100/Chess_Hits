/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author makingitbettergo
 */
public class UserDatabase {

    private String url = "jdbc:derby:ChessHitsUsersDB;create=true";
    private String DB_NAME = "USER";
    private String usernameDerby = "hits";
    private String passwordDerby = "hits";
    Connection conn;

    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(url, usernameDerby, passwordDerby);
            System.out.println(url + "   connected....");

        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void createTable() {
        try {
            Statement statement = conn.createStatement();
            String sqlCreate = "create table " + this.DB_NAME + " (ID varchar(20),"
                    + "WINS int, constraint id_pk PRIMARY KEY (ID))";
            
            //statement.close();
            System.out.println("Table created");

        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean doesAccountExist(String accountName) {
        boolean isFound = false;
        try {
            String userTable = "USER";
            Statement statement = conn.createStatement();
            String selectComm = "SELECT ID from " + userTable + " where ID = " + accountName;
            ResultSet rs = statement.executeQuery(selectComm);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                if (rs.getString(1).equals(accountName)) {
                    isFound = true;
                }
            }
            if (!isFound) {
                addNewUser(accountName);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isFound;
    }

    public void addNewUser(String userName) {
        try {
            Statement statement = conn.createStatement();
            String userTable = "USER";
            String sqlUpdate = "insert into " + userTable + " values("
                    + " " + userName + 0 + ")";
            statement.executeUpdate(sqlUpdate);
        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getQuery(String accountName) {
        ResultSet rs = null;

        try {

            System.out.println(" getting user query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);



            String sqlQuery = "select " + accountName + ", from   "
                    + "where brand='Toyota'";

            rs = statement.executeQuery(sqlQuery);
            rs.beforeFirst();
            while (rs.next()) {
                String model = rs.getString("model"); // 1
                int price = rs.getInt(2);
                System.out.println(model + ":  $" + price);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        //return(rs);  
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
