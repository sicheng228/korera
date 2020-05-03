package com.itlize.Korera.service.serviceImpl;

import com.itlize.Korera.dbModels.Project;
import com.itlize.Korera.dbModels.ProjectToResource;
import com.itlize.Korera.dbModels.User;
import com.itlize.Korera.repository.ProjectRepository;
import com.itlize.Korera.repository.UserRepository;
import com.itlize.Korera.service.ColumnsService;
import com.itlize.Korera.service.ProjectService;
import com.itlize.Korera.service.ProjectToResourceService;
import com.itlize.Korera.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServicelmpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectToResourceService projectToResourceService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ColumnsService columnsService;

    @Override
    public boolean create(Project project, User user) {
        if(project==null || user ==null) {
            System.out.println("null input!");
            return false;
        }
        Project target = get(project.getId());
        if(target!=null){
            System.out.println("project already exists");
            return false;
        }
        try{
            projectRepository.save(project);

            user.addProjects(project);
            projectRepository.save(project);
            userRepository.save(user);
        }catch (Exception e){
            System.out.println("Sth wrong happens when creating: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return  true;
    }


    @Override
    @Transactional
    public boolean delete(Project project) {
        if(project==null){
            System.out.println("null input");
            return false;
        }
        System.out.println("deleting project: " +project.getId());

        try{

            projectRepository.delete(project);
        }catch (Exception e){
            System.out.println("Sth wrong happens when deleting");
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    @Override
    public Project get(Integer id) {
        if(id==null)
            return null;
        Optional<Project> res = projectRepository.findById(id);
        if(res.isPresent()){
            return res.get();
        }
        return null;
    }

    @Override
    public String toJson(Integer id) {
        Project project = get(id);
        if(id==null||project==null)
            return "{\"error\":\"Project does not exist!\"}";
        List<ProjectToResource> resources = projectToResourceService.get(project);
        List<String> resourceJsons = new ArrayList<String>();
        for(ProjectToResource ptr : resources){
            String resourceJson = resourceService.toJson(ptr.getResource(),project);
            resourceJsons.add(resourceJson);
        }
        return "[" + String.join("", resourceJsons) + "]";
    }

    @Override
    public void clear() {
        projectRepository.deleteAll();
    }
}
