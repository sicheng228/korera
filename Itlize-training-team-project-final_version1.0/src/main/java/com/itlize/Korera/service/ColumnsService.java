package com.itlize.Korera.service;

import com.itlize.Korera.dbModels.Columns;
import com.itlize.Korera.dbModels.Project;

import java.util.List;

public interface ColumnsService {
    public boolean create(Columns column);
    public boolean create(Columns column,Project project);
    public boolean delete(Columns column);
    public boolean update(Columns column, String newColumnName);
    public Columns get(Integer id);
    public Columns get(Project project, String columnName);
    public List<Columns> getByProject(Project project);
    public String getColumnsJson(Project project);
    public void clear();
}