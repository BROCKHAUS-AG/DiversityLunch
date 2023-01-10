package de.brockhausag.diversitylunchspringboot.meeting.repository;

import de.brockhausag.diversitylunchspringboot.meeting.model.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity, Long> {
    List<QuestionEntity> getAllByCategoryId(Long categoryId);
}
