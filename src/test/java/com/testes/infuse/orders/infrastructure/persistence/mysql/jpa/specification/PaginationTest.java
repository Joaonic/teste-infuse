package com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.specification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PaginationTest {

    private Pagination pagination;


    @Test
    void buildPageableWithoutSort() {
        pagination = new Pagination(0, 10);
        Pageable expected = PageRequest.of(0, 10);

        Pageable result = pagination.build();

        assertEquals(expected, result);
    }

    @Test
    void buildPageableWithSimpleSort() {
        pagination = new Pagination(1, 10, "name", Sort.Direction.DESC);
        Pageable expected = PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "name"));

        Pageable result = pagination.build();

        assertEquals(expected, result);
    }

    @Test
    void buildPageableWithMultipleSort() {
        Map<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("name", Sort.Direction.ASC);
        sortMap.put("price", Sort.Direction.DESC);
        pagination = new Pagination(2, 15, sortMap, Sort.Direction.ASC);

        Pageable expected = PageRequest.of(2, 15, Sort.by(
                new Sort.Order(Sort.Direction.DESC, "price"),
                new Sort.Order(Sort.Direction.ASC, "name")
        ));

        Pageable result = pagination.build();

        assertEquals(expected, result);
    }
}
