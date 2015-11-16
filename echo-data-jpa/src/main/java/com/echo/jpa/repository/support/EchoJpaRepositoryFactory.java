package com.echo.jpa.repository.support;

import com.echo.jpa.repository.BaseJpaManagerAware;
import com.echo.jpa.repository.BaseJpaRepository;
import com.echo.jpa.repository.BaseQueryDslJpaRepository;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.QueryDslUtils;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>自定义扩展jpa工厂类</p>
 * Created by Galen on 2015/11/11.
 */
public class EchoJpaRepositoryFactory extends JpaRepositoryFactory {

    private final EntityManager entityManager;
    private final List<RepositoryProxyPostProcessor> postProcessors = new ArrayList();

    public EchoJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * 增加代理处理器，主要是对Transactional的处理
     */
    @Override
    public void addRepositoryProxyPostProcessor(RepositoryProxyPostProcessor processor) {
        super.addRepositoryProxyPostProcessor(processor);
        this.postProcessors.add(processor);
    }

    /**
     * 生成repository实例
     */
    @Override
    public <T> T getRepository(Class<T> repositoryInterface, Object customImplementation) {
        RepositoryMetadata metadata = this.getRepositoryMetadata(repositoryInterface);
        Class customImplementationClass = null == customImplementation ? null : customImplementation.getClass();
        RepositoryInformation information = this.getRepositoryInformation(metadata, customImplementationClass);

        //判断是否有实现扩展接口
        if (customImplementation instanceof BaseJpaManagerAware) {
            ((BaseJpaManagerAware) customImplementation).setEntityManager(entityManager);

            //创建代理工厂
            ProxyFactory factory = new ProxyFactory();
            factory.setTarget(customImplementation);

            //添加自定义接口给代理，排除系统提供的接口（super的同名方法有处理，此处不需要处理）
            for (Class<?> intf : repositoryInterface.getInterfaces()) {
                if (!Repository.class.isAssignableFrom(intf) && !JpaSpecificationExecutor.class.isAssignableFrom(intf)) {
                    factory.addInterface(intf);
                }
            }

            //代理处理器传给代理工厂
            for (RepositoryProxyPostProcessor processor : postProcessors) {
                processor.postProcess(factory, information);
            }

            //把代理代理对象作为参数，调用super的同名方法。
            return super.getRepository(repositoryInterface, factory.getProxy());
        }
        return super.getRepository(repositoryInterface, customImplementation);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return isQueryDslExecutor(metadata.getRepositoryInterface()) ? BaseQueryDslJpaRepository.class : BaseJpaRepository.class;
    }

    private boolean isQueryDslExecutor(Class<?> repositoryInterface) {
        return QueryDslUtils.QUERY_DSL_PRESENT && QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
    }
}
