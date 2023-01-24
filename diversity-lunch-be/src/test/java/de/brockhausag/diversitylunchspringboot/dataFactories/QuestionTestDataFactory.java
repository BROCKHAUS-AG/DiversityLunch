package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.generics.dimensionCategory.DimensionCategoryEntity;
import de.brockhausag.diversitylunchspringboot.meeting.model.QuestionEntity;

public class QuestionTestDataFactory {
    public QuestionEntity buildEntity() {
        return buildEntity("Test-Question");
    }

    public QuestionEntity buildEntity(String questionText, DimensionCategoryEntity dimensionCategory) {
        var question = new QuestionEntity();

        question.setId(1L);
        question.setQuestionText(questionText);
        question.setCategory(dimensionCategory);

        return question;
    }

    public QuestionEntity buildEntity(String questionText) {
        var dimensionCategory = new DimensionCategoryEntity();
        dimensionCategory.setDescriptor("Test-Category");

        return buildEntity(questionText, dimensionCategory);
    }

    public QuestionEntity buildEntity(DimensionCategoryEntity dimensionCategory) {
        return buildEntity("Test-Question", dimensionCategory);
    }

    public QuestionEntity buildEntity(String questionText, String dimensionCategory) {
        DimensionCategoryEntity category = new DimensionCategoryTestDataFactory().buildEntity(dimensionCategory);
        return buildEntity(questionText, category);
    }
}
