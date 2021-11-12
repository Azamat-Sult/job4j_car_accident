package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class AccidentMem implements Store {

    private HashMap<Integer, Accident> accidents = new HashMap<>();

    @Override
    public void addAccident(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return accidents.values();
    }

    @Override
    public void init() {
        if (accidents.isEmpty()) {
            Accident accident1 = new Accident(1, "name 1", "description 1", "adress 1");
            Accident accident2 = new Accident(2, "name 2", "description 2", "adress 2");
            Accident accident3 = new Accident(3, "name 3", "description 3", "adress 3");
            Accident accident4 = new Accident(4, "name 4", "description 4", "adress 4");
            addAccident(accident1);
            addAccident(accident2);
            addAccident(accident3);
            addAccident(accident4);
        }
    }
}