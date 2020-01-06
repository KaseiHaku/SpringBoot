package kasei.boot.security.repository.h2.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "groups") // 表明不能跟关键字冲突，否则不能自动创建
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Group implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id", nullable = false, unique = true, length = 32)
    private String id;

    private String name;

    @ManyToMany
    @JoinTable(name = "group_user", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name="user_id"))
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "group_group", joinColumns = @JoinColumn(name = "parent_group_id"), inverseJoinColumns = @JoinColumn(name="child_group_id"))
    private Set<Group> groups;

    @ManyToMany
    @JoinTable(name = "group_role", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name = "group_action", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name="action_id"))
    private Set<Action> actions;


    @Override
    public String getAuthority() {
        return "GROUP_" + this.name;
    }
}
