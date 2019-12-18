package kasei.boot.web.repository.h2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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
