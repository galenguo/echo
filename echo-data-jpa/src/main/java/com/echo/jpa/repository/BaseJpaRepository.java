package com.echo.jpa.repository;

import com.echo.data.domain.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * <p>echo框架的jpa DAO扩张接口</p>
 * Created by Galen on 2015/11/9.
 */
public interface BaseJpaRepository<E extends Id<ID>, ID extends Serializable> extends JpaRepository<E, ID> {

}
