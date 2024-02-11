package de.evangeliumstaucher.app.component;

import de.evangeliumstaucher.repo.CacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaCacheManager implements CacheManager {

    private final CacheRepository cacheRepository;
    private final List<String> names = new ArrayList<>();

    @Override
    public Cache getCache(String name) {
        names.add(name);
        return new JpaCache(name, cacheRepository);
    }

    @Override
    public Collection<String> getCacheNames() {
        return names;
    }

}
