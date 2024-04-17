package org.simplon.permisdrive.dtos.requests;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link org.simplon.permisdrive.models.entities.Sujet}
 */
@Value
public class SujetRequestDto implements Serializable {
    Long sujetId;
    @NotNull(message = "le Sujet ne doit pas être nul !")
    String sujet;
    String description;
}
