package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

    private final FluxTest fluxTest;

    public TestController(FluxTest fluxTest) {
        this.fluxTest = fluxTest;
    }

    @GetMapping
    public Mono<Void> test(){



        return Mono.empty();
    }

}
