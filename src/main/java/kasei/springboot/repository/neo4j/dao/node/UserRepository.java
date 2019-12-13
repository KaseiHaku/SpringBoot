package kasei.springboot.repository.neo4j.dao.node;

import kasei.springboot.repository.neo4j.entity.node.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends Neo4jRepository<User, Long> {

    // save 会在内存中保存一份数据库中的副本，副本改变时自动生成 cypher 语句，但是不会自动 commit 需要调用 save() 方法才 commit 默认 depth=-1
    // load 默认 depth=1 如果全部关联加载，那么设置 depth=-1
    // loadAll
    // delete


    @Query("match(n:User) where n.account={account} return n")
    public User findByAccount(@Param("account") String account);


}
