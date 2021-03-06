package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */
public abstract class AbstractBaseEntity implements BaseEntity {
    private static Long staticId = 0L;
    private final Long id;
    private String name;

    public AbstractBaseEntity(String name) {
        this.id = staticId++;
        this.name = name;
    }

    @Override
    public Long getId() {
        // todo
        return id;
    }

    @Override
    public String getName() {
        // todo
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
