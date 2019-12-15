package kasei.boot.mongo.controller;

import kasei.boot.mongo.repository.mongo.dao.LogDao;
import kasei.boot.mongo.repository.mongo.entity.Log;
import kasei.boot.mongo.repository.mongo.entity.Org;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/Log", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LogController {

    @Autowired private LogDao logDao;


    @GetMapping
    public Iterable<Log> orgGet(@Nullable @RequestParam Set<String> idSet){

        if (idSet == null || idSet.isEmpty()) {
            return logDao.findAll();
        }

        Set<ObjectId> objectIdSet = new HashSet<>();
        idSet.forEach( id -> objectIdSet.add(new ObjectId(id)));
        return logDao.findAllById(objectIdSet);
    }

    @PostMapping
    public List<Log> logPost(@RequestBody Set<Log> logSet){
        return logDao.insert(logSet);
    }

    @DeleteMapping
    public Set<String>  logDelete(@RequestBody Set<String> idSet, String s){
        Set<Log> logSet = new HashSet<>();
        idSet.forEach(id -> {
            Log log = new Log();
            log.setId(new ObjectId(id));
            logSet.add(log);
        });
        logDao.deleteAll(logSet);
        return idSet;
    }

    @DeleteMapping("/del")
    public Set<ObjectId>  logDelete(@RequestBody Set<ObjectId> objectIdSet){
        Set<Log> logSet = new HashSet<>();
        objectIdSet.forEach( objectId -> {
            Log log = new Log();
            log.setId(objectId);
            logSet.add(log);
        });
        logDao.deleteAll(logSet);
        return objectIdSet;
    }
}
