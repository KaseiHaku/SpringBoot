package kasei.springboot.repository.neo4j.entity.node;


import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
public class Permission {

    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String resourceId; // 资源标识符

    private String operator; // 资源操作符 create read update delete

}
