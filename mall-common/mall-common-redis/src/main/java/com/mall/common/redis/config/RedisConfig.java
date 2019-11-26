package com.mall.common.redis.config;

import com.mall.common.redis.properties.RedisProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * program: spring-cloud-mall->RedisConfig
 * description:
 * author: gerry
 * created: 2019-11-26 20:43
 **/
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    @Autowired
    private RedisProperties redisProperties;

    private final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getSimpleName());
                sb.append(":");
                sb.append(method.getName());
                if (ArrayUtils.isNotEmpty(params)) {
                    List p = Arrays.asList(params);
                    String[] args = parameterNameDiscoverer.getParameterNames(method);
                    sb.append(":");
                    List<String> paramsStr = new ArrayList<>();
                    for (int i = 0; i < params.length; i++) {
                        if (params[i] == null || args[i] == null) continue;
                        //如果对象作为参数请注意重写toString方法（如继承MBaseModel的参数）
                        paramsStr.add(args[i] + ":" + params[i].toString());
                    }
                    sb.append(String.join(":", paramsStr));
                }
                System.out.println("redis key=========" + sb.toString());
                return sb.toString();
            }
        };
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        if (1 == 1) {//集群模式连接节点失败需要查询相关资料
            RedisStandaloneConfiguration redis = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
            redis.setPassword(RedisPassword.of(redisProperties.getPassword()));
            return new LettuceConnectionFactory(redis);
        }
        Map<String, Object> source = new HashMap<String, Object>();
        source.put("spring.redis.cluster.nodes", redisProperties.getNodes());
        source.put("spring.redis.cluster.timeout", redisProperties.getTimeOut());
        source.put("spring.redis.cluster.max-redirects", redisProperties.getMaxRedirects());
        RedisClusterConfiguration redisClusterConfiguration;
        redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        return new LettuceConnectionFactory(redisClusterConfiguration);
    }


    @Primary
    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
        //ClassLoader loader = this.getClass().getClassLoader();
        //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
        //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
        //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        defaultCacheConfig.usePrefix();
        defaultCacheConfig.prefixKeysWith("ts");
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
    }

    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory, RedisSerializer fastJson2JsonRedisSerializer) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        //redis   开启事务
//        redisTemplate.setEnableTransactionSupport(true);
        //hash  使用jdk  的序列化
        redisTemplate.setHashValueSerializer(fastJson2JsonRedisSerializer/*new JdkSerializationRedisSerializer()*/);
        //StringRedisSerializer  key  序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //keySerializer  对key的默认序列化器。默认值是StringSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //  valueSerializer
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}