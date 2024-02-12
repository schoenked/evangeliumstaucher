package de.evangeliumstaucher.app.component;

import de.evangeliumstaucher.repo.CacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaCacheManager implements CacheManager {

    private final CacheRepository cacheRepository;
    private final List<String> names = new ArrayList<>();
    private final HashMap<String, Type> cacheTypes = new HashMap<>();

    @Override
    public Cache getCache(String name) {
        names.add(name);
        return new JpaCache(name, cacheRepository, cacheTypes.get(name));
    }

    @Override
    public Collection<String> getCacheNames() {
        return names;
    }

    public void add(String cacheName, Type type) {
        cacheTypes.put(cacheName, type);
    }
}
