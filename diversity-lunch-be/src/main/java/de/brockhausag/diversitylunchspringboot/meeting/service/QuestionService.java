package de.brockhausag.diversitylunchspringboot.meeting.service;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.meeting.model.QuestionEntity;
import de.brockhausag.diversitylunchspringboot.meeting.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<QuestionEntity> getQuestionsForCategory (DimensionCategory category) {
        return questionRepository.getAllByCategoryId(category.getId());
    }
}
