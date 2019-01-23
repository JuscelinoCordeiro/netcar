/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;

/**
 *
 * @author apolo
 */
public class ConnectionFactory {
    private String url;
    private String user;
    private String passwd;

    public ConnectionFactory() {
        this.url = "jdbc:mysql://localhost/netcar";
        this.user = "dimitri";
        this.passwd = "@!@#rf";
    }
    
    
    public Connection getConnection() throws ClassNotFoundException{
        try{
            Class.forName("com.mysql.jdbc.Driver"); //maldita linha necessaria para a parte web
            return DriverManager.getConnection(url, user, passwd);
        }catch(SQLException e){
            throw new RuntimeException (e);
        }
    }
}
