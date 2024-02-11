package de.evangeliumstaucher.app.component;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.evangeliumstaucher.entity.CacheEntity;
import de.evangeliumstaucher.repo.CacheRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;

public class JpaCache implements Cache {
    final Gson gson = Converters.registerOffsetDateTime(new GsonBuilder()).create();
    private final String name;
    private final CacheRepository cacheRepository;


    public JpaCache(String name, CacheRepository cacheRepository) {
        this.name = name;
        this.cacheRepository = cacheRepository;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object key) {
        CacheEntity cacheEntity = cacheRepository.findByKey(this.name + gson.toJson(key));
        if (cacheEntity != null && cacheEntity.getExpiry() > System.currentTimeMillis()) {
            Object o = null;
            try {
                o = gson.fromJson(cacheEntity.getValue(),
                        Class.forName(cacheEntity.getClazz()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            return new SimpleValueWrapper(o);
        }
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        CacheEntity cacheEntity = new CacheEntity();
        cacheEntity.setKey(this.name + gson.toJson(key));
        cacheEntity.setClazz(value.getClass().getName());
        cacheEntity.setValue(gson.toJson(value));
        cacheEntity.setExpiry(System.currentTimeMillis() + Duration.of(14, ChronoUnit.DAYS).toMillis()); // cache for 1 hour
        cacheRepository.save(cacheEntity);
    }

    @Override
    public void evict(Object key) {
        CacheEntity cacheEntity = cacheRepository.findByKey(gson.toJson(key));
        if (cacheEntity != null) {
            cacheRepository.deleteById(cacheEntity.getId());
        }
    }

    @Override
    public void clear() {
        //dont
        //cacheRepository.deleteAll();
    }
}