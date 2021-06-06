package com.etoak.test;

import com.etoak.factory.Factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 */
public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection con = Factory.getCon();

            Statement sm = con.createStatement();

            String sqlsql1 = "select * from user";
            String sqlsml1 = "insert into user values(null,'王五','12345')";

            /*boolean flag1 = sm.execute(sqlsql1);
            System.out.println(flag1);
            boolean flag2 = sm.execute(sqlsml1);
            System.out.println(flag2);*/
            //int count1 = sm.executeUpdate(sqlsql1);
            /*int count = sm.executeUpdate(sqlsml1);
            System.out.println(count);*/

            ResultSet rs = sm.executeQuery(sqlsql1);
            /*while(rs.next()){
                System.out.println("id："+ rs.getInt(1)+"\t用户名："+rs.getString(2)+"\t用户密码："+rs.getString(3));
            }*/
            while(rs.next()){
                System.out.println("id："+ rs.getInt("id")+"\t用户名："+rs.getString("name")+"\t用户密码："+rs.getString("pass"));
            }


        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
