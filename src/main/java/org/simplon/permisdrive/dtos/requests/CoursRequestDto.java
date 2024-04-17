package org.simplon.permisdrive.dtos.requests;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link org.simplon.permisdrive.models.entities.Cours}
 */
@Value
public class CoursRequestDto implements Serializable {
    @NotNull(message = "la lesson ne doit pas être nul !")
    String lesson;
    Double prix;
    String description;
}
