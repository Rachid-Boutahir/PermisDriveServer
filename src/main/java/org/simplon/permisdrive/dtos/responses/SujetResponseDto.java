package org.simplon.permisdrive.dtos.responses;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link org.simplon.permisdrive.models.entities.Sujet}
 */
@Value
public class SujetResponseDto implements Serializable {
    Long sujetId;
    @NotNull(message = "le Sujet ne doit pas être nul !")
    String sujet;
    String description;
    CoursDto cours;

    /**
     * DTO for {@link org.simplon.permisdrive.models.entities.Cours}
     */
    @Value
    public static class CoursDto implements Serializable {
        Long coursId;
        @NotNull(message = "la lesson ne doit pas être nul !")
        String lesson;
        String description;
    }
}
