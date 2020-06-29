package com.example.newzy.external.impl;

import com.example.newzy.commons.RestClientAdapter;
import com.example.newzy.domain.entity.TopStoriesCache;
import com.example.newzy.domain.payload.CommentResponse;
import com.example.newzy.domain.payload.StoryResponse;
import com.example.newzy.external.interfaces.HNService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static com.example.newzy.external.utils.RestClientUtils.executeGet;

/**
 * Project: ehcache
 * Contributed By: Tushar Mudgal
 * On: 27/06/20 | 7:12 PM
 */
@Service
@Log4j2
public class HNServiceImpl implements HNService {

    // @Autowired External service call
    @Autowired
    RestClientAdapter adapter;

    @Value("${HN_BASE_URI}")
    private String BASE_URI;

    @Override
    public List<StoryResponse> fetchTopStories() throws ExecutionException, InterruptedException {
        URI uri = URI.create(BASE_URI + "topstories.json");
        ResponseEntity<List<Long>> listOfStoryIds = executeGet(adapter,
                uri, new ParameterizedTypeReference<List<Long>>() {});
        // retrieved list of ids (trim to first 10)
        List<Long> items = Objects.requireNonNull(listOfStoryIds.getBody()).subList(0, 10);
        // create executor and run parallel threads to execute get
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<CompletableFuture<StoryResponse>> res = new ArrayList<>();
        int len = Math.min(items.size(), 10);
        for (int i = 0; i < len; i++) {
            URI itemURI = URI.create(BASE_URI + "item/" + items.get(i) + ".json");
            CompletableFuture<StoryResponse> it = CompletableFuture.supplyAsync(() ->
                            executeGet(adapter, itemURI, new ParameterizedTypeReference<StoryResponse>() {}).getBody()
                    , executorService);
            res.add(it);
        }
        return res
                .stream()
                .map(CompletableFuture::join)
                .sorted(Comparator.comparingInt(StoryResponse::getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<StoryResponse> fetchPreviousTopStories() {
        log.info("[FetchPreviousTopStories] -- " + TopStoriesCache.getStoryResponseList());
        return TopStoriesCache.getStoryResponseList();
    }

    @Override
    public List fetchTopCommentsOfStory(Long storyId) {
        // URI creation
        URI itemURI = URI.create(BASE_URI + "item/" + storyId + ".json");
        ResponseEntity<StoryResponse> storyResponse = executeGet(adapter,
                itemURI, new ParameterizedTypeReference<StoryResponse>() {});
        int[] kids = Objects.requireNonNull(storyResponse.getBody()).getKids();
        int len = Math.min(kids.length, 10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<CompletableFuture<CommentResponse>> res = new ArrayList<>();
        // 10 future requests to get details of comment by story id
        for (int i = 0; i < len; i++) {
            URI uri = URI.create(BASE_URI + "item/" + kids[i] + ".json");
            CompletableFuture<CommentResponse> it = CompletableFuture.supplyAsync(() ->
                            executeGet(adapter, uri, new ParameterizedTypeReference<CommentResponse>() {}).getBody()
                    , executorService);
            res.add(it);
        }
        return res
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
