package com.mall.utils;

import com.google.gson.Gson;
import com.mall.enums.CtimsModelEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wenguoli
 * @date 2019/11/26 16:35
 */
@Component
public class RedisUtil {
    protected static final Logger logger = LogManager.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 加锁过期时长，单位：秒
     */
    public final static long LOCK_EXPIRE = 60 * 60;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;
    private final static Gson GSON = new Gson();


    //=============================other============================

    public <T> T getMapField(CtimsModelEnum modelEnum, String key, String field) {
        return (T) redisTemplate.boundHashOps(formatKey(modelEnum, key)).get(field);
    }

    public Set<String> getAllKeys(CtimsModelEnum modelEnum, String regKey) {
        return redisTemplate.keys(formatKey(modelEnum, regKey));
    }


    public void addMap(CtimsModelEnum modelEnum, String key, Map<String, Object> map) {
        redisTemplate.boundHashOps(key).putAll(map);
    }


    public void addMap(CtimsModelEnum modelEnum, String key, String field, Object value) {
        redisTemplate.boundHashOps(key).put(field, value);
    }


    public void addMap(CtimsModelEnum modelEnum, String key, String field, Object value, long time) {
        redisTemplate.boundHashOps(key).put(field, value);
        redisTemplate.boundHashOps(key).expire(time, TimeUnit.SECONDS);
    }


