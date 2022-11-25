package de.brockhausag.diversitylunchspringboot.generics.BasicDimension;

import de.brockhausag.diversitylunchspringboot.meeting.model.Category;

public interface BaseEntity {
    Long getId();
    void setId(Long id);
    String getDescriptor();
    void setDescriptor(String descriptor);
    Category getQuestionCategory();
}
