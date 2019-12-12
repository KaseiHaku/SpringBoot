package kasei.springboot.repository.mongo.dao;

import kasei.springboot.repository.mongo.entity.Org;

import java.util.List;

public interface OrgDao {

    List<Org> findAll();
}
