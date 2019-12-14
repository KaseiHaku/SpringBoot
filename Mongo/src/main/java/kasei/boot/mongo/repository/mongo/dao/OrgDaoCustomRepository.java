package kasei.boot.mongo.repository.mongo.dao;

import kasei.boot.mongo.repository.mongo.entity.Org;

import java.util.List;

public interface OrgDaoCustomRepository {

    public List<String> updateByName(String name, Integer year);
}
