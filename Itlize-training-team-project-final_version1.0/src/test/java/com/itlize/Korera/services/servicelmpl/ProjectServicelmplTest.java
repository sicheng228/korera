package com.itlize.Korera.services.servicelmpl;


import com.itlize.Korera.dbModels.Project;
import com.itlize.Korera.dbModels.User;
import com.itlize.Korera.service.ProjectService;
import com.itlize.Korera.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServicelmplTest {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @Test
    public void create() {
        Project toAdd = new Project();
        User user = userService.get("shabby2");
        toAdd.setProjectName("myProject");
        boolean isSuccessful = projectService.create(toAdd,user);
        Assert.assertTrue(isSuccessful);

    }

    @Test
    public void delete() {
        Project toDelete = projectService.get(1);
        System.out.println("deleting " + toDelete.getProjectName());
        boolean isSuccessful = projectService.delete(toDelete);
        Assert.assertTrue(isSuccessful);
    }

    @Test
    public void get() {
        Project res = projectService.get(30);
        Assert.assertTrue(res != null);
    }
}
