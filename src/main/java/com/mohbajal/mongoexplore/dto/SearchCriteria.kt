package com.mohbajal.mongoexplore.dto

/**
 * Created by mb3697 on 5/24/2018.
 * ssot-isaacmongodbapi
 */
class SearchCriteria {

    var query: String? = null
    var projection: String? = null
    var sort: String? = null
    var limit: Int = 0
    var skip: Int = 0

    override fun toString(): String {
        return "SearchCriteria{" +
                "query='" + query + '\'' +
                ", projection='" + projection + '\'' +
                ", sort='" + sort + '\'' +
                ", limit=" + limit +
                ", skip=" + skip +
                '}'
    }
}
