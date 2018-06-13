package com.mohbajal.mongoexplore.dto;

/**
 * Created by mb3697 on 5/24/2018.
 * ssot-isaacmongodbapi
 */
public class SearchCriteria {

    private String query;
    private String projection;
    private String sort;
    private int limit;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "query='" + query + '\'' +
                ", projection='" + projection + '\'' +
                ", sort='" + sort + '\'' +
                ", limit=" + limit +
                '}';
    }
}
