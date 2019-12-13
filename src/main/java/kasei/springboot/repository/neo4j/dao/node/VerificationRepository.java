package kasei.springboot.repository.neo4j.dao.node;

import kasei.springboot.repository.neo4j.entity.node.Verification;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface VerificationRepository extends Neo4jRepository<Verification, Long> {

    @Query("match(n:Verification{identifier:{identifier}, identifierType:{identifierType}, credential:{credential}}) return n")
    public Verification findByIdentifierAndIdentifierTypeAndCredential(
        @Param("identifier") String identifier,
        @Param("identifierType") Integer identifierType,
        @Param("credential") String credential
    );

    public Verification findByIdentifierAndIdentifierType(String identifier, Integer identifierType);

}
