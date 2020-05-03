package com.itlize.Korera.repository;

import com.itlize.Korera.dbModels.Columns;
import com.itlize.Korera.dbModels.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColumnsRepository extends JpaRepository<Columns,Integer> {
    public List<Columns> findByProjectAndColumnName(Project project, String columnName);
    public List<Columns> findByProject(Project project);
}
