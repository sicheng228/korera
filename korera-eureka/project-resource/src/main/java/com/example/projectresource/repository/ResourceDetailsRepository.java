package com.example.projectresource.repository;
;
import com.example.projectresource.dbModels.Columns;
import com.example.projectresource.dbModels.Resource;
import com.example.projectresource.dbModels.ResourceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ResourceDetailsRepository extends JpaRepository<ResourceDetails, Integer> {
    List<ResourceDetails> findByResource(Resource resource);

    List<ResourceDetails> findByResourceAndColumn(Resource resource, Columns column);

}