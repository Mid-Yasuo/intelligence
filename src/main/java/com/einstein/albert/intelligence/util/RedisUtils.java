package com.einstein.albert.intelligence.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 张春杰
 * @date 2022/5/6
 */
@SuppressWarnings({"unchecked", "unused"})
public class RedisUtils {

    private static final RedisTemplate<String, Object> REDIS_TEMPLATE = ContextUtils.getBean("redisTemplate", RedisTemplate.class);

    /**
     * <p>保存</p>
     *
     * @param key   - [String]
     * @param value - [Object]
     * @author 张春杰
     * @date 2023/3/15
     */
    public static void set(String key, Object value) {
        REDIS_TEMPLATE.opsForValue().set(key, value);
    }

    /**
     * <p>设置 不指定过期时间单位：默认秒</p>
     *
     * @param key      key
     * @param value    value
     * @param duration 过期时间，单位：秒
     * @author 张春杰
     * @date 2022/5/6
     */
    public static void set(String key, Object value,
                           int duration) {
        set(key, value, duration, TimeUnit.SECONDS);
    }

    /**
     * <p>设置 指定过期时间单位</p>
     *
     * @param key      key
     * @param value    value
     * @param duration 过期时间
     * @param timeUnit 时间单位
     * @author 张春杰
     * @date 2022/5/6
     */
    public static void set(String key, Object value,
                           int duration, TimeUnit timeUnit) {
        REDIS_TEMPLATE.opsForValue().set(key, value, duration, timeUnit);
    }

    /**
     * <p>若不存在 则设置，return true 否则 return false</p>
     *
     * @param key      键
     * @param value    值
     * @param duration 时长
     * @param timeUnit 时间单位
     * @return boolean
     * @author 张春杰
     * @date 2022/5/6
     */
    public static boolean setIfAbsent(String key, Object value,
                                      int duration, TimeUnit timeUnit) {
        return Boolean.TRUE.equals(REDIS_TEMPLATE.opsForValue().setIfAbsent(key, value, duration, timeUnit));
    }

    /**
     * <p>查询</p>
     *
     * @param key 键
     * @return Object
     * @author 张春杰
     * @date 2022/5/6
     */
    public static Object getValue(String key) {
        return REDIS_TEMPLATE.opsForValue().get(key);
    }

    /**
     * <p>查询 & 设置</p>
     *
     * @param key      键
     * @param duration 时长
     * @return Object
     * @author 张春杰
     * @date 2022/5/6
     */
    public static Object getAndSetValue(String key, int duration) {
        ValueOperations<String, Object> valueOperations = REDIS_TEMPLATE.opsForValue();
        Object redisObj = valueOperations.get(key);
        if (Objects.nonNull(redisObj)) {
            valueOperations.set(key, redisObj, duration, TimeUnit.SECONDS);
        }
        return redisObj;
    }

    /**
     * <p>list left push</p>
     *
     * @param key   键
     * @param value 值
     * @author 张春杰
     * @date 2022/5/6
     */
    public static void leftPush(String key, Object value) {
        REDIS_TEMPLATE.opsForList().leftPush(key, value);
    }

    /**
     * <p>list right pop</p>
     *
     * @param key 键
     * @return Object 队列元素
     * @author 张春杰
     * @date 2022/5/6
     */
    public static Object rightPop(String key) {
        return REDIS_TEMPLATE.opsForList().rightPop(key);
    }

    /**
     * <p>删除键</p>
     *
     * @param key 键
     * @author 张春杰
     * @date 2022/5/6
     */
    public static void deleteKey(String key) {
        REDIS_TEMPLATE.delete(key);
    }

    public static Set<Object> hashKeys(String key) {
        return REDIS_TEMPLATE.opsForHash().keys(key);
    }
    /**
     * <p>设置 hash value</p>
     *
     * @param key     - [String]
     * @param hashKey - [String]
     * @param value   - [String]
     * @author 张春杰
     * @date 2024/1/6
     */
    public static boolean setHash(String key, String hashKey, Object value) {
        return REDIS_TEMPLATE.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * <p>获取hash value</p>
     *
     * @param key     - [String]
     * @param hashKey - [String]
     * @return Object
     * @author 张春杰
     * @date 2024/1/6
     */
    public static Object getHashValue(String key, String hashKey) {
        return REDIS_TEMPLATE.opsForHash().get(key, hashKey);
    }


    public static long deleteHash(String key, Object... hashKey) {
        return Optional.of(REDIS_TEMPLATE.opsForHash().delete(key, hashKey)).orElse(0L);
    }

    /**
     * <p>查询 list 长度</p>
     *
     * @param key 键
     * @return long 队列元素数
     * @author 张春杰
     * @date 2022/5/7
     */
    public static long countRedisListCache(String key) {
        Long cacheSize = REDIS_TEMPLATE.opsForSet().size(key);
        return cacheSize == null ? 0 : cacheSize;
    }

    public static Object randomMember(String key) {
        return REDIS_TEMPLATE.opsForSet().randomMember(key);
    }

    public static List<Object> randomMembers(String key, long count) {
        return REDIS_TEMPLATE.opsForSet().randomMembers(key, count);
    }

    /**
     * <p>保存 set 缓存</p>
     *
     * @param key   key
     * @param value 值
     * @author 张春杰
     * @date 2022/5/7
     */
    public static void addRedisSetCache(String key, Object value) {
        REDIS_TEMPLATE.opsForSet().add(key, value);
    }

    /**
     * <p>移动列表元素</p>
     *
     * @param key     当前列表 key
     * @param destKey 目标列表 key
     * @author 张春杰
     * @date 2022/5/7
     */
    public static void move(String key, String destKey) {
        SetOperations<String, Object> setOperations = REDIS_TEMPLATE.opsForSet();
        Set<Object> members = setOperations.members(key);
        if (members == null || members.isEmpty()) {
            return;
        }
        for (Object object : members) {
            setOperations.move(key, object, destKey);
        }

    }

    /**
     * <p>判断key 是否存在</p>
     *
     * @param key - [String]
     * @author 张春杰
     * @date 2023/3/15
     */
    public static boolean exist(String key) {
        return Boolean.TRUE.equals(REDIS_TEMPLATE.hasKey(key));
    }

    /**
     * <p>判断是否为列表元素</p>
     *
     * @param key     set key
     * @param element element
     * @author 张春杰
     * @date 2022/5/10
     */
    public static boolean isMember(String key, Object element) {
        SetOperations<String, Object> setOperations = REDIS_TEMPLATE.opsForSet();
        return Boolean.TRUE.equals(setOperations.isMember(key, element));
    }

    /**
     * <p>set 添加元素</p>
     *
     * @param key     set key
     * @param element element
     * @author 张春杰
     * @date 2022/5/10
     */
    public static void push(String key, Object element) {
        REDIS_TEMPLATE.opsForSet().add(key, element);
    }

    public static void removeSetElements(String key, Object... elements) {
        SetOperations<String, Object> setOperations = REDIS_TEMPLATE.opsForSet();
        setOperations.remove(key, elements);
    }


    public static int increase(String key) {
        ValueOperations<String, Object> valueOperations = REDIS_TEMPLATE.opsForValue();
        Long increment = valueOperations.increment(key, 1);
        if (increment == null) {
            valueOperations.set(key, 1, 5, TimeUnit.MINUTES);
            return 1;
        }
        return Integer.parseInt(String.valueOf(increment));
    }

    private RedisUtils() {
        //空参构造
    }

}
