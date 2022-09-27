package de.brockhausag.diversitylunchspringboot.profile.logic;
import de.brockhausag.diversitylunchspringboot.profile.data.LanguageRepository;
import de.brockhausag.diversitylunchspringboot.profile.model.entities.LanguageEntity;
import de.brockhausag.diversitylunchspringboot.profile.utils.baseApi.GenericBaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class LanguageService extends GenericBaseEntityService<LanguageEntity, LanguageRepository> {
    public LanguageService(LanguageRepository repository) {
        super(repository);
    }
}