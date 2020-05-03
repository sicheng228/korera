package com.itlize.Korera.service.serviceImpl;

import com.itlize.Korera.dbModels.Columns;
import com.itlize.Korera.dbModels.Project;
import com.itlize.Korera.repository.ColumnsRepository;
import com.itlize.Korera.repository.ProjectRepository;
import com.itlize.Korera.service.ColumnsService;
import com.itlize.Korera.service.ResourceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColumnsServiceImpl implements ColumnsService {
    @Autowired
    private ColumnsRepository columnsRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ResourceDetailsService resourceDetailsService;

    @Override
    public boolean create(Columns column) {
        Columns target = get(column.getProject(),column.getColumnName());
        if(target != null){
            System.out.println("Column exist!");
            System.out.println(target.toString());
            return false;
        }
        try{
            columnsRepository.save(column);
        }catch (Exception e){
            System.out.println("Sth wrong happens when creating "+column.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean create(Columns column, Project project) {
        if(column==null){
            System.out.println("null input!");
            return false;
        }
        Columns target = get(project,column.getColumnName());
        if(target != null){
            System.out.println("Column exist!");
            return false;
        }
        try{
            columnsRepository.save(column);
            if(project!=null) {
                project.addColumns(column);
                columnsRepository.save(column);
                projectRepository.save(project);
            }
        }catch (Exception e){
            System.out.println("Sth wrong happens when creating "+column.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Columns column) {
        if(column==null){
            System.out.println("deleting null column");
            return false;
        }
        System.out.println("deleting column: " +column.getId());
        try{

            columnsRepository.delete(column);
        }catch (Exception e){
            System.out.println("Sth wrong happens when deleting "+column.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Columns column, String newColumnName) {
        Columns toUpdate = get(column.getId());
        if(toUpdate==null){
            System.out.println("target column not found!");
            return false;
        }
        column.setColumnName(newColumnName);
        try{
            columnsRepository.save(column);
        }catch (Exception e){
            System.out.println("Sth wrong happens when updating");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Columns get(Integer id) {
        if(id ==null)
            return null;
        Optional<Columns> res = columnsRepository.findById(id);
        if(res.isPresent()){
            return res.get();
        }
        return null;
    }

    @Override
    public Columns get(Project project, String columnName) {

        List<Columns> res = columnsRepository.findByProjectAndColumnName(project,columnName);

        if(res.size()>0){
            if(project!=null && res.get(0).getProject()==null){
                return null;
            }
            return res.get(0);
        }
        return null;
    }

    @Override
    public List<Columns> getByProject(Project project) {
        return columnsRepository.findByProject(project);
    }

    @Override
    public String getColumnsJson(Project project) {
        List<String> columnNames = new ArrayList<String>();

        List<Columns> columns = getByProject(null);
        for(Columns column:columns){
            columnNames.add( String.format("{" +
                    "\"columnName:\":\"%s\"," +
                    "\"id\":\"%d\"," +
                    "\"type\":\"%s\"," +
                    "\"formula\":\"%s\"}",column.getColumnName(),column.getId(),column.getType(),column.getFormula()));
        }
        columns = getByProject(project);
        for(Columns column:columns){
            columnNames.add( String.format("{" +
                    "\"columnName:\":\"%s\"," +
                    "\"id\":\"%d\"," +
                    "\"type\":\"%s\"," +
                    "\"formula\":\"%s\"}",column.getColumnName(),column.getId(),column.getType(),column.getFormula()));
        }
        return "[" + String.join(",",columnNames) + "]";
    }

    @Override
    public void clear() {
        columnsRepository.deleteAll();
    }
}
