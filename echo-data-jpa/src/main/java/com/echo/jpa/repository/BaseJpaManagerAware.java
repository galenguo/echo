package com.echo.jpa.repository;

import javax.persistence.EntityManager;

/**
 * Created by Galen on 2015/11/15.
 */
public interface BaseJpaManagerAware {

    public void setEntityManager(EntityManager entityManager);
}
