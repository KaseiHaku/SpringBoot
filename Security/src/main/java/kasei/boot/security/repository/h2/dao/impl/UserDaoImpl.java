package kasei.boot.security.repository.h2.dao.impl;

import kasei.boot.security.repository.h2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/** TODO 扩充 UserDao 接口不支持的 jdbc 操作
 * @trap 该实现类不需要  implements UserDao ，只需要保持方法名一致即可，
 *      因为 spring 会自动创建 UserDao 的实现类，然后会整合 *Impl 结尾的类中的方法到实现类中
 * */
@Repository
public class UserDaoImpl {

    @Autowired private JdbcTemplate jdbcTemplate;

    public List<User> gg(){
        RowMapper<User> rowMapper = new BeanPropertyRowMapper(User.class);
        List<User> query = jdbcTemplate.query("select * from user", rowMapper);
        return query;
    }
}
