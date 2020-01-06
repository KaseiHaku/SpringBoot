package kasei.boot.security.repository.h2.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id", nullable = false, unique = true, length = 32)
    private String id;

    private String name;

    @ManyToMany
    /**
     * 关系维护端：负责关系的解除和绑定，本例中关系维护端为 Role
     * name = "role_action"     表示多对多关系的关系关联表的表名
     * joinColumns              表示在关系关联表中，表示 Role 表 ID 外建的列名
     * inverseJoinColumns       表示在关系关联表中，表示 Action 表 ID 外建的列名
     * */
    @JoinTable(name = "role_action", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name="action_id"))
    private Set<Action> actions;

    @ManyToMany
    @JoinTable(name = "role_role", joinColumns = @JoinColumn(name = "parent_role_id"), inverseJoinColumns = @JoinColumn(name="child_role_id"))
    private Set<Role> roles;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @ManyToMany(mappedBy = "roles")
    private Set<Group> groups;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name;
    }


}
