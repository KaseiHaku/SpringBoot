package kasei.springboot.repository.mongo.dao.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import kasei.boot.mongo.repository.mongo.dao.OrgDaoCustomRepository;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class OrgDaoImpl implements OrgDaoCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<String> updateByName(String name, Integer year) {

        Bson bson = null;                       // 代表 mongo 底层数据
        BsonDocument bsonDocument = null;       // 代表 mongo 中的 document
        BsonWriter bsonWriter = null;           // 写 Bson
        BsonReader bsonReader = null;           // 从 Bson 中读
        Codec codec = null;                     // 从 Bson 中读取写入数据时，用于编码解码成 Bson 支持的格式
        CodecRegistry codecRegistry = null;     // 所有 编码解码器 的注册中心
        CodecProvider codecProvider = null;     //
        BsonTypeClassMap bsonTypeClassMap = null; // 保存 Bson 类型和 Java 类型相互转换的映射关系



        MongoCollection<Document> orgCollection = mongoTemplate.getCollection("org");

        Bson bsonFilter = Filters.eq("name", name);
        Bson bsonUpdate = Updates.set("year", year);
        UpdateResult org = orgCollection.updateOne(bsonFilter, bsonUpdate);

        BsonString bsonString = org.getUpsertedId().asString(); // 已经可以更新成功
        FindIterable<Document> id = orgCollection.find(new BsonDocument("_id", bsonString));


        List<String> res = new LinkedList<>();
        Consumer<Document> documentConsumer = doc -> res.add((String)doc.get("df"));
        id.forEach(documentConsumer);

        return res;
    }
}
