package org.simplon.permisdrive.dtos.responses;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link org.simplon.permisdrive.models.entities.Cours}
 */
@Value
public class CoursResponseDto implements Serializable {
    Long coursId;
    String lesson;
    Double prix;
    String description;
    LocalDate createdAt;
    Set<SujetDto> sujets;

    /**
     * DTO for {@link org.simplon.permisdrive.models.entities.Sujet}
     */
    @Value
    public static class SujetDto implements Serializable {
        Long sujetId;
        String sujet;
        String description;
        LocalDate createdAt;
    }
}
