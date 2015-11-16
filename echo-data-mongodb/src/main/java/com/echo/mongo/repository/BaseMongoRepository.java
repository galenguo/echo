package com.echo.mongo.repository;

import com.echo.data.domain.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>mongo 扩展的repository 接口定义</p>
 * Created by Galen on 2015/11/15.
 */
@NoRepositoryBean
public interface BaseMongoRepository<E extends Id<ID>, ID extends Serializable> extends MongoRepository<E, ID> {

    public MongoOperations getMongoOperations();

    public List<E> queryList(Criteria criteria, Pageable pageable);

    public Page<E> query(Criteria criteria, Pageable pageable);

    public List<E> queryList(Query query, Pageable pageable);

    public Page<E> query(Query query, Pageable pageable);

    public List<E> queryList(String queryString, List<Object> parameters, Pageable pageable);

    public Page<E> query(String queryString, List<Object> parameters, Pageable pageable);

    public void insertBatch(Collection<E> entities);

    public void delete(Query query);
}
