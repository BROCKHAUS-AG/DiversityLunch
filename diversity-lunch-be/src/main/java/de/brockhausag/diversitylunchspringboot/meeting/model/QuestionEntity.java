package de.brockhausag.diversitylunchspringboot.meeting.model;

import de.brockhausag.diversitylunchspringboot.generics.dimensionCategory.DimensionCategoryEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private DimensionCategoryEntity category;

    @NotNull
    private String questionText;
}
