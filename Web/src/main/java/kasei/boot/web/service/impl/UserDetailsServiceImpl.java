package kasei.boot.web.service.impl;

import kasei.boot.web.repository.h2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        kasei.boot.web.repository.h2.entity.User byAccount = userDao.findByAccount(username);


        // 注意这里的 User 是  org.springframework.security.core.userdetails.User 不是自定义的 User
        UserDetails userDetails = new User(username, byAccount.getPassword(), new HashSet<>());
        return userDetails;
    }
}
