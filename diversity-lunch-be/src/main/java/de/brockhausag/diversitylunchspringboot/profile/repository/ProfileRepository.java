package de.brockhausag.diversitylunchspringboot.profile.repository;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    @Query("SELECT profile FROM ProfileEntity profile join profile.selectedBasicValues basicValues WHERE basicValues = :option")
    List<ProfileEntity> findALLBySelectedBasicValues(BasicDimensionSelectableOption option);
    @Query("SELECT profile FROM ProfileEntity profile join profile.selectedWeightedValues weightedValues WHERE weightedValues = :option")
    List<ProfileEntity> findALLBySelectedWeightedValues(WeightedDimensionSelectableOption option);
    @Query("SELECT profile FROM ProfileEntity profile join profile.selectedMultiselectValues multiselect join multiselect.selectedOptions multiselectValues WHERE multiselectValues = :option")
    List<ProfileEntity> findALLBySelectedMultiselectValues(MultiselectDimensionSelectableOption option);
}
