package de.brockhausag.diversitylunchspringboot.profile.utils.baseApi;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String descriptor;

    public int hashCode() {
        String hashString = this.id.toString() + this.descriptor;
        return hashString.hashCode() ;
    }

}
