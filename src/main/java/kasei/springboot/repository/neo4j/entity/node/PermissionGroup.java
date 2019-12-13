package kasei.springboot.repository.neo4j.entity.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Set;

@NodeEntity
@Data
public class PermissionGroup {

    @Id
    @GeneratedValue
    private Long id;
    private Set<Permission> permissions;  // 当前权限组包含的独立权限 include 关系
    private Set<PermissionGroup> permissionGroups; // 当前权限组包含的其他权限组 import 关系
}
