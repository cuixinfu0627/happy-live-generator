package com.happy.live.common.frame.mybatis;

import java.io.Serializable;
import java.util.List;

/**
 * 类名:Pageable <tb>
 * 描述:请输入类描述  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/11/21 12:26 <tb>
 */
public class Pageable<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_CURRENT_PAGE = 1;
    public static final int DEFAULT_PAGE_ROW = 20;
    private int currentPage;
    private int totalPage;
    private int totalRow;
    private int pageSize;
    private String orderByClause;
    private boolean needCount;
    private List<T> rows;

    public String getOrderByClause() {
        return this.orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Pageable() {
        this(1, 20);
    }

    public Pageable(int currentPage, int pageRow) {
        this.needCount = true;
        this.needCount = true;
        this.currentPage = currentPage;
        this.pageSize = pageRow;
    }

    public int getCurrentPage() {
        return this.currentPage < 1 ? 1 : this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getFromRow() {
        return (this.getCurrentPage() - 1) * this.getPageSize();
    }

    public int getPageSize() {
        return this.pageSize < 1 ? 20 : this.pageSize;
    }

    public void setPageSize(int pageRow) {
        this.pageSize = pageRow;
    }

    public int getTotalRow() {
        return this.totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
        this.totalPage = totalRow % this.pageSize != 0 ? totalRow / this.pageSize + 1 : totalRow / this.pageSize;
    }

    public boolean isNeedCount() {
        return this.needCount;
    }

    public void setNeedCount(boolean needCount) {
        this.needCount = needCount;
    }

    public String toJson() {
        String json = "{";
        json = json + "pageSize:\"" + this.getPageSize() + "\",";
        json = json + "currentPage:\"" + this.getCurrentPage() + "\",";
        json = json + "totalRow:\"" + this.getTotalRow() + "\",";
        json = json + "totalPage:\"" + this.getTotalPage() + "\"";
        json = json + "}";
        return json;
    }
}
