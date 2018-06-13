package com.mohbajal.mongoexplore.controller

import com.google.gson.Gson
import com.mohbajal.mongoexplore.dto.GenericSearchResults
import com.mohbajal.mongoexplore.dto.SearchCriteria
import com.mohbajal.mongoexplore.service.DBService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Created by mb3697 on 5/24/2018.
 * ssot-isaacmongodbapi
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/mongo/v1")
class GenericAPIController {

    @Autowired
    lateinit internal var dbService: DBService
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/hello")
    internal fun hello(): String {
        return "Hello World"
    }

    @GetMapping("/collections")
    internal fun getCollections(): Set<String> {
        return dbService.listAllCollectionNames()
    }

    @PostMapping("/upsert_entry")
    internal fun add(@RequestHeader(value = "Collection-Name", defaultValue = "defaultCollection") collectionName: String,
                     @RequestBody collectionToUpsert: Any): ResponseEntity<*>? {
        dbService.insertGenericCollection(collectionName, collectionToUpsert)
        return ResponseEntity("OK", HttpStatus.OK)
    }

    @PostMapping("/find_entry")
    internal fun find(@RequestHeader(value = "Collection-Name", defaultValue = "defaultCollection") collectionName: String,
                      @RequestBody sc: SearchCriteria): ResponseEntity<*> {
        if (sc.query == null) {
            return ResponseEntity(GenericSearchResults(), HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity(dbService.findInGenericCollection(collectionName, sc), HttpStatus.OK)
    }

    companion object {
        private val gson = Gson()
    }

}