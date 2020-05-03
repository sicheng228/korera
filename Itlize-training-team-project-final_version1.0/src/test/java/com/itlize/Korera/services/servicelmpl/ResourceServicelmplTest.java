package com.itlize.Korera.services.servicelmpl;

import com.itlize.Korera.dbModels.Resource;
import com.itlize.Korera.service.ResourceService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ResourceServicelmplTest {
    @Autowired
    ResourceService resourceService;

    @Test
    public void all(){
        //create
        Integer id=100;
        Resource resource = new Resource();
        resource.setId(id);
        boolean success = resourceService.create(resource);
        Assert.assertTrue(success);
        //read
        Resource res = resourceService.get(id);
        Assert.assertTrue(res!=null);
        //delete
        success = resourceService.delete(res);
        Assert.assertTrue(success);
    }
    @Test
    public void create() {
        Resource resource = new Resource();
        boolean success = resourceService.create(resource);
        Assert.assertTrue(success);

    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }
}
