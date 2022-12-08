package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.profile.model.dtos.HobbyDto;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;

import java.util.List;
import java.util.stream.Stream;

public class HobbyTestDataFactory {

    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first hobby", "second hobby", "third hobby"};

    private static final HobbyCategoryTestDataFactory categoryFactory = new HobbyCategoryTestDataFactory();

    public List<HobbyDto> buildDtoList(int listLength) {
        List<HobbyDto> hobbyDtoList = Stream.of(1, 2, 3).map(this::buildDto).toList();
        if (listLength > 0 && listLength < hobbyDtoList.size()) {
            return hobbyDtoList.subList(0, listLength);
        }
        return hobbyDtoList;
    }
    public HobbyDto buildDto(int setNumber) {
        HobbyDto hobbyDto;
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new HobbyDto(ids[setNumber], descriptors[setNumber], categoryFactory.buildDto(setNumber));
        }
        return new HobbyDto(ids[1], descriptors[1], categoryFactory.buildDto(1));
    }


    public HobbyEntity buildEntity(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new HobbyEntity(ids[setNumber], descriptors[setNumber], categoryFactory.buildEntity(setNumber));
        }
        return new HobbyEntity(ids[1], descriptors[1], categoryFactory.buildEntity(1));
    }

    public HobbyDto buildDtoWithoutCategory() {
        HobbyDto incompleteDto = new HobbyDto();
        incompleteDto.setId(ids[0]);
        incompleteDto.setDescriptor(descriptors[0]);
        return incompleteDto;
    }

    public HobbyDto buildDtoWithoutDescriptor() {
        HobbyDto incompleteDto = new HobbyDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
