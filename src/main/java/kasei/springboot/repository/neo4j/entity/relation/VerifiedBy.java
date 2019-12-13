package kasei.springboot.repository.neo4j.entity.relation;

import kasei.springboot.repository.neo4j.entity.node.User;
import kasei.springboot.repository.neo4j.entity.node.Verification;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type="VERIFIED_BY")
public class VerifiedBy {



    @Id
    @GeneratedValue
    private Long relationshipId;

    @StartNode
    private User user;

    @EndNode
    private Verification verification;

    @Property
    private String title;

}
