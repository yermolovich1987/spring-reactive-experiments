package com.dimasco.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class MovieEvent {
    private String name;
    private LocalDateTime date;
}
