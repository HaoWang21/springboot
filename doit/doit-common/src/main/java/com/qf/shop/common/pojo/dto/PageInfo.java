package com.qf.shop.common.pojo.dto;

import java.io.Serializable;

public class PageInfo implements Serializable {
    private int page;
    private int limit;

    public int getOffset() {
        return (page-1)*limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
