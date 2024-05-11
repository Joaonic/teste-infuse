package com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter;

import org.springframework.data.domain.Sort;

import java.util.Map;

public interface IFilter {
    Boolean getSimplified();

    void setSimplified(Boolean value);

    Integer getPage();

    void setPage(Integer value);

    Integer getPageSize();

    void setPageSize(Integer value);

    String getSort();

    void setSort(String value);

    Map<String, Sort.Direction> getSortMap();

    void setSortMap(Map<String, Sort.Direction> values);

    Sort.Direction getSortDirection();

    void setSortDirection(Sort.Direction value);
}
