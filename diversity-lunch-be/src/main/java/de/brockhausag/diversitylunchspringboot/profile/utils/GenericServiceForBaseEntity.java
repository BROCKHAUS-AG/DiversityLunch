package de.brockhausag.diversitylunchspringboot.profile.utils;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class GenericServiceForBaseEntity<RepositoryType extends CrudRepository<EntityType, Long>, EntityType extends BaseEntity> {

    private final RepositoryType repository;

    public GenericServiceForBaseEntity(RepositoryType repository) {
        this.repository = repository;
    }

    public EntityType createEntity(EntityType entity){
        //set id to null to force auto generation of id and thus avoid updating existing entries
        entity.setId(0L);
        return repository.save(entity);
    }

    public Optional<EntityType> getEntityById(Long id){
        return repository.findById(id);
    }

    public EntityType updateOrCreateEntity(EntityType entity){
        return repository.save(entity);
    }

    public boolean deleteEntityById(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<EntityType> getAllEntities(){
        Iterable<EntityType> entitiesIterable = repository.findAll();
        return StreamSupport
                .stream(entitiesIterable.spliterator(), false)
                .collect(Collectors.toList());
    }

}
