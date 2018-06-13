package com.mohbajal.mongoexplore.service

import com.google.gson.Gson
import com.mohbajal.mongoexplore.dto.GenericSearchResults
import com.mohbajal.mongoexplore.dto.SearchCriteria
import org.bson.Document
import org.jongo.Jongo
import org.jongo.MongoCollection
import org.jongo.MongoCursor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

import java.util.ArrayList

/**
 * Created by mb3697 on 6/11/2018.
 * mongo-explore
 */
@Service
class DBService {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    fun listAllCollectionNames():Set<String> = mongoTemplate.collectionNames


    fun insertGenericCollection(collectionName: String, collectionToUpsert: Any) {
        val doc = Document.parse(gson.toJson(collectionToUpsert))
        mongoTemplate.save(doc, collectionName)
    }

    fun findInGenericCollection(collectionName: String, sc: SearchCriteria): GenericSearchResults {
        val sr = GenericSearchResults()
        val results = ArrayList<Document>()
        // Uses the Jongo library to parse query string  @Link: http://jongo.org
        val jongo = Jongo(mongoTemplate.db)

        val entries = jongo.getCollection(collectionName)
        if (sc.projection == null)
            sc.projection = "{_class: 0}"
        val cursor = entries.find(sc.query).sort(sc.sort).projection(sc.projection).limit(sc.limit).skip(sc.skip).`as`(Document::class.java)
        while (cursor.hasNext()) {
            results.add(cursor.next())
        }

        sr.results = results
        sr.numFound = results.size
        logger.info("Fetch request received for " + sc + ", " + results.size + " records returned.")
        return sr
    }

    companion object {
        private val gson = Gson()
    }
}
