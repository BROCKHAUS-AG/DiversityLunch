package de.brockhausag.diversitylunchspringboot.generics.dimension;

import de.brockhausag.diversitylunchspringboot.meeting.model.Category;

public interface DimensionEntity {

    Long getId();
    void setId(Long id);
    String getDescriptor();
    void setDescriptor(String descriptor);
    Category getQuestionCategory();
}
