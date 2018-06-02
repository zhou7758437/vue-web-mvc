package vue.web.mvc.service.util;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.event.*;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.impl.copy.SerializingCopier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/20 14:31
 * Description:
 */
public class MemCacheUtil {
    private static CacheManager cacheManager;

    private static Map<String, Cache> cacheMap = new HashMap<>();


    public synchronized static <K, V> Cache<K, V> getCache(String name, Class<K> keyType, Class<V> vType, long maxNum, long timeOut, TimeUnit timeUnit) {

        if (cacheManager == null) {
            cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
            cacheManager.init();
        }

        String keyName = String.format("%s_%s", keyType.getName(), vType.getName());
        keyName = name + "_" + keyName;
        Cache<K, V> cache = null;
        if (cacheMap.containsKey(keyName)) {
            cache = cacheMap.get(keyName);
        } else {
            CacheConfigurationBuilder<K, V> configuration = CacheConfigurationBuilder.newCacheConfigurationBuilder(keyType, vType, ResourcePoolsBuilder.heap(maxNum));

            if (timeOut > 0) {
                configuration = configuration.withExpiry(Expirations.timeToLiveExpiration(Duration.of(timeOut, timeUnit)));
            }
            if (!hasSerializer(keyType)) {
                EhCacheFastJsonSerializer<K> serializer = new EhCacheFastJsonSerializer<>(keyType, false);
                configuration.withKeySerializer(serializer);
            }
            if (!hasSerializer(vType)) {
                EhCacheFastJsonSerializer<V> serializer = new EhCacheFastJsonSerializer<>(vType, false);
                configuration.withValueSerializer(serializer);
                SerializingCopier<V> copier=new SerializingCopier<>(serializer);
                configuration.withValueCopier(copier);
            }


            cache = cacheManager.createCache(keyName,
                    configuration.build());


            cache.getRuntimeConfiguration()
                    .registerCacheEventListener(new CacheEventListener<K, V>() {
                                                    @Override
                                                    public void onEvent(CacheEvent<? extends K, ? extends V> cacheEvent) {

                                                    }
                                                },
                            EventOrdering.UNORDERED,
                            EventFiring.ASYNCHRONOUS,
                            EventType.REMOVED,
                            EventType.CREATED,
                            EventType.EVICTED,
                            EventType.EXPIRED,
                            EventType.UPDATED);
            cacheMap.put(keyName, cache);
        }

        return cache;
    }

    static final Set<Class> hasSerializerTypes = new HashSet<>();

    static {
        hasSerializerTypes.add(Long.class);
        hasSerializerTypes.add(Integer.class);
        hasSerializerTypes.add(Float.class);
        hasSerializerTypes.add(Double.class);
        hasSerializerTypes.add(Character.class);
        hasSerializerTypes.add(String.class);
        hasSerializerTypes.add(byte[].class);
    }

    private static <V> boolean hasSerializer(Class<V> vType) {
        if (Serializable.class.isAssignableFrom(vType)) {
            return true;
        }
        if (hasSerializerTypes.contains(vType)) {
            return true;
        }
        return false;
    }

}
