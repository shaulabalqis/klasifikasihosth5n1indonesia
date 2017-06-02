/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fungsi1;

import java.sql.*;

/**
 *
 * @author Shaula Balqis Z
 */
public class DBFunction1 {
    
    public static Connection conn = null;
    public static Statement stmt ;
    public static void connectDB() throws ClassNotFoundException{
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/skripsi1"; // sesuaikan dengan nama database anda
	    String user = "root"; // user mysql 
	    String pass = ""; // passowrd mysql
	    try {
		Class.forName(driver);
		conn = DriverManager.getConnection(url,user,pass);
	    } 
	    catch (SQLException e) {
		System.out.println("SQLException: "+e.getMessage());
		System.out.println("SQLState: "+e.getSQLState());
		System.out.println("VendorError: "+e.getErrorCode());
	    }
    }
    
}
