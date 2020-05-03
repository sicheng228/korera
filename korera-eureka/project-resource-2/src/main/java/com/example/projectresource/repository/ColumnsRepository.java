package com.example.projectresource.repository;

import com.example.projectresource.dbModels.Columns;
import com.example.projectresource.dbModels.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnsRepository extends JpaRepository<Columns, Integer> {
    public List<Columns> findByProjectAndColumnName(Project project, String columnName);

    public List<Columns> findByProject(Project project);
}
