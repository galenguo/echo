package com.echo.jpa.service;

import com.echo.data.domain.Id;
import com.echo.data.sql.query.Query;
import com.echo.jpa.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Galen
 * @since 2016/2/19
 */
public interface JpaService<R extends BaseJpaRepository<E, ID>, E extends Id<ID>, ID extends Serializable> {

    public void remove(ID id);

    public void remove(E entity);

    public E findOne(ID id);

    public List<E> findAll(Query query);

    public Page<E> findPage(Query query);

    public void save(E entity);

    public void saveAll(Collection<E> entities);
}
