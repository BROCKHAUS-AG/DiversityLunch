package de.brockhausag.diversitylunchspringboot.profile.logic;

import de.brockhausag.diversitylunchspringboot.profile.data.HobbyRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.HobbyEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class HobbyService {
    private final HobbyRepository repository;
    
    public HobbyEntity createEntity(HobbyEntity entity){
        //set id to null to force auto generation of id thus avoid updating existing entries
        entity.setId(0L);
        return repository.save(entity);
    }

    public Optional<HobbyEntity> getEntityById(Long id){
        return repository.findById(id);
    }

    public HobbyEntity updateOrCreateEntity(HobbyEntity entity){
        return repository.save(entity);
    }

    public boolean deleteEntityById(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<HobbyEntity> getAllEntities(){
        Iterable<HobbyEntity> entitiesIterable = repository.findAll();
        return StreamSupport
                .stream(entitiesIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public boolean existsById(Long id){
        return this.repository.existsById(id);
    }
    
}
