package kasei.boot.controller;


import kasei.boot.mongo.repository.mongo.dao.OrgDao;
import kasei.boot.mongo.repository.mongo.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/Org", produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrgController {

    @Autowired private OrgDao orgDao ;

    @GetMapping
    public List<Org> orgGet(@Nullable @RequestParam Set<String> idSet){

        if (idSet == null) {
            return orgDao.findAll();
        }
        List<Org> orgList = new LinkedList<>();
        Iterable<Org> allById = orgDao.findAllById(idSet);
        allById.forEach(org -> orgList.add(org));
        return orgList;
    }

    @PostMapping
    public List<Org> orgPost(@RequestBody Set<Org> orgSet){
        List<Org> insert = orgDao.insert(orgSet);
        return insert;
    }


    @DeleteMapping
    public Set<String> orgDelete(@RequestBody Set<String> idSet){
        Set<Org> orgSet = new HashSet<>();
        idSet.forEach(id -> {
            Org org = new Org();
            org.setId(id);
            orgSet.add(org);
        });
        orgDao.deleteAll(orgSet);
        return idSet;
    }


    /** TODO 全量更新 */
    @PutMapping
    public List<Org> orgPut(@RequestBody Set<Org> orgSet){
        return orgDao.saveAll(orgSet);
    }


    /** TODO 部分更新 */
    @PatchMapping
    public List<String> orgPatch(@RequestBody Set<Org> orgSet){

        String name = null;
        Integer year = null;
        for (Org org : orgSet) {
            name = org.getName();
            year = org.getYear();
            break;
        }

        List<String> strings = orgDao.updateByName(name, year);
        return strings;
    }

}
