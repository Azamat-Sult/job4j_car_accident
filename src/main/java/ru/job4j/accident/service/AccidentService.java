package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.Store;

import java.util.Collection;

@Service
public class AccidentService {

    private Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    public void addAccident(Accident accident) {
        accident.setType(store.getAccidentTypeById(accident.getType().getId()));
        store.addAccident(accident);
    }

    public Accident getAccidentById(int id) {
        return store.getAccidentById(id);
    }

    public Collection<Accident> getAllAccidents() {
        store.init();
        return store.getAllAccidents();
    }

    public Collection<AccidentType> getAllAccidentTypes() {
        store.init();
        return store.getAllAccidentTypes();
    }

    public void deleteAccident(int id) {
        store.deleteAccident(id);
    }
}