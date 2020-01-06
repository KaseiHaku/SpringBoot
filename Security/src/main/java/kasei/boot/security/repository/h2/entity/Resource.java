package kasei.boot.security.repository.h2.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
/** TODO @Trap 表明不能跟关键字冲突，否则不能自动创建 */
@Table(name = "resource")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Resource {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id", nullable = false, unique = true, length = 32)
    private String id;

    private String uri; // Uniform Resource Identifier 统一资源标识符

    /**
     * CascadeType.REMOVE: 当在 Resource 实例中删除 actions 中的一个 action 时，会在数据库 action 表中删除对应的 action
     * CascadeType.PERSIST: 当在 Resource 实例中新增 actions 中的一个 action 时，会在数据库 action 表中插入对应的 action
     * CascadeType.MERGE: 当在 Resource 实例中修改 actions 中的一个 action 时，会在数据库 action 表中修改对应的 action
     * CascadeType.REFRESH: 当多线程别人修改了实例，需要先刷新当前实例的内容，再保存
     * CascadeType.DETACH: 当删除一个实例时，由于该实例存在外键，删不了，该配置会删除所有外键关系
     * CascadeType.ALL: 以上所有配置
     * */
    @OneToMany(mappedBy = "resource", cascade = CascadeType.REMOVE)
    private Set<Action> actions;
}
