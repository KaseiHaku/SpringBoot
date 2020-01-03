package kasei.boot.security.controller;

import kasei.boot.security.repository.h2.dao.UserDao;
import kasei.boot.security.repository.h2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {


    @Autowired
    private UserDao userDao;


    @GetMapping("/gg")
    public List<User> gg(){
        return userDao.gg();
    }

    @GetMapping("/id")
    public User getById(){
        Optional<User> byId = userDao.findById(1);
        return byId.get();
    }


}
