package com.etoak.factory;

import java.sql.*;

/**
 *
 */
public class Factory {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getCon(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lee","root","ljc123");
            return con;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(ResultSet rs, Statement pst, Connection con){
        try {
            if(rs!=null)
                rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(pst!=null)
                    pst.close();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if(con!=null)
                        con.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
