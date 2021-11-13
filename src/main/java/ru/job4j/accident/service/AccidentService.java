package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.Store;

import java.util.Collection;

@Service
public class AccidentService {

    private Store store;

    public AccidentService(Store store) {
        this.store = store;
    }

    public void addAccident(Accident accident) {
        store.addAccident(accident);
    }

    public Accident getAccidentById(int id) {
        return store.getAccidentById(id);
    }

    public Collection<Accident> getAllAccidents() {
        store.init();
        return store.getAllAccidents();
    }

    public void deleteAccident(int id) {
        store.deleteAccident(id);
    }
}