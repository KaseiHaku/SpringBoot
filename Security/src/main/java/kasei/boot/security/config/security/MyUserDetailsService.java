package kasei.boot.security.config.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import kasei.boot.security.repository.h2.dao.UserDao;
import kasei.boot.security.repository.h2.entity.Role;
import kasei.boot.security.repository.h2.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Set;

public class MyUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    public MyUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byAccount = userDao.findByAccount(username);
        if (byAccount == null) {
            return null;
        }
        UserDetails userDetails = byAccount;


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(byAccount);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return userDetails;
    }
}
