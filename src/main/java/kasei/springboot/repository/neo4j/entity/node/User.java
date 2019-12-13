package kasei.springboot.repository.neo4j.entity.node;

import lombok.Data;
import org.neo4j.ogm.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@NodeEntity(label="User")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String account;

    @Property(name = "nickname")
    private String nickname;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date createTime;

    @Relationship(type="AUTHENTICATE_BY", direction=Relationship.OUTGOING)
    private Set<Verification> authenticateBy;

    private Set<Permission> permissions;  // 当前权限组包含的独立权限 include 关系
    private Set<PermissionGroup> permissionGroups; // 当前权限组包含的其他权限组 import 关系






}
