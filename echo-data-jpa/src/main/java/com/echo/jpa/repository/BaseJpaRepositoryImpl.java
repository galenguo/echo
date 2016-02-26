package com.echo.jpa.repository;

import com.echo.data.domain.Id;
import com.echo.data.sql.query.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>echo框架的jpa扩张</p>
 * Created by Galen on 2015/11/11.
 */
@NoRepositoryBean
public abstract class BaseJpaRepositoryImpl<E extends Id<ID>, ID extends Serializable> extends SimpleJpaRepository<E, ID>
        implements BaseJpaRepository<E, ID> {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    protected EntityManager entityManager;

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

    // TODO: 2016/2/19 add query logic
    @Override
    public Page<E> queryPage(Query query) {
        return null;
    }

    @Override
    public Page<E> queryPage(String queryStr, String countStr, List<Object> params, Pageable pageable, Map<String, String> hints) {
        return null;
    }

    @Override
    public List<E> queryList(Query query) {
        return null;
    }

    @Override
    public List<E> queryList(String queryStr, List<Object> params, Pageable pageable, Map<String, String> hints) {
        return null;
    }

    @Override
    public Long count(Query query) {
        return null;
    }

    @Override
    public Long count(String queryStr, List<Object> params, Pageable pageable, Map<String, String> hints) {
        return null;
    }
}
