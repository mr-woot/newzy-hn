package com.example.newzy.controllers;

import com.example.newzy.services.impl.NewzyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Project: ehcache
 * Contributed By: Tushar Mudgal
 * On: 22/06/20 | 7:35 PM
 */
@RestController
@RequestMapping("/api/v1/")
public class NewzyController {

    @Autowired
    NewzyServiceImpl newzyService;

    /*
     * /top-stories API
     */
    @GetMapping(value = "top-stories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List fetchTopStories() throws ExecutionException, InterruptedException {
        return newzyService.fetchTopStories();
    }

    @GetMapping(value = "previous-top-stories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List fetchPreviousTopStories() throws ExecutionException, InterruptedException {
        return newzyService.fetchPreviousTopStories();
    }

    @GetMapping(value = "top-comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List fetchTopStories(@RequestParam Long storyId) throws ExecutionException, InterruptedException {
        return newzyService.fetchTopCommentsOfStory(storyId);
    }
}
