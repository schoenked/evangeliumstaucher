package de.evangeliumstaucher.app.component;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.evangeliumstaucher.entity.CacheEntity;
import de.evangeliumstaucher.repo.CacheRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.io.*;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class JpaCache implements Cache {
    final Gson gson = Converters.registerOffsetDateTime(new GsonBuilder()).create();
    private final String name;
    private final CacheRepository cacheRepository;
    private final Type type;

    public JpaCache(String name, CacheRepository cacheRepository, Type type) {
        this.name = name;
        this.cacheRepository = cacheRepository;
        this.type = type;
    }

    @Override
    public CompletableFuture<?> retrieve(Object key) {
        return Cache.super.retrieve(key);
    }

    @Override
    public <T> CompletableFuture<T> retrieve(Object key, Supplier<CompletableFuture<T>> valueLoader) {
        return Cache.super.retrieve(key, valueLoader);
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
        CacheEntity cacheEntity = cacheRepository.findFirstByKeyOrderByExpiryDesc(this.name + gson.toJson(key));
        if (cacheEntity != null && cacheEntity.getExpiry() > System.currentTimeMillis()) {
            Object o = gson.fromJson(cacheEntity.getValue(), type);
            if (o == null) {
                return null;
            }
            return new SimpleValueWrapper(o);
        }
        return null;
    }

    private Object getDeserialized(String s) throws IOException, ClassNotFoundException {
        // Deserialize the TypeToken instance from the byte array
        ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object o = ois.readObject();
        ois.close();
        return o;
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
        String val = gson.toJson(value);
        cacheEntity.setKey(this.name + gson.toJson(key));
        cacheEntity.setValue(val);
        cacheEntity.setExpiry(System.currentTimeMillis() + Duration.of(14, ChronoUnit.DAYS).toMillis()); // cache
        cacheRepository.save(cacheEntity);
    }

    private String getSerialized(Object o) throws IOException {
        // Serialize the TypeToken instance to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return baos.toString();
    }

    @Override
    public void evict(Object key) {
        CacheEntity cacheEntity = cacheRepository.findFirstByKeyOrderByExpiryDesc(this.name);
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