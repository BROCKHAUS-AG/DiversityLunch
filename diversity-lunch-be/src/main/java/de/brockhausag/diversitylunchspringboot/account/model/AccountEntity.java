package de.brockhausag.diversitylunchspringboot.account.model;

import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {

    @GeneratedValue
    @Id
    private long id;

    @OneToOne
    private ProfileEntity profile;

    private String uniqueName;
}
