/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan_bchub;

/**
 *
 * @author Lab
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
     private static final String JBDC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
     private static final String USERNAME = "perpustakaan_bchub";
     private static final String PASSWORD = "123";
     
     public static Connection getConnection() throws SQLException{
         return DriverManager.getConnection(JBDC_URL, USERNAME, PASSWORD);
     }
}
