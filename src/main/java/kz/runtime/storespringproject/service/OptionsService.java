package kz.runtime.storespringproject.service;


import kz.runtime.storespringproject.entities.Options;
import kz.runtime.storespringproject.repos.OptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OptionsService {
    private final OptionRepository optionRepository;

    public OptionsService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public Set<Options> findOptionsById(Long id) {
        return optionRepository.findOptionsByCategoryId(id);
    }

}
