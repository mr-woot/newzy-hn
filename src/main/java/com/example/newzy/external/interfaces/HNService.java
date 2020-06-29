package com.example.newzy.external.interfaces;

import com.example.newzy.constants.AppConstants;
import com.example.newzy.domain.payload.CommentResponse;
import com.example.newzy.domain.payload.StoryResponse;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Project: ehcache
 * Contributed By: Tushar Mudgal
 * On: 27/06/20 | 6:56 PM
 */
public interface HNService {

    @Cacheable(value = "topStories", key = "'" + AppConstants.TOP_STORIES_CACHE_KEY + "'")
    List<StoryResponse> fetchTopStories() throws ExecutionException, InterruptedException;

    List<StoryResponse> fetchPreviousTopStories() throws ExecutionException, InterruptedException;

    @Cacheable(value = "topComments", key = "#storyId.toString()")
    List<CommentResponse> fetchTopCommentsOfStory(Long storyId) throws ExecutionException, InterruptedException;
}