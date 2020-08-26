package kasei.springboot.repository.mongo.dao;

import kasei.boot.mongo.repository.mongo.entity.Log;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogDao extends MongoRepository<Log, ObjectId> {
}
