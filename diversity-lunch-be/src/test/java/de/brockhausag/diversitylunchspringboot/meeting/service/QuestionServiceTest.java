package de.brockhausag.diversitylunchspringboot.meeting.service;

import de.brockhausag.diversitylunchspringboot.dimensions.entities.model.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.dimensions.repositories.DimensionCategoryRepository;
import de.brockhausag.diversitylunchspringboot.meeting.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionService questionService;
    @Mock
    private DimensionCategoryRepository categoryRepository;


    @Test
    void getQuestionForCategory_returnsEmptyListIfNoCategoryIsFound() {
        // Arrange
        DimensionCategory input = new DimensionCategory();
        when(categoryRepository.getDimensionCategoryByDescription(any())).thenReturn(Optional.empty());

        // Act
        var actual = questionService.getQuestionsForCategory(input);

        // Assert
        assertEquals(0, actual.size());
        verify(questionRepository, times(1)).getAllByCategoryId(any());
    }
}
