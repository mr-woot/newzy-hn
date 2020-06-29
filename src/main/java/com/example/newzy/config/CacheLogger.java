package com.example.newzy.config;

import com.example.newzy.constants.AppConstants;
import com.example.newzy.domain.entity.TopStoriesCache;
import com.example.newzy.domain.payload.StoryResponse;
import lombok.extern.log4j.Log4j2;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project: ehcache
 * Contributed By: Tushar Mudgal
 * On: 28/06/20 | 8:56 PM
 */
@Component
@Log4j2
public class CacheLogger implements CacheEventListener<String, Object> {

    @Override
    public void onEvent(CacheEvent<? extends String, ?> cacheEvent) {
        log.info("Key: {} | EventType: {}",
                cacheEvent.getKey(), cacheEvent.getType());
        if ((String.valueOf(cacheEvent.getKey()).equals(AppConstants.TOP_STORIES_CACHE_KEY)) &&
                (cacheEvent.getType() == EventType.EXPIRED)) {
            TopStoriesCache.setStoryResponseList((List<StoryResponse>) cacheEvent.getOldValue());
        }
    }
}