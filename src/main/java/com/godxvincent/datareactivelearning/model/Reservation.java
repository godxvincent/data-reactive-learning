package com.godxvincent.datareactivelearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data // Crea getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    private Integer id;
    private String name;
}
