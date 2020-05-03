package com.example.projectresource.repository;

import com.example.projectresource.dbModels.Project;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
