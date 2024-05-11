package com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.specification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Pagination {
    private Integer pageNumber;
    private int pageSize;
    private String sort;
    private Map<String, Sort.Direction> sortMap;
    private Sort.Direction sortDirection = Sort.Direction.ASC;

    public Pagination(Integer pageNumber, Integer pageSize) {

        this.pageNumber = pageNumber;
        this.pageSize = pageSize == null ? 15 : pageSize;

    }

    public Pagination(Integer pageNumber, Integer pageSize, String sort, Sort.Direction sortDirection) {

        this.pageNumber = pageNumber;
        this.pageSize = pageSize == null ? 15 : pageSize;
        this.sort = sort;
        this.sortDirection = sortDirection;

    }

    public Pagination(Integer pageNumber, Integer pageSize, Map<String, Sort.Direction> sortMap, Sort.Direction sortDirection) {

        this.pageNumber = pageNumber;
        this.pageSize = pageSize == null ? 15 : pageSize;
        this.sortMap = sortMap;
        this.sortDirection = sortDirection;

    }

    public Pageable build() {

        if (pageNumber == null) {

            return PageRequest.of(0, pageSize, Sort.unsorted());

        }

        if (sortMap != null && !sortMap.isEmpty()) {
            List<Sort.Order> orders = new ArrayList<>();
            for (Map.Entry<String, Sort.Direction> entry : sortMap.entrySet()) {
                orders.add(new Sort.Order(entry.getValue(), entry.getKey()));

            }
            return PageRequest.of(pageNumber, pageSize, Sort.by(orders));

        } else if (sort != null && !sort.isEmpty()) {

            return PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sort));

        } else {

            return PageRequest.of(pageNumber, pageSize);

        }

    }
}
