package com.echo.jpa.repository;

import com.echo.data.domain.Id;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by Galen on 2015/11/15.
 */
public class BaseQueryDslJpaRepository<T extends Id<ID>, ID extends Serializable> extends QueryDslJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseQueryDslJpaRepository(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseQueryDslJpaRepository(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager, EntityPathResolver resolver) {
        super(entityInformation, entityManager, resolver);
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
