package kasei.boot.mongo.repository.mongo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kasei.boot.mongo.config.json.ObjectIdJsonDeserializer;
import kasei.boot.mongo.config.json.ObjectIdJsonSerializer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.Set;

@Data // 该注解包含了 Lombok 中以下所有注解的功能 @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"} )
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "log")
public class Log {

    @Id
    // @Setter(AccessLevel.PRIVATE)    // 让 setter 方法私有化，不允许代码设置 id
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    @JsonDeserialize(using = ObjectIdJsonDeserializer.class)
    private ObjectId id;
    private Set<String> tags;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")    // 配置 Date 类型进行 JSON 转换时的格式
    private Date createTime;

}
