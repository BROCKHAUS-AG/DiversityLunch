package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.dataFactories.dimension.*;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.BasicDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.MultiselectDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.ProfileEntitySelectedMultiselectValue;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimension;
import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.WeightedDimensionSelectableOption;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.ProfileEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProfileTestdataFactory {

    public ProfileEntity buildEntity(int setNumber) {
        ProfileEntity result;
        switch (setNumber) {
            case 1:
                result = ProfileEntity.builder()
                        .id(1L)
                        .name("First User")
                        .email("first.mail@some.tld")
                        .birthYear(1957)
                        .selectedBasicValues(getBasicSelectedOptions(setNumber))
                        .selectedWeightedValues(getWeightedSelectedOptions(setNumber))
                        .selectedMultiselectValues(getMultiselectSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
                break;
            case 2:
                result = ProfileEntity.builder()
                        .id(2L)
                        .name("Second User")
                        .email("second.mail@some.tld")
                        .birthYear(2001)
                        .selectedBasicValues(getBasicSelectedOptions(setNumber))
                        .selectedWeightedValues(getWeightedSelectedOptions(setNumber))
                        .selectedMultiselectValues(getMultiselectSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
                break;
            case 3:
                result = ProfileEntity.builder()
                        .id(3L)
                        .name("Third User")
                        .email("third.mail@some.tld")
                        .birthYear(1969)
                        .selectedBasicValues(getBasicSelectedOptions(setNumber))
                        .selectedWeightedValues(getWeightedSelectedOptions(setNumber))
                        .selectedMultiselectValues(getMultiselectSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
                break;
            default:
                result = ProfileEntity.builder()
                        .id(10L)
                        .name("Default User")
                        .email("default.mail@some.tld")
                        .birthYear(2000)
                        .selectedBasicValues(getBasicSelectedOptions(setNumber))
                        .selectedWeightedValues(getWeightedSelectedOptions(setNumber))
                        .selectedMultiselectValues(getMultiselectSelectedOptions(setNumber))
                        .wasChangedByAdmin(false)
                        .build();
        }
        return result;
    }

    private Map<BasicDimension, BasicDimensionSelectableOption> getBasicSelectedOptions(int setNumber) {
        BasicDimensionTestDataFactory basicFactory = new BasicDimensionTestDataFactory();
        BasicSelectableOptionTestDataFactory basicSelectableFactory = new BasicSelectableOptionTestDataFactory();
        Set<BasicDimension> basicDimensions = basicFactory.buildEntities(2);
        Map<BasicDimension, BasicDimensionSelectableOption> result = new HashMap<>();
        int selectedValue = 0;

        for (BasicDimension d : basicDimensions) {
            switch (setNumber) {
                case 1:
                    selectedValue = 1;
                    break;
                case 2:
                    selectedValue = 3;
                    break;
                case 3:
                    selectedValue++;
                    break;
                default:
            }
            result.put(d, basicSelectableFactory.buildEntity(d.getDimensionCategory(), selectedValue));
        }
        return result;
    }

    private Map<WeightedDimension, WeightedDimensionSelectableOption> getWeightedSelectedOptions(int setNumber) {
        WeightedDimensionTestDataFactory WeightedFactory = new WeightedDimensionTestDataFactory();
        WeightedSelectableOptionTestDataFactory WeightedSelectableFactory = new WeightedSelectableOptionTestDataFactory();
        Set<WeightedDimension> WeightedDimensions = WeightedFactory.buildEntities(2);
        Map<WeightedDimension, WeightedDimensionSelectableOption> result = new HashMap<>();
        int selectedValue = 0;

        for (WeightedDimension d : WeightedDimensions) {
            switch (setNumber) {
                case 1:
                    selectedValue = 1;
                    break;
                case 2:
                    selectedValue = 3;
                    break;
                case 3:
                    selectedValue++;
                    break;
                default:
            }
            result.put(d, WeightedSelectableFactory.buildEntity(d.getDimensionCategory(), selectedValue));
        }
        return result;
    }
    private Map<MultiselectDimension, ProfileEntitySelectedMultiselectValue> getMultiselectSelectedOptions(int setNumber) {
        MultiselectDimensionTestDataFactory MultiselectFactory = new MultiselectDimensionTestDataFactory();
        MultiselectSelectableOptionTestDataFactory MultiselectSelectableFactory = new MultiselectSelectableOptionTestDataFactory();
        Set<MultiselectDimension> MultiselectDimensions = MultiselectFactory.buildEntities(2);
        Map<MultiselectDimension, ProfileEntitySelectedMultiselectValue> result = new HashMap<>();

        for (MultiselectDimension d : MultiselectDimensions) {
            Set<MultiselectDimensionSelectableOption> selectedOptions = new HashSet<>();
            switch (setNumber) {
                case 1:
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 1));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 2));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 3));
                    break;
                case 2:
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 4));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 5));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 6));
                    break;
                case 3:
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 3));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 4));
                    selectedOptions.add(MultiselectSelectableFactory.buildEntity(d.getDimensionCategory(), 5));
                    break;
                default:
            }
            ProfileEntitySelectedMultiselectValue selectedMultiselectValue = new ProfileEntitySelectedMultiselectValue();
            selectedMultiselectValue.setSelectedOptions(selectedOptions);
            result.put(d, selectedMultiselectValue);
        }
        return result;
    }

}