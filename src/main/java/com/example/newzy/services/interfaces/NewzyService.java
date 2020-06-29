package com.example.newzy.services.interfaces;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Project: ehcache
 * Contributed By: Tushar Mudgal
 * On: 22/06/20 | 7:36 PM
 */
public interface NewzyService {
    List fetchTopStories() throws ExecutionException, InterruptedException;
    List fetchPreviousTopStories() throws ExecutionException, InterruptedException;
    List fetchTopCommentsOfStory(Long storyId) throws ExecutionException, InterruptedException;
}
