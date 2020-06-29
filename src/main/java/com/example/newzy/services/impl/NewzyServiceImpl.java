package com.example.newzy.services.impl;

import com.example.newzy.external.interfaces.HNService;
import com.example.newzy.services.interfaces.NewzyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Project: ehcache
 * Contributed By: Tushar Mudgal
 * On: 28/06/20 | 12:46 AM
 */
@Service
public class NewzyServiceImpl implements NewzyService {

    @Autowired
    HNService hnService;

    @Override
    public List fetchTopStories() throws ExecutionException, InterruptedException {
        return hnService.fetchTopStories();
    }

    @Override
    public List fetchPreviousTopStories() throws ExecutionException, InterruptedException {
        return hnService.fetchPreviousTopStories();
    }

    @Override
    public List fetchTopCommentsOfStory(Long storyId) throws ExecutionException, InterruptedException {
        return hnService.fetchTopCommentsOfStory(storyId);
    }
}
