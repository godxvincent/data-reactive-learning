package com.godxvincent.datareactivelearning.repository;

import com.godxvincent.datareactivelearning.model.Reservation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {

}
