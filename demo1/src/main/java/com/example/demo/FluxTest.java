package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

@Slf4j
@Component
public class FluxTest {

    public Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5);

    public void subscribe() throws InterruptedException {
        Mono m1 = Mono.just(1).map(x -> {
            log.info(currentThread().getName() + ": 1 sleep");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return x;
        }).subscribeOn(Schedulers.newElastic("PUB1"));
        Mono m2 = Mono.just(2).map(x -> {
            log.info(currentThread().getName() + ": 2 sleep");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return x;
        }).subscribeOn(Schedulers.newElastic("PUB2"));
        Mono m3 = Mono.just(3).map(x -> {
            log.info(currentThread().getName() + ": 3 sleep");
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return x;
        }).subscribeOn(Schedulers.newElastic("PUB3"));
        log.info(currentThread().getName() + " : Mono.zip(m1, m2, m3)");
        Mono.zip(m1, m2, m3).publishOn(Schedulers.newElastic("zip")).subscribe(tup -> log.info(currentThread().getName() + " next: " + tup));
        sleep(5000); // parallel 스케줄러는 데몬 스레드이며, main thread는 비 데몬 스레드로 main문이 종되면 데몬 스레드는 자동 종료되므로, sleep 처리함.


    }

    public void addElement(Integer add) {

    }

}
