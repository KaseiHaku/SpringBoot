package kasei.springboot.repository.neo4j.entity.node;


import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity
@Data
public class Verification {
    @Id
    @GeneratedValue
    private Long id;

    private String identifier;
    private Integer identifierType;
    private String certificate;

    @Relationship(type="AUTHENTICATE_BY", direction=Relationship.INCOMING)
    private User user;

}
