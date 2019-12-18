package kasei.boot.web.repository.h2.dao;

import kasei.boot.web.repository.h2.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeDao extends JpaRepository<Node, String> {
}
