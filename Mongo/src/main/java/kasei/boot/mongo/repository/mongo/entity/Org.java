package kasei.boot.mongo.repository.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.function.Consumer;

/*
Mongo 中的 _class 属性： 用于当 class 之间由继承关系的时候，可以从 Mongo 中反序列为子类，而不是都是父类
*/
@Document(collection = "org")
@CompoundIndexes({
    @CompoundIndex(name = "org_idx", def = "{'name': 1, 'age': -1}")  // 1 表示 ascend ； -1 表示 descend
})
@Data
public class Org {
    @Id // 表示当前字段是 mongo 中 document 的 "_id" 字段对应
    private String id;

    @Indexed(unique = true) // 表明当前字段需要加索引，并且是唯一索引
    private String name;
    private Integer year;
    @Transient // 表示当前字段不会保存到 mongo 中
    private String ignored;

    @DBRef  // 表示当前字段跟其他 collection 有关联关系，相当于关系型数据库中的外键关系
    private Set<Staff> staffSet;
}
