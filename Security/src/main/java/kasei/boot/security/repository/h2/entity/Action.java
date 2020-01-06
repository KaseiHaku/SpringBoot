package kasei.boot.security.repository.h2.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Action implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id", nullable = false, unique = true, length = 32)
    private String id;
    private String name;

    /**
     * optional = false     表示删除 ResourceAction 对 Resource 不影响
     * */
    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_id")
    private Resource resource;


    /**
     * mappedBy: 表示关系维护端不在当前类，而在 Role 类的 actions 字段
     * */
    @ManyToMany(mappedBy = "actions", cascade = CascadeType.REMOVE)
    private Set<Role> roles;

    @ManyToMany(mappedBy = "actions", cascade = CascadeType.REMOVE)
    private Set<User> users;

    @ManyToMany(mappedBy = "actions", cascade = CascadeType.REMOVE)
    private Set<Group> groups;


    @Override
    public String getAuthority() {
        return "ACTION_" + this.name;
    }
}
