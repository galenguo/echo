package com.echo.jpa.repository.support;

import com.echo.data.domain.Id;
import com.echo.jpa.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * <p>jpa的Repository工厂示例生成类</p>
 * Created by Galen on 2015/11/11.
 */
public class EchoJpaRepositoryFactoryBean<R extends BaseJpaRepository<E, ID>, E extends Id<ID>, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, E, ID> {
        @Override
        protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
                return new EchoJpaRepositoryFactory(entityManager);
        }
}
