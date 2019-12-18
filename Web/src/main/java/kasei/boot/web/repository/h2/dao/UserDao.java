package kasei.boot.web.repository.h2.dao;

import kasei.boot.web.repository.h2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> gg();

    User findByAccount(String account);
}
