package com.itlize.Korera.services;

import com.itlize.Korera.dbModels.*;
import com.itlize.Korera.service.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceDetailsServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    ResourceService resourceService;
    @Autowired
    ResourceDetailsService resourceDetailsService;
    @Autowired
    ColumnsService columnsService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectToResourceService projectToResourceService;

    @Test
    public void createBasicDB() {
        User user = new User("shabby","admin","123");
        User user1 = new User("shabby1","admin","123");
        userService.create(user);
        userService.create(user1);

        Project project = new Project();
        Project project1 = new Project();
        projectService.create(project,user);
        projectService.create(project1,user1);

        Columns column = new Columns("column1", ProjectColumnEnum.NUMBER,null);
        Columns column1 = new Columns("column2", ProjectColumnEnum.FORMULA,"a*b");
        Columns column3 = new Columns("column1", null,null);

        columnsService.create(column,project);
        columnsService.create(column1,project);
        columnsService.create(column3);

        Resource resource = new Resource();
        Resource resource1 = new Resource();
        Resource resource2 = new Resource();
        resourceService.create(resource);
        resourceService.create(resource1);
        resourceService.create(resource2);
        List<Resource> resources = resourceService.getAll();

        ProjectToResource ptr = new ProjectToResource();
        ProjectToResource ptr1 = new ProjectToResource();
        ProjectToResource ptr2 = new ProjectToResource();
        projectToResourceService.create(ptr,project,resource);
        projectToResourceService.create(ptr1,project1,resource);
        projectToResourceService.create(ptr2,project1,resource1);


        ResourceDetails rd = new ResourceDetails("columnValue");
        ResourceDetails rd1 = new ResourceDetails("columnValue");
        ResourceDetails rd2 = new ResourceDetails("columnValue");
        ResourceDetails rd3 = new ResourceDetails("columnValue");
        ResourceDetails rd4 = new ResourceDetails("columnValue");
        ResourceDetails rd5 = new ResourceDetails("columnValue");
        resourceDetailsService.create(rd, resource, column);
        resourceDetailsService.create(rd1, resource, column3);
        resourceDetailsService.create(rd2, resource1, column);
        resourceDetailsService.create(rd3, resource1, column3);
        resourceDetailsService.create(rd4, resource2, column);
        resourceDetailsService.create(rd5, resource2, column3);


    }

    @Test
    public void clearDB(){
        userService.clear();
        columnsService.clear();
        projectToResourceService.clear();
        projectService.clear();
        resourceService.clear();
        resourceDetailsService.clear();
    }
}