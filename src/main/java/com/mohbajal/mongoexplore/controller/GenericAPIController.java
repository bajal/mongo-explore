package com.mohbajal.mongoexplore.controller;

import com.google.gson.Gson;
import com.mohbajal.mongoexplore.dto.GenericSearchResults;
import com.mohbajal.mongoexplore.dto.SearchCriteria;
import com.mohbajal.mongoexplore.service.DBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mb3697 on 5/24/2018.
 * ssot-isaacmongodbapi
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/mongo/v1")
public class GenericAPIController {

    @Autowired
    DBService dbService;

    private final static Gson gson = new Gson();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "hello_world", method = RequestMethod.GET)
    String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "upsert_entry", method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestHeader(value = "Collection-Name", defaultValue = "defaultCollection") String collectionName,
                          @RequestBody Object collectionToUpsert) {

        dbService.insertGenericCollection(collectionName, collectionToUpsert);
        return null;
    }

    @RequestMapping(value = "find_entry", method = RequestMethod.POST)
    ResponseEntity<?> find(@RequestHeader(value = "Collection-Name", defaultValue = "defaultCollection") String collectionName,
                           @RequestBody SearchCriteria sc) {
        if(sc.getQuery()==null) {
            return new ResponseEntity<>(new GenericSearchResults(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dbService.findInGenericCollection(collectionName, sc), HttpStatus.OK);
    }

}