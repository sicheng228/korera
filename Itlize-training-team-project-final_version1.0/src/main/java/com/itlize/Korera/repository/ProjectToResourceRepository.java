package com.itlize.Korera.repository;

import com.itlize.Korera.dbModels.Project;
import com.itlize.Korera.dbModels.ProjectToResource;
import com.itlize.Korera.dbModels.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectToResourceRepository extends JpaRepository<ProjectToResource, Integer> {
    public List<ProjectToResource> findByProjectAndResource(Project project, Resource resource);
    public List<ProjectToResource> findByProject(Project project);
}