package com.echo.jpa.repository;

import com.echo.data.domain.Id;
import com.echo.data.sql.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>echo框架的jpa DAO扩张接口</p>
 * Created by Galen on 2015/11/9.
 */
public interface BaseJpaRepository<E extends Id<ID>, ID extends Serializable> extends JpaRepository<E, ID> {

    public EntityManager getEntityManager();

    /**
     * query page by query conditioins
     * @param query
     * @return
     */
    public Page<E> queryPage(Query query);

    /**
     * query page by jpql
     * @param queryStr
     * @param countStr
     * @param params
     * @param pageable
     * @param hints
     * @return
     */
    public Page<E> queryPage(String queryStr, String countStr, List<Object> params, Pageable pageable,
                             Map<String, String> hints);

    /**
     *  query list by query conditions
     * @param query
     * @return
     */
    public List<E> queryList(Query query);

    /**
     * query list by jpql
     * @param queryStr
     * @param params
     * @param pageable
     * @param hints
     * @return
     */
    public List<E> queryList(String queryStr, List<Object> params, Pageable pageable,
                             Map<String, String> hints);

    /**
     * count by query conditions
     * @param query
     * @return
     */
    public Long count(Query query);

    /**
     * count by jpql
     * @param queryStr
     * @param params
     * @param pageable
     * @param hints
     * @return
     */
    public Long count(String queryStr, List<Object> params, Pageable pageable,
                         Map<String, String> hints);

}
