package com.example.projectresource.repository;

import com.example.projectresource.dbModels.Resource;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResourceRepository extends JpaRepository<Resource, Integer> {

}