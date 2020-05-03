package com.example.projectresource.service;

import com.example.projectresource.dbModels.Project;
import com.example.projectresource.dbModels.Resource;

import java.util.List;

public interface ResourceService {
    public boolean create(Resource resource);

    public boolean delete(Resource resource);

    public Resource get(Integer id);

    public List<Resource> getAll();

    public String toJson(Integer id);

    public String toJson(Integer id, Project project);

    public String toJson(Resource resource);

    public String toJson(Resource resource, Project project);

    public String getAllJson();

    public void clear();
}