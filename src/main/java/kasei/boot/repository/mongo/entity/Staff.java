package kasei.boot.repository.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Staff {
    @Id
    private String id;
    @Indexed(unique = true)
    private String account;
    @Indexed(unique = true)
    private String nickName;

}
