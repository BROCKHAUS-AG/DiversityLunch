package de.brockhausag.diversitylunchspringboot.meeting.service;

import de.brockhausag.diversitylunchspringboot.generics.dimensionCategory.DimensionCategoryRepository;
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
    private DimensionCategoryRepository categoryRepository;

    @InjectMocks
    private QuestionService questionService;

    @Test
    void getQuestionForCategory_returnsEmptyListIfNoCategoryIsFound() {
        when(categoryRepository.getDimensionCategoryEntityByDescriptor(any())).thenReturn(Optional.empty());

        var actual = questionService.getQuestionsForCategory("");

        assertEquals(0, actual.size());
        verify(categoryRepository, times(1)).getDimensionCategoryEntityByDescriptor(any());
    }
}