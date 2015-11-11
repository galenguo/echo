package com.echo.jpa.repository;

import com.echo.data.domain.Id;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * <p>echo框架的jpa扩张</p>
 * Created by Galen on 2015/11/11.
 */
@NoRepositoryBean
public class BaseJpaRepositoryImpl<E extends Id<ID>, ID extends Serializable> extends SimpleJpaRepository<E, ID> implements BaseJpaRepository<E, ID> {

    private EntityManager entityManager;

    public BaseJpaRepositoryImpl(JpaEntityInformation<E, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseJpaRepositoryImpl(Class<E> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
