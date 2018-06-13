package com.mohbajal.mongoexplore.dto;

import org.bson.Document;

import java.util.List;

/**
 * Created by mb3697 on 5/24/2018.
 * ssot-isaacmongodbapi
 */
public class GenericSearchResults {
    private int numFound = 0;
    List<Document> results;

    public int getNumFound() {
        return numFound;
    }

    public void setNumFound(int numFound) {
        this.numFound = numFound;
    }

    public List<Document> getResults() {
        return results;
    }

    public void setResults(List<Document> results) {
        this.results = results;
    }
}
