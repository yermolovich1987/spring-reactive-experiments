package com.dimasco.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Movie {
    private String id;
    private String title;

    public Movie(String title) {
        this.title = title;
    }
}
