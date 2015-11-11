package com.echo.jpa.repository;

import com.echo.data.domain.Id;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;

import java.io.Serializable;

/**
 * Created by Galen on 2015/11/11.
 */
public class EchoJpaRepositoryFactoryBean<R extends BaseJpaRepository<E, ID>, E extends Id<ID>, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, E, ID> {
}
