package org.simplon.permisdrive.dtos.requests;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.simplon.permisdrive.models.entities.Cours}
 */
@Value
public class CoursUpdateDto implements Serializable {
    String lesson;
    Double prix;
    String description;
}
