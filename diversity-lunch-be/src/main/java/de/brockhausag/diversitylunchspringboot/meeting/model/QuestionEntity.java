package de.brockhausag.diversitylunchspringboot.meeting.model;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DimensionCategory category;
    @NotNull
    private String questionText;
}