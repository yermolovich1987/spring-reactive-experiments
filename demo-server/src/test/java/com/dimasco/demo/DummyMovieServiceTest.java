package com.dimasco.demo;

import com.dimasco.demo.service.DummyMovieService;
import org.junit.Test;

public class DummyMovieServiceTest {

    @Test
    public void eventsStream() {
        DummyMovieService dummyMovieService = new DummyMovieService();

        dummyMovieService.events("Aaa").log().take(5).subscribe(System.out::println);
    }
}
