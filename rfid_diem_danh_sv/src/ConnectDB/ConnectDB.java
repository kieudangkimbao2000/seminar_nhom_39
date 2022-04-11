/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author kieud
 */
public class ConnectDB {
    private String DB_URL = "jdbc:mysql://localhost:3306";
    private String Username;
    private String Password;
    
    public ConnectDB(String database, String Username, String Password){
        this.DB_URL += "/" + database;
        this.Username = Username;
        this.Password = Password;       
    }
    
    public Connection getConnection(){
        Connection conn = null;
        try
        {
//            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(this.DB_URL, this.Username, this.Password);
            System.out.println("Ket noi database thanh cong");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return conn;
    }
}
