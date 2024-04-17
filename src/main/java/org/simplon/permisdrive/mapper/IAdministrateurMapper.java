package org.simplon.permisdrive.mapper;

import org.mapstruct.*;
import org.simplon.permisdrive.dtos.requests.AdministrateurRequestDto;
import org.simplon.permisdrive.dtos.requests.AdministrateurUpdateDto;
import org.simplon.permisdrive.dtos.responses.AdministrateurResponseDto;
import org.simplon.permisdrive.models.entities.Administrateur;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface IAdministrateurMapper {
    Administrateur toEntity(AdministrateurRequestDto administrateurRequestDto);

    Administrateur toEntity(AdministrateurResponseDto administrateurResponseDto);

    Administrateur toEntity(AdministrateurUpdateDto administrateurUpdateDto);

    AdministrateurRequestDto toRequestDto(Administrateur administrateur);

    AdministrateurResponseDto toResponseDto(Administrateur administrateur);

    AdministrateurUpdateDto toUpdateDto(Administrateur administrateur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Administrateur partialUpdate(AdministrateurRequestDto administrateurRequestDto,
                                 @MappingTarget Administrateur administrateur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Administrateur partialUpdate(AdministrateurResponseDto administrateurResponseDto,
                                 @MappingTarget Administrateur administrateur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Administrateur partialUpdate(AdministrateurUpdateDto administrateurUpdateDto,
                                 @MappingTarget Administrateur administrateur);
}
