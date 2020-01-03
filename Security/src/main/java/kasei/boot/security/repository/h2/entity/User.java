package kasei.boot.security.repository.h2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", indexes = {}) // indexes 用于创建数据库索引
public class User {

    @Id
    @Column(name="id", nullable = false, unique = true)
    private Integer id;
    @Column(length = 256, nullable = false, unique = true)
    private String account;
    @Column(length = 256)
    private String password;

    @ManyToMany
    @JoinTable(name = "user_action", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="action_id"))
    private Set<Action> actions;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;
}
