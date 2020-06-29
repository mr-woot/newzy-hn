package com.example.newzy.external.utils;

import com.example.newzy.commons.RestClientAdapter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 * Project: ehcache
 * Contributed By: Tushar Mudgal
 * On: 28/06/20 | 6:00 PM
 */
public class RestClientUtils {

    public RestClientUtils() { }

    public static <T> ResponseEntity<T> executeGet(RestClientAdapter adapter, URI uri, ParameterizedTypeReference<T> ref) {
        return adapter.getRestTemplate().exchange(uri,
                HttpMethod.GET, null, ref);
    }
}
