package com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.specification;

import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter.IFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public abstract class SpecificationBuilder<T, F extends IFilter> {
    protected Root<T> root;
    protected CriteriaQuery<?> query;
    protected CriteriaBuilder cb;

    List<Predicate> predicates;
    List<Predicate> simplifiedPredicates;

    public Specification<T> build(F filter) {

        return (r, q, c) -> {

            this.root = r;
            this.query = q;
            this.cb = c;
            this.predicates = new ArrayList<>();
            this.simplifiedPredicates = new ArrayList<>();

            loadPredicates(filter);
            loadSimplifiedPredicates(filter);

            return filter.getSimplified() == null || filter.getSimplified()
                    ? generateSimplifiedPredicates()
                    : generatePredicates();
        };

    }

    public Pageable buildPageable(F filter) {

        if (filter.getSortMap() != null && !filter.getSortMap().isEmpty()) {

            return new Pagination(filter.getPage(), filter.getPageSize(), filter.getSortMap(), filter.getSortDirection()).build();

        } else if (filter.getSort() != null) {

            return new Pagination(filter.getPage(), filter.getPageSize(), filter.getSort(), filter.getSortDirection()).build();
        }

        return new Pagination(filter.getPage(), filter.getPageSize()).build();

    }


    /**
     * This method should be used when an advanced search is required.
     * Advanced search means a multiple parameters search
     *
     * @param filter F to be used when searching for entity
     */
    protected abstract void loadPredicates(F filter);

    /**
     * This method should be used when a simple search is required.
     * Simple search means one input parameter search string for one or
     * more fields in the entity
     *
     * @param filter F to be used when searching for entity
     */
    protected abstract void loadSimplifiedPredicates(F filter);

    protected void addPredicates(Predicate predicate) {

        if (predicate == null) {

            return;
        }

        predicates = predicates != null ? predicates : new ArrayList<>();

        predicates.add(predicate);
    }

    protected void addSimplifiedPredicates(Predicate predicate) {

        if (predicate == null) {

            return;

        }

        simplifiedPredicates = simplifiedPredicates != null ? simplifiedPredicates : new ArrayList<>();

        simplifiedPredicates.add(predicate);
    }


    private Predicate generatePredicates() {

        Predicate[] predicatesArray = this.predicates.toArray(new Predicate[0]);

        return this.cb.and(predicatesArray);

    }

    private Predicate generateSimplifiedPredicates() {

        Predicate[] predicatesArray = this.simplifiedPredicates.toArray(new Predicate[0]);

        return this.cb.and(predicatesArray);

    }

    protected String getNormalizedParam(String param) {

        return param == null ? null : '%' + (Normalizer.normalize(param.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").replaceAll("[.&,\\-)(?']", "")) + '%';
    }

}