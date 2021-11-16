package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.Store;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*@Service*/
public class JdbcAndHbmService implements AccidentService {

    private Store store;

    public JdbcAndHbmService(Store store) {
        this.store = store;
    }

    @Override
    public void addAccident(Accident accident, String[] rIds) {
        accident.setType(store.getAccidentTypeById(accident.getType().getId()));
        Set<Rule> newSet = new HashSet<>();
        for (String id : rIds) {
            newSet.add(store.getAccidentRuleById(Integer.parseInt(id)));
        }
        accident.setRules(newSet);
        store.addAccident(accident);
    }

    @Override
    public Accident getAccidentById(int id) {
        return store.getAccidentById(id);
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return store.getAllAccidents();
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return store.getAllAccidentTypes();
    }

    @Override
    public Collection<Rule> getAllAccidentRules() {
        return store.getAllAccidentRules();
    }

    @Override
    public void deleteAccident(int id) {
        store.deleteAccident(id);
    }
}