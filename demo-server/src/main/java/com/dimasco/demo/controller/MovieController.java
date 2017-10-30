package com.dimasco.demo.controller;

import com.dimasco.demo.model.Movie;
import com.dimasco.demo.model.MovieEvent;
import com.dimasco.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public Flux<Movie> getAllMovies() {
        return movieService.findAll();
    }

    @GetMapping("/movies/{id}")
    public Mono<Movie> getById(@PathVariable String id) {
        return movieService.findById(id);
    }

    @GetMapping(value = "/movies/{id}/events", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<MovieEvent> getEvents(@PathVariable String id) {
        return movieService.events(id);
    }
}

