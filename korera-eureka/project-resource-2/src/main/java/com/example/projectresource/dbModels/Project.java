package com.example.projectresource.dbModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Project {
    @Id
    @GeneratedValue
    private Integer id;

    private String projectName;

    @CreatedDate
    private Date timeCreated;
    @LastModifiedDate
    private Date lastUpdated;

    @JsonIgnore
    @OneToMany(targetEntity = Columns.class, cascade = CascadeType.REMOVE, mappedBy = "project")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Columns> columns = new HashSet<Columns>();

    @JsonIgnore
    @OneToMany(targetEntity = ProjectToResource.class, cascade = CascadeType.REMOVE, mappedBy = "project")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProjectToResource> resources = new HashSet<ProjectToResource>();


    public String toJson() {
        return "{" +
                "\"id\" : \"" + id +
                "\", \"projectName\":\"" + projectName + '"' +
                '}';
    }

    public Collection<ProjectToResource> getResources() {
        return resources;
    }

    public void addProjectToResource(ProjectToResource vResource) {
        if (resources.contains(vResource)) {
            return;
        }
        resources.add(vResource);
        vResource.setProject(this);
    }

    public void removeProjectToResource(ProjectToResource vResource) {
        if (!resources.contains(vResource)) {
            return;
        }
        resources.remove(vResource);
        vResource.setProject(null);
    }

    public Collection<Columns> getColumns() {
        return columns;
    }

    public void addColumns(Columns column) {
        if (columns.contains(column)) {
            return;
        }
        columns.add(column);
        column.setProject(this);
    }

    public void removeColumns(Columns column) {
        if (!columns.contains(column)) {
            return;
        }
        columns.remove(column);
        column.setProject(null);
    }

    public Project() {
    }

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}