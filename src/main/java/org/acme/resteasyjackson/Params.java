package org.acme.resteasyjackson;
import javax.ws.rs.QueryParam;

public class Params {

    @QueryParam("orderBy")
    private String orderBy;
    @QueryParam("limit")
    private int limit;
    @QueryParam("offset")
    private int offset;

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }



}
