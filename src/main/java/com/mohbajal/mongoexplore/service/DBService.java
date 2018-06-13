package com.mohbajal.mongoexplore.service;

import com.google.gson.Gson;
import com.mohbajal.mongoexplore.dto.GenericSearchResults;
import com.mohbajal.mongoexplore.dto.SearchCriteria;
import org.bson.Document;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mb3697 on 6/11/2018.
 * mongo-explore
 */
@Service
public class DBService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final Gson gson = new Gson();
    @Autowired MongoTemplate mongoTemplate;

    public void insertGenericCollection(String collectionName, Object collectionToUpsert) {

        Document doc = Document.parse(gson.toJson(collectionToUpsert));
        mongoTemplate.save(doc, collectionName);

    }

    public GenericSearchResults findInGenericCollection(String collectionName, SearchCriteria sc) {
        GenericSearchResults sr = new GenericSearchResults();
        List<Document> results = new ArrayList<>();
        // Uses the Jongo library to parse query string  @Link: http://jongo.org
        Jongo jongo = new Jongo(mongoTemplate.getDb());

        MongoCollection entries = jongo.getCollection(collectionName);
        if(sc.getProjection()==null)
            sc.setProjection("{_class: 0}");
        MongoCursor<Document> cursor = entries.find(sc.getQuery())
                .sort(sc.getSort()).projection(sc.getProjection()).limit(sc.getLimit()).as(Document.class);
        while(cursor.hasNext()) {
            results.add( cursor.next() );
        }
        System.out.println(results);
        System.out.println(gson.toJson(results));

        sr.setResults(results);
        sr.setNumFound(results.size());
        logger.info("Fetch request received for " + sc + ", "+ results.size() +  " records returned.");
        return sr;
    }
}
