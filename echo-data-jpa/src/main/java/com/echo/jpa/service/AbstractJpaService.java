package com.echo.jpa.service;

import com.echo.data.domain.Id;
import com.echo.data.sql.query.Query;
import com.echo.jpa.repository.BaseJpaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Galen
 * @since 2016/2/19
 */
public abstract class AbstractJpaService<R extends BaseJpaRepository<E, ID>, E extends Id<ID>, ID extends Serializable>
        implements JpaService<R, E, ID> {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    protected R repository;

    @Override
    public void remove(ID id) {
        repository.delete(id);
    }

    public void remove(E entity) {
        repository.delete(entity);
    }

    @Override
    public E findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public List<E> findAll(Query query) {
        // TODO: 2016/2/19 add Query method
        return null;
    }

    @Override
    public Page<E> findPage(Query query) {
        // TODO: 2016/2/19 add Query method
        return null;
    }

    @Override
    public void save(E entity) {
        // TODO: 2016/2/19 why have to invoke deatch method?
        //this.repository.getEntityManager().detach(entity);
        repository.save(entity);
    }

    @Override
    public void saveAll(Collection<E> entities) {
        repository.save((Iterable<E>) entities.iterator());
    }
}
