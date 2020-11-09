package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria {
    Long id;
    String name;

    public Criteria id(Long id) {
        this.id = id;
        return this;
    }

    public Criteria name(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
