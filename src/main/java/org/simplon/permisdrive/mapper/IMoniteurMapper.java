package org.simplon.permisdrive.mapper;

import org.mapstruct.*;
import org.simplon.permisdrive.dtos.requests.MoniteurRequestDto;
import org.simplon.permisdrive.dtos.requests.MoniteurUpdateDto;
import org.simplon.permisdrive.models.entities.Moniteur;
import org.simplon.permisdrive.dtos.responses.MoniteurResponseDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface IMoniteurMapper {

    Moniteur toEntity(MoniteurRequestDto moniteurRequestDto);

//    Moniteur toEntity(MoniteurResponseDto moniteurResponseDto);

    Moniteur toEntity(MoniteurUpdateDto moniteurUpdateDto);

    MoniteurRequestDto toRequestDto(Moniteur moniteur);

    MoniteurUpdateDto toUpdateDto(Moniteur moniteur);

    MoniteurResponseDto toResponseDto(Moniteur moniteur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Moniteur partialUpdate(MoniteurRequestDto moniteurRequestDto, @MappingTarget Moniteur moniteur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Moniteur partialUpdate(MoniteurResponseDto moniteurResponseDto, @MappingTarget Moniteur moniteur);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Moniteur partialUpdate(MoniteurUpdateDto moniteurUpdateDto, @MappingTarget Moniteur moniteur);
}
