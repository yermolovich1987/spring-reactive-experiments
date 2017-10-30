package com.dimasco.demo.service;

import com.dimasco.demo.model.Movie;
import com.dimasco.demo.model.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Mono<Movie> save(Movie movie);

    Mono<Movie> findById(String id);

    Flux<Movie> findAll();

    Flux<MovieEvent> events(String id);
}
