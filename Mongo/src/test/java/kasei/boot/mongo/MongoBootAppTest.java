package kasei.boot.mongo;

import kasei.boot.mongo.repository.mongo.entity.Log;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;


@SpringBootTest
class MongoBootAppTest {

    @Test
    public void test(){

        Set<Log> logs = new HashSet<>();
        logs.add(new Log(new ObjectId("5df6059267e6f51675f9db5f"), null, null, null));
        logs.add(new Log(new ObjectId("5df6059267e6f51675f9db5f"), null, null, null));
        System.out.println(logs.size());



    }

}
