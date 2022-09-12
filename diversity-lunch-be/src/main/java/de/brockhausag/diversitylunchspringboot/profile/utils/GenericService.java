package de.brockhausag.diversitylunchspringboot.profile.utils;

import org.springframework.data.repository.CrudRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GenericService <RepositoryType extends CrudRepository<EntityType, Long>, EntityType extends BaseEntity> {

    private final RepositoryType repository;

    public GenericService(RepositoryType repository) {

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
        List<EntityType> result = Collections.emptyList();
        repository.findAll().forEach(result::add);
        return result;
    }

}
