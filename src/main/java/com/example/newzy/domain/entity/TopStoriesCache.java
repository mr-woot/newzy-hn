package com.example.newzy.domain.entity;

import com.example.newzy.domain.payload.StoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: ehcache
 * Contributed By: Tushar Mudgal
 * On: 29/06/20 | 8:40 PM
 */
@Component
public class TopStoriesCache {
    private static TopStoriesCache cache;
    private static List<StoryResponse> storyResponseList;

    private TopStoriesCache() {
        storyResponseList = new ArrayList<>();
    }

    public TopStoriesCache getInstance() {
        if (cache == null) {
            cache = new TopStoriesCache();
        }
        return cache;
    }

    public static List<StoryResponse> getStoryResponseList() {
        return storyResponseList;
    }

    public static void setStoryResponseList(List<StoryResponse> storyResponseList) {
        TopStoriesCache.storyResponseList = storyResponseList;
    }
}
