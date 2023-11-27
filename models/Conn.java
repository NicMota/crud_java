package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn 
{   
    
    
    
    public Conn()
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver");

            Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:C:/Users/User/Desktop/crud_java/dbTable/","sa","");
            
            System.out.printf("fodido");
            
        }catch(SQLException e)
        {
            System.out.printf("cuzinho1");
        }catch(ClassNotFoundException e)
        {
            System.out.printf("cuzinho2");
        }
    }
    public static void main(String[] args)
    {
        Conn n = new Conn();
    }
    
}

