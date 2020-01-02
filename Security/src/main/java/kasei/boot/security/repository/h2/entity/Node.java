package kasei.boot.security.repository.h2.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "node")
public class Node {

    @Id
    @Column
    private String id;
    private String name;
}
