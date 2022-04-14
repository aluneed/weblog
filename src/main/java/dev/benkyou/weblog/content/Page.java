package dev.benkyou.weblog.content;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public class Page<T> {

    public Page() {}

    public Page(PanacheQuery<T> panacheQuery) {
        this.list = panacheQuery.list();
        this.pageCount = panacheQuery.pageCount();
        this.count = panacheQuery.count();
    }
    public Page(List<T> list, Integer pageCount, Long count) {
        this.list = list;
        this.pageCount = pageCount;
        this.count = count;
    }

    private List<T> list;

    private Long count;

    private Integer pageCount;

    public List<T> getList() {
        return list;
    }

    public Page<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public Long getCount() {
        return count;
    }

    public Page<T> setCount(Long count) {
        this.count = count;
        return this;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Page<T> setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }
}
