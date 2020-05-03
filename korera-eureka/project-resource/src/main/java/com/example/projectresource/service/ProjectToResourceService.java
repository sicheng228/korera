package com.example.projectresource.service;

import com.example.projectresource.dbModels.Project;
import com.example.projectresource.dbModels.ProjectToResource;
import com.example.projectresource.dbModels.Resource;

import java.util.List;

public interface ProjectToResourceService {
    public boolean create(ProjectToResource projectToResource, Project project, Resource resource);

    public boolean delete(ProjectToResource projectToResource);

    public ProjectToResource get(Integer id);

    public ProjectToResource get(Project project, Resource resource);

    public List<ProjectToResource> get(Project project);

    public void clear();


}