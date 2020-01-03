package kasei.boot.security.repository.h2.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Resource {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id", nullable = false, unique = true, length = 32)
    private String id;

    private String uri; // Uniform Resource Identifier 统一资源标识符
}
