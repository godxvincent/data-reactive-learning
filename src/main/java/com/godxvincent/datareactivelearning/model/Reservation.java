package com.godxvincent.datareactivelearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data // Crea getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    private String id;
    private String name;
}
