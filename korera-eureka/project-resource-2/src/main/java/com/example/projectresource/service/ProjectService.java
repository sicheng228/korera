package com.example.projectresource.service;

import com.example.projectresource.dbModels.Project;

public interface ProjectService {
    public boolean create(Project projec);

    public boolean delete(Project project);

    public Project get(Integer id);

    public String toJson(Integer id);

    public void clear();
}