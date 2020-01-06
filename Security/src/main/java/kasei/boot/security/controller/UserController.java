package kasei.boot.security.controller;

import kasei.boot.security.repository.h2.dao.UserDao;
import kasei.boot.security.repository.h2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/User", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {


    @Autowired
    private UserDao userDao;


    @PostMapping
    public List<User> batchInsertUser(@RequestBody Set<User> users){
        List<User> dbUsers = userDao.saveAll(users);
        return dbUsers;
    }


    @GetMapping
    public List<User> getAllUser(){
        return userDao.customGetAllUser();
    }

    @GetMapping("/id")
    public User getById(){
        Optional<User> byId = userDao.findById(1);
        return byId.get();
    }


}
