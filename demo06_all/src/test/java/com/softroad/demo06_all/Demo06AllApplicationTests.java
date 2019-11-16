package com.softroad.demo06_all;

import com.softroad.pojo.Users;
import com.softroad.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo06AllApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {

        Users users = new Users();
        users.setUserid(13);
        users.setUsername("woshi");
        users.setUserage(56);
        users.setIphone(1234567);
        users.setAddree("别境");
        //userService.addUser(users);
        System.err.println(users.getUserid());
        System.err.println(userService.addUser(users));
    }

    @Test
    public void selectByNameUser() {

        Users users = new Users();
        users.setUserid(13);
        users.setUsername("woshi");
        users.setUserage(56);
        users.setIphone(1234567);
        users.setAddree("别境");
        String username = users.getUsername();
        //userService.addUser(users);
        List<Users> users1 =  userService.selectByName(username);
        System.err.println(users.getUsername());

        System.err.println(users1);
    }
    @Test
    public void loginUser() {
        Users users = new Users();
        users.setUserid(18);
        users.setUsername("woshi");
        users.setUserage(56);
        users.setIphone(1234567);
        users.setAddree("别境");

        int iphone = users.getUserid();

         users = userService.loginUser(iphone);
        System.err.println(users.getUsername());
    }

}
