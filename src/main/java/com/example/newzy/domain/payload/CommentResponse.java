package com.example.newzy.domain.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Project: ehcache
 * Contributed By: Tushar Mudgal
 * On: 28/06/20 | 8:18 PM
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentResponse implements Serializable {
    private static final long serialVersionUID = 2L;
    private String by;
    private int id;
    private int[] kids;
    private Long parent;
    private String text;
    private String type;
    private Long time;
}
