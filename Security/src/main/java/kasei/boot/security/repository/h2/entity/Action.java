package kasei.boot.security.repository.h2.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Action {

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


}
