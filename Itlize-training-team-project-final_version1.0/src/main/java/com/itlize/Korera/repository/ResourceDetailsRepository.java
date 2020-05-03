package com.itlize.Korera.repository;

import com.itlize.Korera.dbModels.Columns;
import com.itlize.Korera.dbModels.Resource;
import com.itlize.Korera.dbModels.ResourceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ResourceDetailsRepository extends JpaRepository<ResourceDetails,Integer> {
    List<ResourceDetails> findByResource(Resource resource);
    List<ResourceDetails> findByResourceAndColumn(Resource resource, Columns column);

}