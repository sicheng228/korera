package com.itlize.Korera.service;

import com.itlize.Korera.dbModels.Project;
import com.itlize.Korera.dbModels.ProjectToResource;
import com.itlize.Korera.dbModels.Resource;

import java.util.List;

public interface ProjectToResourceService {
    public boolean create(ProjectToResource projectToResource, Project project, Resource resource);
    public boolean delete(ProjectToResource projectToResource);
    public ProjectToResource get(Integer id);
    public ProjectToResource get(Project project, Resource resource);
    public List<ProjectToResource> get(Project project);
    public void clear();


}