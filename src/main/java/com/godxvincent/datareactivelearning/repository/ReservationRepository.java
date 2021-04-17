package com.godxvincent.datareactivelearning.repository;

import com.godxvincent.datareactivelearning.model.Reservation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ReservationRepository extends ReactiveCrudRepository<Reservation, Integer> {


    Flux<Reservation> findByName(String name);
}
