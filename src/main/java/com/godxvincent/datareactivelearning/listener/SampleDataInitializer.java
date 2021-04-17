package com.godxvincent.datareactivelearning.listener;

import com.godxvincent.datareactivelearning.model.Reservation;
import com.godxvincent.datareactivelearning.repository.ReservationRepository;
import com.godxvincent.datareactivelearning.services.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
//Esta versión esta deprecada
//import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


@Component
// Esta anotación de lombok analiza el código y entiende que una variable final sin inicializar debe crearsele un constructor.
@RequiredArgsConstructor
@Log4j2
public class SampleDataInitializer {

    private final ReservationRepository reservationRepository;

    private final DatabaseClient databaseClient;

    private final ReservationService reservationService;

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {

        this.databaseClient
                .sql("select * from reservation")
                .fetch()
                .all()
                .doOnComplete(() -> log.info("----------------------------------------------"))
                .subscribe(log::info);

        Flux<Reservation> reservations = reservationService.saveAll("Ricardo", "Andres", "Vargas", "Martinez", "Carlos", "Violet");
        reservationRepository
                .deleteAll()
                .thenMany(reservations)
                .thenMany(this.reservationRepository.findAll())
                .subscribe(log::info);


    }


}
