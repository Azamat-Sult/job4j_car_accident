package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class SpringDataService implements AccidentService {

    private AccidentRepository accidentRepository;
    private AccidentTypeRepository accidentTypeRepository;
    private RuleRepository ruleRepository;

    @Autowired
    public SpringDataService(AccidentRepository accidentRepository,
                             AccidentTypeRepository accidentTypeRepository,
                             RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public void addAccident(Accident accident, String[] rIds) {
        Set<Rule> newSet = new HashSet<>();
        for (String id : rIds) {
            newSet.add(ruleRepository.findById(Integer.parseInt(id)));
        }
        accident.setRules(newSet);
        accident.setType(accidentTypeRepository.findById(accident.getType().getId()));
        accidentRepository.save(accident);
    }

    @Override
    public Accident getAccidentById(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return accidentRepository.findAll();
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return accidentTypeRepository.findAll();
    }

    @Override
    public Collection<Rule> getAllAccidentRules() {
        return ruleRepository.findAll();
    }

    @Override
    public void deleteAccident(int id) {
        accidentRepository.deleteById(id);
    }
}