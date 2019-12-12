package kasei.springboot.repository.mongo.dao.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import kasei.springboot.repository.mongo.dao.OrgDao;
import kasei.springboot.repository.mongo.entity.Org;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class OrgDaoImpl implements OrgDao {

    @Autowired private MongoDbFactory mongoDbFactory;

    @Override
    public List<Org> findAll() {
        MongoDatabase springcloud = mongoDbFactory.getDb("springcloud");
        FindIterable<Document> org = springcloud.getCollection("org").find();

        List<Org> orgList = new ArrayList<>();
        Consumer<Document> orgConsumer = doc -> {
            Org org1 = new Org();
            org1.setName((String)doc.get("name"));
            orgList.add(org1);
        };
        org.forEach(orgConsumer);
        return orgList;
    }
}
