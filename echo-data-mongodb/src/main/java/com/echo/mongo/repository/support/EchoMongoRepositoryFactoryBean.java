package com.echo.mongo.repository.support;

import com.echo.data.domain.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

/**
 * <p>mongo repository 工厂bean，负责创建工厂。</p>
 * Created by Galen on 2015/11/15.
 */
public class EchoMongoRepositoryFactoryBean<R extends MongoRepository<E, ID>, E extends Id<ID>, ID extends Serializable>
        extends MongoRepositoryFactoryBean<R, E, ID> {

    @Override
    protected RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
        return new EchoMongoRepositoryFactory(operations);
    }
}
