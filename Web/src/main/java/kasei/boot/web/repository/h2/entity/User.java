package kasei.boot.web.repository.h2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
