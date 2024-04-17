package org.simplon.permisdrive.mapper;

import org.mapstruct.*;
import org.simplon.permisdrive.dtos.requests.CoursUpdateDto;
import org.simplon.permisdrive.models.entities.Cours;
import org.simplon.permisdrive.dtos.requests.CoursRequestDto;
import org.simplon.permisdrive.dtos.responses.CoursResponseDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICoursMapper {
    Cours RequestDtoToEntity(CoursRequestDto coursRequestDto);

    Cours UpdateDtoToEntity(CoursUpdateDto coursUpdateDto);

    Cours ResponseDtoToEntity(CoursResponseDto coursResponseDto);

    CoursUpdateDto toUpdateDto(Cours cours);

    CoursRequestDto toRequestDto(Cours cours);

    CoursResponseDto toResponseDto(Cours cours);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cours partialUpdate(CoursRequestDto coursRequestDto, @MappingTarget Cours cours);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cours partialUpdate(CoursResponseDto coursResponseDto, @MappingTarget Cours cours);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cours partialUpdate(CoursUpdateDto coursUpdateDto, @MappingTarget Cours cours);

    @AfterMapping
    default void linkSujets(@MappingTarget Cours cours) {
        cours.getSujets().forEach(sujet -> sujet.setCours(cours));
    }
}
