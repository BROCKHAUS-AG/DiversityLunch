package de.brockhausag.diversitylunchspringboot.meeting.service;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategoryRepository;
import de.brockhausag.diversitylunchspringboot.meeting.model.QuestionEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final DimensionCategoryRepository dimensionCategoryRepository;

    public List<QuestionEntity> getQuestionsForCategory (String categoryDescriptor) {
        var dimensionCategory = dimensionCategoryRepository.getDimensionCategoryEntityByDescriptor(categoryDescriptor);
        var questionList = new ArrayList<QuestionEntity>();

        if (dimensionCategory.isPresent())  {
            questionList.addAll(questionRepository.getAllByCategoryId(dimensionCategory.get().getId()));
        } else {
            log.warn("Could not find Category for Identifier " + categoryDescriptor);
        }

        return questionList;
    }
}
