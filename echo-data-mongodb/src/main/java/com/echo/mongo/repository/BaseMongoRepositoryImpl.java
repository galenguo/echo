package com.echo.mongo.repository;

import com.echo.data.domain.Id;
import com.mongodb.util.JSON;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>mongo 扩展的repository 实现</p>
 * Created by Galen on 2015/11/15.
 */

public class BaseMongoRepositoryImpl<E extends Id<ID>, ID extends Serializable> extends SimpleMongoRepository<E, ID> implements BaseMongoRepository<E, ID> {

    private static final Pattern PLACEHOLDER = Pattern.compile("\\?(\\d+)");

    private final MongoOperations mongoOperations;

    private final MongoEntityInformation<E, ID> mongoEntityInformation;

    public BaseMongoRepositoryImpl(MongoEntityInformation<E, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.mongoEntityInformation = metadata;
    }

    @Override
    public MongoOperations getMongoOperations() {
        return this.mongoOperations;
    }

    @Override
    public List<E> queryList(Criteria criteria, Pageable pageable) {
        Query query = new Query();
        if(criteria != null) {
            query.addCriteria(criteria);
        }
        return queryList(query, pageable);
    }

    @Override
    public Page<E> query(Criteria criteria, Pageable pageable) {
        List<E> list = queryList(criteria, pageable);
        return new PageImpl<E>(list, pageable, getCount(Query.query(criteria)));
    }

    @Override
    public List<E> queryList(Query query, Pageable pageable) {
        if(pageable != null) {
            query.with(pageable);
        }
        return this.mongoOperations.find(query, this.mongoEntityInformation.getJavaType(), this.mongoEntityInformation.getCollectionName());
    }

    @Override
    public Page<E> query(Query query, Pageable pageable) {
        List<E> list = queryList(query, pageable);
        return new PageImpl<E>(list, pageable, getCount(query));
    }

    @Override
    public List<E> queryList(String queryString, List<Object> parameters, Pageable pageable) {
        Query query = createQuery(queryString, parameters);
        return queryList(query, pageable);
    }

    @Override
    public Page<E> query(String queryString, List<Object> parameters, Pageable pageable) {
        Query query = createQuery(queryString, parameters);
        List<E> list = queryList(query, pageable);
        return new PageImpl<E>(list, pageable, getCount(query));
    }

    @Override
    public void insertBatch(Collection<E> entities) {
        this.mongoOperations.insert(entities, this.mongoEntityInformation.getJavaType());
    }

    @Override
    public void delete(Query query) {
        this.mongoOperations.remove(query, this.mongoEntityInformation.getJavaType());
    }

    private Query createQuery(String queryStr, List<Object> parameters) {
        String queryString = replacePlaceholders(queryStr, parameters);
        Query query = new BasicQuery(queryString);
        return query;
    }

    private String replacePlaceholders(String input, List<Object> parameters) {
        Matcher matcher = PLACEHOLDER.matcher(input);
        String result = input;

        while (matcher.find()) {
            String group = matcher.group();
            int index = Integer.parseInt(matcher.group(1));
            result = result.replace(group, JSON.serialize(this.mongoOperations.getConverter().convertToMongoType(parameters.get(index), null)));
        }
        return result;
    }

    private long getCount(Query query) {
        return this.mongoOperations.count(query, this.mongoEntityInformation.getJavaType());
    }
}
