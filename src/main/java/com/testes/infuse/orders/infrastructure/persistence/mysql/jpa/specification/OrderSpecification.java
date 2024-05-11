package com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.specification;

import com.testes.infuse.orders.core.domain.entity.Order;
import com.testes.infuse.orders.infrastructure.persistence.mysql.jpa.filter.Filter;
import jakarta.persistence.criteria.Predicate;

public class OrderSpecification extends SpecificationBuilder<Order, Filter> {

    @Override
    public void loadPredicates(Filter filter) {
        withControlNumber(filter);
        withDates(filter);
        withName(filter);
        withUnitPrice(filter);
        withQuantity(filter);
        withCustomerCode(filter);
        withTotalPrice(filter);
    }


    @Override
    protected void loadSimplifiedPredicates(Filter filter) {
        if (Boolean.TRUE.equals(filter.getSimplified())) {
            withSimplified(filter);
        }
    }

    private void withSimplified(Filter filter) {
        String simplifiedField = filter.getSimplifiedField();

        if (simplifiedField != null && !simplifiedField.isBlank()) {

            String parameter = getNormalizedParam(simplifiedField.toLowerCase());

            Predicate namePredicate = cb.like(root.get("name"), parameter);

            addSimplifiedPredicates(namePredicate);
        }
    }


    /**
     * Adds a predicate for filtering by control number if provided in the filter.
     */
    private void withControlNumber(Filter filter) {
        if (filter.getControlNumber() != null && !filter.getControlNumber().isEmpty()) {
            Predicate controlNumberPredicate = cb.equal(root.get("controlNumber"), filter.getControlNumber());
            addPredicates(controlNumberPredicate);
        }
    }

    /**
     * Adds predicates for filtering between start and end registration dates if provided.
     */
    private void withDates(Filter filter) {
        if (filter.getStartRegistrationDate() == null && filter.getEndRegistrationDate() == null) {
            return;
        }

        if (filter.getStartRegistrationDate() == null) {
            Predicate datePredicate = cb.lessThanOrEqualTo(root.get("registrationDate"),
                    filter.getEndRegistrationDate());
            addPredicates(datePredicate);
            return;
        }

        if (filter.getEndRegistrationDate() == null) {
            Predicate datePredicate = cb.greaterThanOrEqualTo(root.get("registrationDate"),
                    filter.getStartRegistrationDate());
            addPredicates(datePredicate);
            return;
        }

        Predicate datePredicate = cb.between(root.get("registrationDate"),
                filter.getStartRegistrationDate(),
                filter.getEndRegistrationDate());
        addPredicates(datePredicate);

    }

    /**
     * Adds a predicate for filtering by name if provided in the filter.
     */
    private void withName(Filter filter) {
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            Predicate namePredicate = cb.equal(cb.lower(root.get("name")), filter.getName());
            addPredicates(namePredicate);
        }
    }

    /**
     * Adds a predicate for filtering by unit price if provided.
     */
    private void withUnitPrice(Filter filter) {
        if (filter.getUnitPrice() != null) {
            Predicate unitPricePredicate = cb.equal(root.get("unitPrice"), filter.getUnitPrice());
            addPredicates(unitPricePredicate);
        }
    }

    /**
     * Adds a predicate for filtering by quantity if provided.
     */
    private void withQuantity(Filter filter) {
        if (filter.getQuantity() != null) {
            Predicate quantityPredicate = cb.equal(root.get("quantity"), filter.getQuantity());
            addPredicates(quantityPredicate);
        }
    }

    /**
     * Adds a predicate for filtering by customer code if provided.
     */
    private void withCustomerCode(Filter filter) {
        if (filter.getCustomerCode() != null && !filter.getCustomerCode().isEmpty()) {
            Predicate customerCodePredicate = cb.equal(root.get("customerCode"), filter.getCustomerCode());
            addPredicates(customerCodePredicate);
        }
    }

    /**
     * Adds a predicate for filtering by total price if provided.
     */
    private void withTotalPrice(Filter filter) {
        if (filter.getTotalPrice() != null) {
            Predicate totalPricePredicate = cb.equal(root.get("totalPrice"), filter.getTotalPrice());
            addPredicates(totalPricePredicate);
        }
    }
}
