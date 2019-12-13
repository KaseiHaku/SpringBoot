package kasei.springboot.repository.neo4j.dao.node;


import kasei.springboot.repository.neo4j.entity.node.SystemParam;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SystemParamRepository extends Neo4jRepository<SystemParam, Long> {

    public SystemParam findByCode(String code);
}
