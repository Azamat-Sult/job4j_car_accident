package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccidentService {

    private Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    public void addAccident(Accident accident, String[] rIds) {
        accident.setType(store.getAccidentTypeById(accident.getType().getId()));
        Set<Rule> newSet = new HashSet<>();
        for (String id : rIds) {
            newSet.add(store.getAccidentRuleById(Integer.parseInt(id)));
        }
        accident.setRules(newSet);
        store.addAccident(accident);
    }

    public Accident getAccidentById(int id) {
        return store.getAccidentById(id);
    }

    public Collection<Accident> getAllAccidents() {
        return store.getAllAccidents();
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        return store.getAllAccidentTypes();
    }

    public Collection<Rule> getAllAccidentRules() {
        return store.getAllAccidentRules();
    }

    public void deleteAccident(int id) {
        store.deleteAccident(id);
    }
}