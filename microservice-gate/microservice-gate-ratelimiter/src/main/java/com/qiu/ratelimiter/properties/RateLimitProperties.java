package com.qiu.ratelimiter.properties;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-12
 **/
@Data
@Validated
@NoArgsConstructor
@ConfigurationProperties(RateLimitProperties.PREFIX)
public class RateLimitProperties {

    public static final String PREFIX = "zuul.ratelimit";

    private Policy defaultPolicy;
    @NotNull
    private Map<String, Policy> policies = Maps.newHashMap();
    private boolean behindProxy;
    private boolean enabled;
    @NotNull
    @Value("${spring.application.name:rate-limit-application}")
    private String keyPrefix;
    private String repository = String.valueOf(Repository.IN_MEMORY);

    public enum Repository {
        /**
         * Uses Redis as data storage
         */
        REDIS,

        /**
         * Uses Consul as data storage
         */
        CONSUL,

        /**
         * Uses SQL database as data storage
         */
        JPA,

        /**
         * Uses Bucket4j JCache as data storage
         */
        BUCKET4J_JCACHE,

        /**
         * Uses Bucket4j Hazelcast as data storage
         */
        BUCKET4J_HAZELCAST,

        /**
         * Uses Bucket4j Ignite as data storage
         */
        BUCKET4J_IGNITE,

        /**
         * Uses Bucket4j Infinispan as data storage
         */
        BUCKET4J_INFINISPAN,

        /**
         * Uses a ConcurrentHashMap as data storage
         */
        IN_MEMORY
    }

    public Optional<Policy> getPolicy(String key) {
        return Optional.ofNullable(policies.getOrDefault(key, defaultPolicy));
    }

    @Data
    @NoArgsConstructor
    public static class Policy {

        @NotNull
        private Long refreshInterval = TimeUnit.MINUTES.toSeconds(1L);
        @NotNull
        private Long limit;
        @NotNull
        private List<String> type = Lists.newArrayList();

        public enum Type {
            ORIGIN, USER, URL
        }
    }
}
