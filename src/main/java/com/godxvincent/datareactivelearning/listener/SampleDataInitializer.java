package com.godxvincent.datareactivelearning.listener;

import com.godxvincent.datareactivelearning.model.Reservation;
import com.godxvincent.datareactivelearning.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Component
// Esta anotación de lombok analiza el código y entiende que una variable final sin inicializar debe crearsele un constructor.
@RequiredArgsConstructor
@Log4j2
public class SampleDataInitializer {

    private final ReservationRepository reservationRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {

        // Al hacer crear un flux estoy creando un publicador de eventos de 0 a N.
        // con just estoy creando varios datos que serán emitidos por el publicador
        Flux<String> names = Flux.just("Ricardo", "Andres", "Vargas", "Martinez", "Carlos", "Violet");
        // Itero la lista de eventos y creo nuevos publicadores de tipo Reservation publicando estas emiciones
        Flux<Reservation> reservations = names.map(name -> new Reservation(null, name));
        // Creo otro publicador donde cada emisión (emit) en realidad es la invocación del salvado de cada uno de los objetos creados.
        Flux<Reservation> saved = reservations.flatMap(reservation -> this.reservationRepository.save(reservation));

        // Creo un pipe donde elimino lo que hay en la tabla, luego todas las emisiones de salvar objetos es aplicado, luego
        // recupero lo que he escrito y luego me suscribo pasandole una funcion de log para que muestre en consola
        // los objetos salvados.
        this.reservationRepository.deleteAll().thenMany(saved).thenMany(this.reservationRepository.findAll()).subscribe(log::info);

//        Esto genera un publicador anidado dentro de otro.
//        Flux<Mono<Reservation>> map = reservations.map(reservation -> this.reservationRepository.save(reservation));
    }


}
