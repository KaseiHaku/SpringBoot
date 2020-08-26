package kasei.boot.repository.mongo.dao;



import kasei.boot.mongo.repository.mongo.entity.Org;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrgDao extends MongoRepository<Org, String>, OrgDaoCustomRepository {

}
