package com.dimasco.demo.service;

import com.dimasco.demo.model.Movie;
import com.dimasco.demo.model.MovieEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Veeeeeeeery dummy service that stores data in the in memory set. Currently there is no value from reactive components. Just to check what exists.
 *
 * TODO Try to use ReactiveMongoRepositoryLater.
 */
@Service
public class DummyMovieService implements MovieService {
    private final Map<String, Movie> movies = new ConcurrentHashMap<>();
    //private final AtomicLong atomicLong = new AtomicLong();

    @Override
    public Mono<Movie> save(Movie movie) {
        movie.setId(UUID.randomUUID().toString());
        movies.put(movie.getId(), movie);

        return Mono.just(movie);
    }

    @Override
    public Mono<Movie> findById(String id) {
        return Mono.justOrEmpty(movies.get(id));
    }

    @Override
    public Flux<Movie> findAll() {
        return Flux.just(movies.values().toArray(new Movie[movies.size()]));
    }

    @Override
    public Flux<MovieEvent> events(String id) {
        return Flux.<MovieEvent> generate(synchronousSink -> synchronousSink.next(new MovieEvent(id, LocalDateTime.now()))).delayElements(Duration.ofSeconds(1));
    }
}
