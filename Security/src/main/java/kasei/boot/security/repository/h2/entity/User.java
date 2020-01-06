package kasei.boot.security.repository.h2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", indexes = {}) // indexes 用于创建数据库索引
public class User implements UserDetails {

    @Id
    @Column(name="id", nullable = false, unique = true)
    private Integer id;
    @Column(length = 256, nullable = false, unique = true)
    private String account;
    private String nickname;
    @Column(length = 256)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(name = "user_action", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="action_id"))
    private Set<Action> actions;

    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles;

    /**
     * mappedBy: 表示关系维护端不在当前类，而在 Group 类的 users 字段
     * */
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Group> groups;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(actions);
        authorities.addAll(roles);
        authorities.addAll(groups);
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getAccount(), user.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccount());
    }
}
