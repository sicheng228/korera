package com.example.projectresource.service;


import com.example.projectresource.dbModels.Columns;
import com.example.projectresource.dbModels.Resource;
import com.example.projectresource.dbModels.ResourceDetails;

import java.util.List;

public interface ResourceDetailsService {
    public boolean create(ResourceDetails resourceDetails, Resource resource, Columns column);

    public boolean delete(ResourceDetails resourceDetails);

    public boolean update(ResourceDetails resourceDetails);

    public boolean update(Resource resource, Columns column, String columnValue);

    public ResourceDetails get(Integer id);

    public ResourceDetails get(Integer resourceId, Columns column);

    public ResourceDetails get(Resource resource, Columns column);

    public List<ResourceDetails> get(Resource resource);

    public void clear();
}