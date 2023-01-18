package de.brockhausag.diversitylunchspringboot.profile.data;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    @Query("SELECT p FROM profile_entity AS p join profile_entity_selected_basic_values AS v on p.id=v.profile_entity_id WHERE v.selected_basic_values_id=?1")
    List<ProfileEntity> findALLBySelectedBasicValuesId(Long optionId);
}
