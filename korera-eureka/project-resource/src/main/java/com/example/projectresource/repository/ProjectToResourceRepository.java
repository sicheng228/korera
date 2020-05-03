package com.example.projectresource.repository;
import com.example.projectresource.dbModels.Project;
import com.example.projectresource.dbModels.ProjectToResource;
import com.example.projectresource.dbModels.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectToResourceRepository extends JpaRepository<ProjectToResource, Integer> {
    public List<ProjectToResource> findByProjectAndResource(Project project, Resource resource);

    public List<ProjectToResource> findByProject(Project project);
}