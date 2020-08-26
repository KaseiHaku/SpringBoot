package kasei.boot.repository.mongo.dao;

import java.util.List;

public interface OrgDaoCustomRepository {

    public List<String> updateByName(String name, Integer year);
}
