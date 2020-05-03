package com.itlize.Korera.dbModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Resource {
    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    private Date timeCreated;
    @LastModifiedDate
    private Date lastUpdated;

    @JsonIgnore
    @OneToMany(targetEntity = ProjectToResource.class,cascade = CascadeType.REMOVE,mappedBy = "resource")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ProjectToResource> projects = new HashSet<ProjectToResource>();

    @JsonIgnore
    @OneToMany(targetEntity = ResourceDetails.class,cascade = CascadeType.REMOVE,mappedBy = "resource")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ResourceDetails> entries = new HashSet<ResourceDetails>();



    public Collection<ResourceDetails> getEntries() {
        return entries;
    }

    public void addEntries(ResourceDetails entry){
        if(entries.contains(entry)){
            return;
        }
        entries.add(entry);
        entry.setResource(this);
    }
    public void removeEntries(ResourceDetails entry){
        if(!entries.contains(entry)){
            return;
        }
        entries.remove(entry);
        entry.setResource(null);
    }

    public Collection<ProjectToResource> getProjects() {
        return projects;
    }

    public void addProjects(ProjectToResource project) {
        if(projects.contains(project))
            return;
        projects.add(project);
        project.setResource(this);
    }
    public void removeProjects(ProjectToResource project){
        if(!projects.contains(project)){
            return;
        }
        projects.remove(project);
        project.setResource(null);
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                '}';
    }
    public String toJson(List<String> entries){
        String ret = null;
        List<String> colsContent = new ArrayList<String>();
        for( String entry : entries){
            colsContent.add(entry);
        }
        ret = "{" + String.join(",",colsContent) + "}";
        return String.format("{\"resourceId\": \"%d\", \"content\": \"%s\"}", getId(),ret);
    }

    public Resource() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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