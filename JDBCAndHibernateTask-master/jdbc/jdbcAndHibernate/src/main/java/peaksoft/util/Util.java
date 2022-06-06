package peaksoft.util;

import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
            private static final String URL="jdbc:postgresql://localhost:5432/postgres";
            private static final String USERNAME="postgres";
            private static final String PASSWORD= "1234";


    public static Connection connection(){
        Connection connection=null;
        try{
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Successfully");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
