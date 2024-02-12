package de.evangeliumstaucher.app.component;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.evangeliumstaucher.entity.CacheEntity;
import de.evangeliumstaucher.repo.CacheRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.io.*;
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
                o = getDeserialized(cacheEntity.getValue());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
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
        try {
            String clazz = getSerialized(value);
            cacheEntity.setKey(this.name + gson.toJson(key));
            cacheEntity.setValue(gson.toJson(value));
            cacheEntity.setExpiry(System.currentTimeMillis() + Duration.of(14, ChronoUnit.DAYS).toMillis()); // cache
            cacheRepository.save(cacheEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        CacheEntity cacheEntity = cacheRepository.findByKey(this.name);
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