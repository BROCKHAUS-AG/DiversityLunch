package de.brockhausag.diversitylunchspringboot.profile.logic;
import de.brockhausag.diversitylunchspringboot.profile.data.LanguageRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.LanguageEntity;
import de.brockhausag.diversitylunchspringboot.generics.BasicDimension.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class LanguageService extends BaseEntityService<LanguageEntity, LanguageRepository> {
    public LanguageService(LanguageRepository repository) {
        super(repository);
    }
}
