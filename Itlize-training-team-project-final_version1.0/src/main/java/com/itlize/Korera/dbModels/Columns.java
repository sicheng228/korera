package com.itlize.Korera.dbModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Columns {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    private Date timeCreated;
    @LastModifiedDate
    private Date lastUpdated;

    @ManyToOne(targetEntity = Project.class,cascade = CascadeType.DETACH)
    private Project project;

    @Override
    public String toString() {
        return "Columns{" +
                "id=" + id +
                ", project=" + project +
                ", columnName='" + columnName + '\'' +
                ", type=" + type +
                ", formula='" + formula + '\'' +
                '}';
    }
    @JsonIgnore
    @OneToMany(targetEntity = ResourceDetails.class,cascade = CascadeType.REMOVE,mappedBy = "column")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<ResourceDetails> entries = new HashSet<ResourceDetails>();

    private String columnName;

    @Enumerated(EnumType.STRING)
    private ProjectColumnEnum type;

    private String formula;

    public Collection<ResourceDetails> getEntries() {
        return entries;
    }

    public String toJson(){
        String ret;
        ret = "{"+"\"columnId\" : " + '"' + id +'"' +',' + "\"columnName\" : " + '"' + columnName +'"' +'}' ;
        return ret;
    }

    public void addEntries(ResourceDetails entry){
        if(entries.contains(entry)){
            return;
        }
        entries.add(entry);
        entry.setColumn(this);
    }
    public void removeEntries(ResourceDetails entry){
        if(!entries.contains(entry)){
            return;
        }
        entries.remove(entry);
        entry.setColumn(null);
    }

    public Columns( String columnName, ProjectColumnEnum type, String formula) {
        this.columnName = columnName;
        this.type = type;
        this.formula = formula;
    }

    public Columns() {
    }

    public Columns(String columnName) {
        this.columnName = columnName;
    }

    public Columns(Project projectId, String columnName) {
        this.project = projectId;
        this.columnName = columnName;
    }

    public ProjectColumnEnum getType() {
        return type;
    }

    public void setType(ProjectColumnEnum type) {
        this.type = type;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project projectId) {
        this.project = projectId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}