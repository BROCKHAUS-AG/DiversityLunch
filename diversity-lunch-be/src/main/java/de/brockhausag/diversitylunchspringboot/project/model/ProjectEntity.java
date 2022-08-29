package de.brockhausag.diversitylunchspringboot.project.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String descriptor;
}
