package com.itlize.Korera.service;

import com.itlize.Korera.dbModels.Project;
import com.itlize.Korera.dbModels.User;

public interface ProjectService {
    public boolean create(Project project, User user);
    public boolean delete(Project project);
    public Project get(Integer id);
    public String toJson(Integer id);
    public void clear();
}