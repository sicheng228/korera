package com.itlize.Korera.service.serviceImpl;

import com.itlize.Korera.dbModels.Columns;
import com.itlize.Korera.dbModels.Project;
import com.itlize.Korera.dbModels.Resource;
import com.itlize.Korera.dbModels.ResourceDetails;
import com.itlize.Korera.repository.ResourceRepository;
import com.itlize.Korera.service.ColumnsService;
import com.itlize.Korera.service.ProjectToResourceService;
import com.itlize.Korera.service.ResourceDetailsService;
import com.itlize.Korera.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServicelmpl implements ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ColumnsService columnsService;
    @Autowired
    private ResourceDetailsService resourceDetailsService;
    @Autowired
    private ProjectToResourceService projectToResourceService;

    @Override
    public boolean create(Resource resource) {
        if(resource==null){
            System.out.println("null input detected");
            return false;
        }
        System.out.println("Adding "+ resource.toString());
        if(resource.getId()!=null) {
            Optional<Resource> target = resourceRepository.findById(resource.getId());
            if (target.isPresent()) {
                System.out.println("resource exist:" + resource.toString());
                return false;
            }
        }
        try{
            resourceRepository.save(resource);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
        System.out.println("resource added.");
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Resource resource) {
        if(resource==null){
            System.out.println("deleting null resource");
        }
        System.out.println("deleting resource: " +resource.getId());
        try{

            resourceRepository.delete(resource);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Override
    public Resource get(Integer id) {
        Optional<Resource> target = resourceRepository.findById(id);
        if(target.isPresent()){
            return target.get();
        }else{
            return null;
        }
    }

    @Override
    public List<Resource> getAll() {
        return resourceRepository.findAll();
    }

    @Override
    public String toJson(Integer id) {
        return toJson(id,null);
    }

    @Override
    public String toJson(Integer id, Project project) {
        Resource resource = get(id);
        List<Columns> columns = columnsService.getByProject(null);
        if(project!=null){
            columns.addAll(columnsService.getByProject(project));
        }
        List<String> entries = new ArrayList<String>();
        for(Columns column : columns){
            ResourceDetails entry = resourceDetailsService.get(resource,column);
            entries.add(entry.toEntry());
        }
        return resource.toJson(entries);
    }

    @Override
    public String toJson(Resource resource) {
        return toJson(resource,null);
    }

    @Override
    public String toJson(Resource resource, Project project) {
        List<Columns> columns = columnsService.getByProject(null);
        if(project!=null){
            columns.addAll(columnsService.getByProject(project));
        }
        List<String> entries = new ArrayList<String>();
        for(Columns column : columns){
            ResourceDetails entry = resourceDetailsService.get(resource,column);
            if(entry!=null)
                entries.add(entry.toEntry());
        }
        return resource.toJson(entries);
    }

    @Override
    public String getAllJson() {
        List<Resource> resources = getAll();
        List<String> resourceJsons = new ArrayList<String>();
        for(Resource resource : resources){
            resourceJsons.add(toJson(resource,null));
        }
        return "[" + String.join(",", resourceJsons) + "]";
    }

    @Override
    public void clear() {
        resourceRepository.deleteAll();
    }
}
