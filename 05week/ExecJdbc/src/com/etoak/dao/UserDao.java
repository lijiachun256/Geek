package com.etoak.dao;

import com.etoak.factory.Factory;
import com.etoak.po.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UserDao {

    Connection con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    public boolean addUser(User user){
        try {
            /*String sql = "insert into user values(null,'"+user.getName()+"','"+user.getPass()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(user.getDate())+"')";
            con = Factory.getCon();
            st = con.createStatement();
            return st.executeUpdate(sql)==1;*/
            String sql2 = "insert into user values(null,?,?,?)";
            con = Factory.getCon();
            pst = con.prepareStatement(sql2);
            pst.setString(1,user.getName());
            pst.setString(2,user.getPass());
            pst.setDate(3,new java.sql.Date(user.getDate().getTime()));
            return pst.executeUpdate()==1;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Factory.close(null,pst,con);
        }
    }

    public boolean delUser(String name,String pass){
        try {
            String sql = "delete from user where name = ? and pass = ?";
            con = Factory.getCon();
            pst = con.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,pass);
            return pst.executeUpdate()==1;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Factory.close(null,pst,con);
        }
    }

    public User queryUserByNameAndPass(String name,String pass){
        try {
            String sql = "select * from user where name = ? and pass = ?";
            con = Factory.getCon();
            pst = con.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,pass);
            rs = pst.executeQuery();
            if(rs.next()){
                return new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4));
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            Factory.close(rs,pst,con);
        }
    }

    public boolean updateUser(User user){
        try {
            String sql = "update user set ";
            if(user.getName()!=null){
                sql += "name = '"+user.getName()+"',";
            }
            if(user.getPass()!=null){
                sql += "pass = '"+user.getPass()+"',";
            }
            if(user.getDate()!=null){
                sql += "date = '"+new SimpleDateFormat("yyyy-MM-dd").format(user.getDate())+"',";
            }
            sql = sql.substring(0,sql.length()-1);
            sql += " where id = "+user.getId();
            con = Factory.getCon();
            pst = con.prepareStatement(sql);
            return pst.executeUpdate()==1;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            Factory.close(null,st,con);
        }
    }

    public List<User> pageUser(Integer index,Integer max){
        try {
            String sql = "select * from user limit ?,?";
            con = Factory.getCon();
            pst = con.prepareStatement(sql);
            pst.setInt(1,index);
            pst.setInt(2,max);
            rs = pst.executeQuery();
            List<User> list = new ArrayList<>();
            while(rs.next()){
                list.add(new User(rs.getInt("id"),rs.getString("name"),rs.getString("pass"),rs.getDate("date")));
            }
            return list;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            Factory.close(rs,pst,con);
        }
    }

    public Integer countUser(){
        try {
            String sql = "select count(*) from user";
            con = Factory.getCon();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            Factory.close(null,pst,con);
        }
    }

}
