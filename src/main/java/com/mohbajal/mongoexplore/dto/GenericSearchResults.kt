package com.mohbajal.mongoexplore.dto

import org.bson.Document
import java.util.*

/**
 * Created by mb3697 on 5/24/2018.
 * ssot-isaacmongodbapi
 */
class GenericSearchResults {
    var numFound = 0
    var results: List<Document> = ArrayList()
}
