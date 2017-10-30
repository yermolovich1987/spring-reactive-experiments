package com.dimasco.demo.controller;

import com.dimasco.demo.model.Movie;
import com.dimasco.demo.model.MovieEvent;
import com.dimasco.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WebConfiguration {
    private final MovieService movieService;

    @Autowired
    public WebConfiguration(MovieService movieService) {
        this.movieService = movieService;
    }

    @Bean
    public RouterFunction<?> router() {
        return route(GET("/movies/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        request -> {
                            Mono<Movie> movie = Mono.justOrEmpty(request.pathVariable("id")).flatMap(movieService::findById);

                            return ServerResponse.ok().body(BodyInserters.fromPublisher(movie, Movie.class));
                        })

                .andRoute(GET("/movies/{id}/events").and(accept(MediaType.APPLICATION_JSON)).and(contentType(MediaType.TEXT_EVENT_STREAM)),
                        request -> {
                            Flux<MovieEvent> events = Flux.just(request.pathVariable("id")).flatMap(movieService::events);
                            events.subscribe(System.out::println);

                            return ServerResponse.ok().body(BodyInserters.fromPublisher(movieService.events(request.pathVariable("id")), MovieEvent.class));
                        })
                .andRoute(GET("/movies").and(accept(MediaType.APPLICATION_JSON)),
                        request -> ServerResponse.ok().body(BodyInserters.fromPublisher(movieService.findAll(), Movie.class)));
    }
}
