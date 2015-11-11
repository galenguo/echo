package com.echo.jpa.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;

/**
 * Created by Galen on 2015/11/11.
 */
public class EchoJpaRepositoryFactory extends JpaRepositoryFactory {

    private EntityManager entityManager;

    public EchoJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
