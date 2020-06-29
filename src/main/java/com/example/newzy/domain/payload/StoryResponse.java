package com.example.newzy.domain.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Project: ehcache
 * Contributed By: Tushar Mudgal
 * On: 27/06/20 | 7:16 PM
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryResponse implements Serializable {
    private static final long serialVersionUID = 2L;
    private String by;
    private int id;
    private int descendants;
    private int[] kids;
    private int score;
    private String type;
    private String title;
    private String url;
    private Long time;
}