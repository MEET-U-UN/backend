package co.edu.unal.software_engineering.meetu.service;

import co.edu.unal.software_engineering.meetu.model.Option;
import co.edu.unal.software_engineering.meetu.repository.OptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OptionService {

    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository){
        this.optionRepository = optionRepository;
    }

    public void save( Option option ){
        optionRepository.save( option );
    }

}
