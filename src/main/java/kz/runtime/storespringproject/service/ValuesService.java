package kz.runtime.storespringproject.service;


import kz.runtime.storespringproject.entities.Values;
import kz.runtime.storespringproject.repos.ValuesRepository;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ValuesService {
    private final ValuesRepository valuesRepository;

    public ValuesService(ValuesRepository valuesRepository) {
        this.valuesRepository = valuesRepository;
    }

    public List<Values> findValuesByOptionId(Long id) {
        return valuesRepository.findValuesByOptionId(id);
    }
}
