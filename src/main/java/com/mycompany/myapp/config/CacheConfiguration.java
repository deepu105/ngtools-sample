package com.mycompany.myapp.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.mycompany.myapp.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.SocialUserConnection.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.BankAccount.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.BankAccount.class.getName() + ".operations", jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.Label.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.Label.class.getName() + ".operations", jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.Operation.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.Operation.class.getName() + ".labels", jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.FieldTestMapstructEntity.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.FieldTestEntity.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.FieldTestInfiniteScrollEntity.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.FieldTestPagerEntity.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.FieldTestPaginationEntity.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.FieldTestServiceClassEntity.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.FieldTestServiceImplEntity.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithDTO.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithServiceClass.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithServiceImpl.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithPagination.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithServiceClassAndPagination.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithServiceImplAndPagination.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithServiceClassAndDTO.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithServiceImplAndDTO.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithPaginationAndDTO.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithServiceClassPaginationAndDTO.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.EntityWithServiceImplPaginationAndDTO.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.Division.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.Division.class.getName() + ".divisionsPlaces", jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.Division.class.getName() + ".preferredPlaces", jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.Place.class.getName(), jcacheConfiguration);
            cm.createCache(com.mycompany.myapp.domain.Place.class.getName() + ".preferredDivisions", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
