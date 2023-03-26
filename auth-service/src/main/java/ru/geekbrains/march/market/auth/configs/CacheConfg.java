package ru.geekbrains.march.market.auth.configs;

import org.springframework.context.annotation.Bean;
import ru.geekbrains.march.market.auth.entities.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheConfg {
    @Bean
    public Map<String, Cache> setCache() {
        Map<String, Cache> cache = new ConcurrentHashMap<>();
        return cache;
    }
}
