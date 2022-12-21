package de.brockhausag.diversitylunchspringboot.dataFactories;

import java.util.List;
import java.util.stream.Stream;

public class HobbyTestDataFactory {

    private static final int numberOfCompleteSets = 3;
    private static final Long[] ids = {666L, 1L, 2L, 3L};
    private static final String[] descriptors = {"incomplete", "first hobby", "second hobby", "third hobby"};

    public List<HobbyDto> buildDtoList(int listLength) {
        List<HobbyDto> hobbyDtoList = Stream.of(1, 2, 3).map(this::buildDto).toList();
        if (listLength > 0 && listLength < hobbyDtoList.size()) {
            return hobbyDtoList.subList(0, listLength);
        }
        return hobbyDtoList;
    }
    public HobbyDto buildDto(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new HobbyDto(ids[setNumber], descriptors[setNumber]);
        }
        return new HobbyDto(ids[1], descriptors[1]);
    }


    public HobbyEntity buildEntity(int setNumber) {
        if ((setNumber >= 1) && setNumber <= numberOfCompleteSets) {
            return new HobbyEntity(ids[setNumber], descriptors[setNumber]);
        }
        return new HobbyEntity(ids[1], descriptors[1]);
    }

    public HobbyDto buildDtoWithoutDescriptor() {
        HobbyDto incompleteDto = new HobbyDto();
        incompleteDto.setId(ids[0]);
        return incompleteDto;
    }
}
