package kasei.boot.mongo.repository.mongo.dao;



import kasei.boot.mongo.repository.mongo.entity.Org;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrgDao extends MongoRepository<Org, String>, OrgDaoCustomRepository {

}
