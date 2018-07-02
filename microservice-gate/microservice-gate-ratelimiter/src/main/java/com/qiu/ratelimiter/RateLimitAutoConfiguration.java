package com.qiu.ratelimiter;

import com.qiu.ratelimiter.adapter.RateLimiter;
import com.qiu.ratelimiter.adapter.jpa.IRateLimiterRepository;
import com.qiu.ratelimiter.adapter.jpa.JpaRateLimiter;
import com.qiu.ratelimiter.adapter.memery.InMemoryRateLimiter;
import com.qiu.ratelimiter.adapter.redis.RedisRateLimiter;
import com.qiu.ratelimiter.filter.RateLimitFilter;
import com.qiu.ratelimiter.properties.RateLimitProperties;
import com.qiu.ratelimiter.user.IUserPrincipal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import static com.qiu.ratelimiter.properties.RateLimitProperties.PREFIX;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-12
 **/
@Configuration
@EnableConfigurationProperties(RateLimitProperties.class)
@ConditionalOnProperty(prefix = PREFIX, name = "enabled", havingValue = "true")
public class RateLimitAutoConfiguration {

    @Bean
    public RateLimitFilter rateLimiterFilter(final RateLimiter rateLimiter,
                                             final RateLimitProperties rateLimitProperties,
                                             final RouteLocator routeLocator, final IUserPrincipal userPrincipal) {
        return new RateLimitFilter(rateLimiter, rateLimitProperties, routeLocator,userPrincipal);
    }

    @ConditionalOnClass(RedisTemplate.class)
    @ConditionalOnMissingBean(RateLimiter.class)
    @ConditionalOnProperty(prefix = PREFIX, name = "repository", havingValue = "REDIS")
    public static class RedisConfiguration {

        @Bean("rateLimiterRedisTemplate")
        public StringRedisTemplate redisTemplate(final RedisConnectionFactory connectionFactory) {
            return new StringRedisTemplate(connectionFactory);
        }

        @Bean
        public RateLimiter redisRateLimiter(@Qualifier("rateLimiterRedisTemplate") final RedisTemplate redisTemplate) {
            return new RedisRateLimiter(redisTemplate);
        }
    }

    @EnableJpaRepositories(basePackages = {"com.qiu.ratelimiter.adapter.jpa"})//启用JPA资源库并指定资源库接口位置
    @EntityScan(basePackages = {"com.qiu.ratelimiter"})//指定实体的位置
    @ConditionalOnMissingBean(RateLimiter.class)
    @ConditionalOnProperty(prefix = PREFIX, name = "repository", havingValue = "JPA")
    public static class SpringDataConfiguration {

        @Bean
        public RateLimiter springDataRateLimiter(IRateLimiterRepository rateLimiterRepository) {
            return new JpaRateLimiter(rateLimiterRepository);
        }

    }

    @ConditionalOnMissingBean(RateLimiter.class)
    @ConditionalOnProperty(prefix = PREFIX, name = "repository", havingValue = "IN_MEMORY", matchIfMissing = true)
    public static class InMemoryConfiguration {

        @Bean
        public RateLimiter inMemoryRateLimiter() {
            return new InMemoryRateLimiter();
        }
    }

}
