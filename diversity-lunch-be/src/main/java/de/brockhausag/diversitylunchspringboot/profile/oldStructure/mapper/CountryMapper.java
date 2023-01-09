package de.brockhausag.diversitylunchspringboot.profile.oldStructure.mapper;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.BasicDimensionSelectableOptionRepository;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.dtos.CountryDto;
import de.brockhausag.diversitylunchspringboot.profile.oldStructure.generics.DimensionMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class CountryMapper implements DimensionMapper<CountryDto, BasicDimensionSelectableOption> {

    private final BasicDimensionService dimensionService;

    @Override
    public CountryDto entityToDto(BasicDimensionSelectableOption entity) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(entity.getId());
        countryDto.setDescriptor(entity.getValue());
        return countryDto;
    }

    @Override
    public BasicDimensionSelectableOption dtoToEntity(CountryDto dto) {
        return BasicDimensionSelectableOption.builder()
                .id(dto.getId())
                .value(dto.getDescriptor())
                .ignoreInScoring(false)
                .dimensionCategory(dimensionService.getDimension("Ethnische Herkunft").getDimensionCategory())
                .build();
    }
}
