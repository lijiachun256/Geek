package com.etoak.test;

import com.etoak.dao.UserDao;
import com.etoak.po.User;

import java.text.SimpleDateFormat;

/**
 *
 */
public class TestPreparedStatement {
    public static void main(String[] args) {
        try {
            UserDao dao = new UserDao();
            User user = new User(null,"凄","12000",new SimpleDateFormat("yyyy-MM-dd").parse("2017-8-7"));
            User user1 = new User(3,null,"21000",null);
            //System.out.println(dao.addUser(user));
            //System.out.println(dao.updateUser(user1));
            //System.out.println(dao.queryUserByNameAndPass("绯","99000"));
            /*for(User users:dao.pageUser(0,3))
                System.out.println(users);*/
            System.out.println(dao.countUser());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
