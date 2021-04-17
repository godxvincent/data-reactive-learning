package com.godxvincent.datareactivelearning.services;

import com.godxvincent.datareactivelearning.model.Reservation;
import com.godxvincent.datareactivelearning.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
// Puedo indicarle con la etiqueta transactional que todo lo que ocurra en el servicio es transaccional.
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TransactionalOperator transactionalOperator;


    public Flux<Reservation> saveAll(String ... names){
        Flux<Reservation> reservations = Flux
                .fromArray(names)
                .map(name -> new Reservation(null, name))
                .flatMap(this.reservationRepository::save)
                .doOnNext(this::assertValid);

        return reservations;
        // De esta manera le decimos al operador transaccional que este pendiente de que todas las operaciones
        // delegadas sean resuletas.
//        return transactionalOperator.transactional(reservations);
    }

    private void assertValid(Reservation reservation) {
        Assert.isTrue(reservation.getName() != null &&
                                reservation.getName().length() > 0 &&
                                Character.isUpperCase(reservation.getName().charAt(0)),
                "The name must start with a capital letter");

    }
}
