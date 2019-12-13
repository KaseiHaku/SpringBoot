package kasei.springboot.repository.neo4j.entity.node;


import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
public class SystemParam {

    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String code;

    private String value;

    private String remark;



}
