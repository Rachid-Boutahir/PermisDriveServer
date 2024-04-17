package org.simplon.permisdrive.mapper;

import org.mapstruct.*;
import org.simplon.permisdrive.models.entities.Sujet;
import org.simplon.permisdrive.dtos.requests.SujetRequestDto;
import org.simplon.permisdrive.dtos.requests.SujetUpdateDto;
import org.simplon.permisdrive.dtos.responses.SujetResponseDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ISujetMapper {
    Sujet toEntity(SujetRequestDto sujetRequestDto);

    Sujet toEntity(SujetResponseDto sujetResponseDto);

    Sujet toEntity(SujetUpdateDto sujetUpdateDto);

    SujetRequestDto toRequestDto(Sujet sujet);

    SujetResponseDto toResponseDto(Sujet sujet);

    SujetUpdateDto toUpdateDto(Sujet sujet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sujet partialUpdate(SujetRequestDto sujetRequestDto, @MappingTarget Sujet sujet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sujet partialUpdate(SujetResponseDto sujetResponseDto, @MappingTarget Sujet sujet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sujet partialUpdate(SujetUpdateDto sujetUpdateDto, @MappingTarget Sujet sujet);
}