    //=============================common============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public boolean expire(CtimsModelEnum modelEnum, String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(formatKey(modelEnum, key), time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(CtimsModelEnum modelEnum, String key) {
        return redisTemplate.getExpire(formatKey(modelEnum, key), TimeUnit.SECONDS);
    }


    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(CtimsModelEnum modelEnum, String key) {
        try {
            return redisTemplate.hasKey(formatKey(modelEnum, key));
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(CtimsModelEnum modelEnum, String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(formatKey(modelEnum, key[0]));
            } else {
                redisTemplate.delete(formatKey(modelEnum, key));
            }
        }
    }


    //============================String=============================

    /**
     * 普通缓存放入并设置时间
     *
     * @param key    键
     * @param value  值
     * @param expire 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public void set(CtimsModelEnum modelEnum, String key, Object value, long expire) {
        if (expire != NOT_EXPIRE) {
            valueOperations.set(formatKey(modelEnum, key), toJson(value), expire, TimeUnit.SECONDS);
        } else {
            valueOperations.set(formatKey(modelEnum, key), toJson(value));
        }
    }


    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public void set(CtimsModelEnum modelEnum, String key, Object value) {
        set(modelEnum, key, value, NOT_EXPIRE);
    }

    public <T> T get(CtimsModelEnum modelEnum, String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(formatKey(modelEnum, key));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(formatKey(modelEnum, key), expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    /**
     * 普通Model缓存获取
     *
     * @param key 键,clazz
     * @return T 值
     */
    public <T> T get(CtimsModelEnum modelEnum, String key, Class<T> clazz) {
        return get(modelEnum, key, clazz, NOT_EXPIRE);
    }

    /**
     * 普通string缓存获取
     *
     * @param key 键,expire 更新缓存失效时间
     * @return T 值
     */
    public String get(CtimsModelEnum modelEnum, String key, long expire) {
        String value = valueOperations.get(formatKey(modelEnum, key));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(formatKey(modelEnum, key), expire, TimeUnit.SECONDS);
        }
        return value;
    }

    /**
     * 普通缓存获取
     *
     * @param key 键,clazz
     * @return string 值
     */
    public String get(CtimsModelEnum modelEnum, String key) {
        return get(modelEnum, key, NOT_EXPIRE);
    }


    /**
     * 获取批量缓存
     * @param keys 多个键
     * @return list对象
     */
    public List<Object> get(CtimsModelEnum modelEnum, String... keys) {
        List<Object> list = new ArrayList<Object>();
        for (String key : keys) {
            list.add(get(modelEnum, key));
        }
        return list;
    }


    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     */
    public long incr(CtimsModelEnum modelEnum, String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(formatKey(modelEnum, key), delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     */
    public long decr(CtimsModelEnum modelEnum, String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(formatKey(modelEnum, key), -delta);
    }

    //================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(CtimsModelEnum modelEnum, String key, String item) {
        return redisTemplate.opsForHash().get(formatKey(modelEnum, key), item);
    }


    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public <T> T hget(CtimsModelEnum modelEnum, String key, String item, Class<T> clazz) {
        String value = (String) redisTemplate.opsForHash().get(formatKey(modelEnum, key), item);
        return value == null ? null : fromJson(value, clazz);

    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(CtimsModelEnum modelEnum, String key) {
        return redisTemplate.opsForHash().entries(formatKey(modelEnum, key));
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(CtimsModelEnum modelEnum, String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(formatKey(modelEnum, key), map);
            return true;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(CtimsModelEnum modelEnum, String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(formatKey(modelEnum, key), map);
            if (time > 0) {
                expire(modelEnum, key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(CtimsModelEnum modelEnum, String key, String item, Object value) {
        try {
            int cs = 1; // 向Redis中入数据的次数
            while (true) {
                redisTemplate.opsForHash().put(formatKey(modelEnum, key), item, value);
                if (cs > 3 || hasKey(modelEnum, key)) {
                    break;
                } else {
                    cs++;
                }
            }
            if (cs > 3) {
                throw new Exception("向Redis插入数据失败，请稍后再试");
            }
            return true;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * 并指定缓存失效时间
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(CtimsModelEnum modelEnum, String key, String item, Object value,
                        long time) {
        try {
            int cs = 1; // 向Redis中入数据的次数
            while (true) {
                redisTemplate.opsForHash().put(formatKey(modelEnum, key), item, value);
                // 如果缓存失效时间大于0，则指定缓存失效时间
                if (time > 0) {
                    expire(modelEnum, key, time);
                }
                if (cs > 3 || hasKey(modelEnum, key)) {
                    break;
                } else {
                    cs++;
                }
            }
            if (cs > 3) {
                throw new Exception("向Redis插入数据失败，请稍后再试");
            }
            return true;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(CtimsModelEnum modelEnum, String key, Object... item) {
        redisTemplate.opsForHash().delete(formatKey(modelEnum, key), item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(CtimsModelEnum modelEnum, String key, String item) {
        return redisTemplate.opsForHash().hasKey(formatKey(modelEnum, key), item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     */
    public double hincr(CtimsModelEnum modelEnum, String key, String item, double by) {
        return redisTemplate.opsForHash().increment(formatKey(modelEnum, key), item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     */
    public double hdecr(CtimsModelEnum modelEnum, String key, String item, double by) {
        return redisTemplate.opsForHash().increment(formatKey(modelEnum, key), item, -by);
    }

    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     */
    public Set<Object> sGet(CtimsModelEnum modelEnum, String key) {
        try {
            return redisTemplate.opsForSet().members(formatKey(modelEnum, key));
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(CtimsModelEnum modelEnum, String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(formatKey(modelEnum, key), value);
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(CtimsModelEnum modelEnum, String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(formatKey(modelEnum, key), values);
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(CtimsModelEnum modelEnum, String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(formatKey(modelEnum, key), values);
            if (time > 0) {
                expire(modelEnum, key, time);
            }
            return count;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    public long sGetSetSize(CtimsModelEnum modelEnum, String key) {
        try {
            return redisTemplate.opsForSet().size(formatKey(modelEnum, key));
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(CtimsModelEnum modelEnum, String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(formatKey(modelEnum, key), values);
            return count;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return 0;
        }
    }
    //===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     */
    public List<Object> lGet(CtimsModelEnum modelEnum, String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(formatKey(modelEnum, key), start, end);
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     */
    public long lGetListSize(CtimsModelEnum modelEnum, String key) {
        try {
            return redisTemplate.opsForList().size(formatKey(modelEnum, key));
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    public Object lGetIndex(CtimsModelEnum modelEnum, String key, long index) {
        try {
            return redisTemplate.opsForList().index(formatKey(modelEnum, key), index);
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public boolean lSet(CtimsModelEnum modelEnum, String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(formatKey(modelEnum, key), value);
            return true;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public boolean lSet(CtimsModelEnum modelEnum, String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(formatKey(modelEnum, key), value);
            if (time > 0) {
                expire(modelEnum, key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public boolean lSet(CtimsModelEnum modelEnum, String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(formatKey(modelEnum, key), value);
            return true;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);

            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public boolean lSet(CtimsModelEnum modelEnum, String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(formatKey(modelEnum, key), value);
            if (time > 0) {
                expire(modelEnum, key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public boolean lUpdateIndex(CtimsModelEnum modelEnum, String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(formatKey(modelEnum, key), index, value);
            return true;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(CtimsModelEnum modelEnum, String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList()
                    .remove(formatKey(modelEnum, key), count, value);
            return remove;
        } catch (Exception e) {
            logger.error("redis 操作失败，失败原因：", e);
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 加锁
     *
     * @param value 当前时间+超时时间
     */
    public boolean lock(String key, String value) {
        //SETNX命令, 可以设置返回true, 不可以返回false
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String currentValue = (String) redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue)
                && (Long.parseLong(currentValue) < System.currentTimeMillis())) {
            //GETSET命令, 获取上一个锁的时间
            String oldValue = (String) redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     */
    public void unLock(String key, String value) {
        try {
            String currentValue = (String) redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue)
                    && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            logger.error("【redis分布式锁】解锁异常, {}", e);
        }
    }


    /**
     * 批量向redis中插入:key  value
     * 如果键已存在则返回false,不更新,防止覆盖。使用pipeline批处理方式(不关注返回值)
     *
     * @param list           一个map代表一行记录,2个key:key & value。
     * @param ctimsModelEnum redis中key的值前缀。
     * @return
     */
    public boolean pipelinedString(final List<Map<String, Object>> list,
                                   final CtimsModelEnum ctimsModelEnum) {
        boolean result = (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                for (Map<String, Object> map : list) {
                    byte[] key = serializer
                            .serialize(formatKey(ctimsModelEnum, map.get("key").toString()));
                    byte[] values = serializer.serialize(map.get("value").toString());
                    connection.set(key, values);
                }
                return true;
            }
        }, false, true);
        return result;
    }

    /**
     * 批量向redis中插入:key  value
     * 如果键已存在则返回false,不更新,防止覆盖。使用pipeline批处理方式(不关注返回值)
     *
     * @param list           一个map代表一行记录,2个key:key & value。
     * @param ctimsModelEnum redis中key的值前缀。
     * @return
     */
    public boolean pipelinedHash(final List<Map<String, Object>> list,
                                 final CtimsModelEnum ctimsModelEnum) {
        boolean result = (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                for (Map<String, Object> map : list) {
                    byte[] key = serializer
                            .serialize(formatKey(ctimsModelEnum, map.get("key").toString()));
                    byte[] hkey = serializer.serialize(map.get("hkey").toString());
                    byte[] values = serializer.serialize(map.get("value").toString());
                    connection.hSet(key, hkey, values);
                }
                return true;
            }
        }, false, true);
        return result;
    }

    /***
     * 模糊搜索key值是否存在,spring-redis 版本号在1.8之后的不需要关闭游标，之前的需要关闭游标。
     * @param modelEnum
     * @param key
     * @param count
     * @return
     */
    public boolean scan(CtimsModelEnum modelEnum, String key, long count) throws Exception {
        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
        Cursor cursor = redisConnection
                .scan(ScanOptions.scanOptions().match(formatKey(modelEnum, key)).count(count).build());
        try {
            Boolean isHas = cursor.hasNext();
            return isHas;
        } catch (Exception e) {
            logger.error("redis 查询key是否存在 异常：" + formatKey(modelEnum, key), e);
            return false;
        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
    }


    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return GSON.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }


    /**
     * redis关键字生成
     *
     * @param modelEnum 枚举值
     * @param key       键
     * @return 返回key
     */
    private String formatKey(CtimsModelEnum modelEnum, String key) {
        return "ctims:" + modelEnum.getCode() + ":" + key;
    }

    /**
     * 返回多个redis 关键字生成
     *
     * @param modelEnum 枚举值
     * @param key       键
     * @return 返回列表 key
     */
    private List<String> formatKey(CtimsModelEnum modelEnum, String... key) {
        List<String> list = new ArrayList<>();
        for (String k : key) {
            list.add(formatKey(modelEnum, k));
        }
        return list;
    }

}
