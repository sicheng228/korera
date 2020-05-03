package com.itlize.Korera.service.serviceImpl;


import com.itlize.Korera.repository.UserRepository;
import com.itlize.Korera.dbModels.User;
import com.itlize.Korera.service.ProjectService;
import com.itlize.Korera.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ProjectService projectService;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public boolean create(User user) {
        try{
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(User user) {
        if(user==null){
            return false;
        }
        System.out.println("deleting user: " +user.getUsername());

        userRepository.delete(user);
        return true;
    }

    @Override
    public User get(String userName) {

        Optional<User> a= userRepository.findById(userName);
        if(a.isPresent()){
            return a.get();
        }
        return null;
    }

    @Override
    public boolean update(String userName, User user) {
        User toUpdate = userRepository.getOne(userName);
        toUpdate.setUsername(user.getUsername());
        toUpdate.setTitle(user.getTitle());
        toUpdate.setPassword(user.getPassword());
        try{
            userRepository.save(toUpdate);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        userRepository.deleteAll();
    }
}


