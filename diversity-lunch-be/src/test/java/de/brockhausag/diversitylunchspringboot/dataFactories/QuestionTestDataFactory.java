package de.brockhausag.diversitylunchspringboot.dataFactories;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.meeting.model.QuestionEntity;

public class QuestionTestDataFactory {
    public QuestionEntity buildEntity() {
        return buildEntity("Test-Question");
    }

    public QuestionEntity buildEntity(String questionText, DimensionCategory dimensionCategory) {
        var question = new QuestionEntity();

        question.setId(1L);
        question.setQuestionText(questionText);
        question.setCategory(dimensionCategory);

        return question;
    }

    public QuestionEntity buildEntity(String questionText) {
        var dimensionCategory = DimensionCategory.builder().id(1L).description("test-category").profileQuestion("test-question").build();

        return buildEntity(questionText, dimensionCategory);
    }

    public QuestionEntity buildEntity(DimensionCategory dimensionCategory) {
        return buildEntity("Test-Question", dimensionCategory);
    }

    public QuestionEntity buildEntity(String questionText, String dimensionCategory) {
        DimensionCategory category = DimensionCategory.builder().id(1L).description("dimensionCategory").profileQuestion(dimensionCategory + "?").build();
        return buildEntity(questionText, category);
    }
}
