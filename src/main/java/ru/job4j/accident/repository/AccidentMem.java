package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {
    private static final class Lazy {
        private static final AccidentMem INST = new AccidentMem();
    }

    public static AccidentMem instOf() {
        return AccidentMem.Lazy.INST;
    }

    private HashMap<Integer, Accident> accidents = new HashMap<>();

    public void initAccidentMem() {
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

    public void addAccident(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public HashMap<Integer, Accident> getAllAccidents() {
        return accidents;
    }
}