package com.itlize.Korera.services.servicelmpl;


import com.itlize.Korera.dbModels.User;
import com.itlize.Korera.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServicelmplTest {
    @Autowired
    UserService userService;


    @Test
    void createUser() {
        User toAdd = new User("shabby2","admin","123");
        boolean a = userService.create(toAdd);
        Assert.assertTrue(a);
    }

    @Test
    void deleteUser() {
        User user = userService.get("shabby1");
        boolean a = userService.delete(user);
        Assert.assertTrue(a);
    }

    @Test
    void getUser() {
        User user = userService.get("shabby2");
        Assert.assertTrue(user!=null);
    }

    @Test
    void updateUser() {
    }
}
