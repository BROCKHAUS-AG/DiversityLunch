package de.brockhausag.diversitylunchspringboot.profile.mapper;

import de.brockhausag.diversitylunchspringboot.profile.logic.HobbyCategoryService;
import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyCategoryEntity;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class HobbyMapper {

    private final HobbyCategoryMapper categoryMapper;
    private final HobbyCategoryService hobbyCategoryService;


    public HobbyDto entityToDto(HobbyEntity entity) {
        HobbyDto dto = new HobbyDto();
        dto.setId(entity.getId());
        dto.setDescriptor(entity.getDescriptor());
        dto.setCategory(categoryMapper.entityToDto(entity.getCategory()));

        return dto;
    }


    public Optional<HobbyEntity> dtoToEntity(HobbyDto dto) {
        Optional<HobbyCategoryEntity> optionalHobbyCategoryEntityEntity = this.hobbyCategoryService.getEntityById(dto.getCategory().getId());

        if (optionalHobbyCategoryEntityEntity.isEmpty()) {
            return Optional.empty();
        }

        HobbyEntity entity = new HobbyEntity();
        entity.setId(dto.getId());
        entity.setDescriptor(dto.getDescriptor());
        entity.setCategory(optionalHobbyCategoryEntityEntity.get());
        return Optional.of(entity);
    }

    public List<HobbyDto> entityToDto(List<HobbyEntity> entities) {
            return entities.stream().map(this::entityToDto).collect(Collectors.toList());
        }
}

