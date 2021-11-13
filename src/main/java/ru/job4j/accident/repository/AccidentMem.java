package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class AccidentMem implements Store {

    private HashMap<Integer, AccidentType> accidentTypes = new HashMap<>();

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
    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }

    @Override
    public void deleteAccident(int id) {
        accidents.remove(id);
    }

    public void addAccidentType(AccidentType accidentType) {
        accidentTypes.put(accidentType.getId(), accidentType);
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return accidentTypes.values();
    }

    public AccidentType getAccidentTypeById(int id) {
        return accidentTypes.get(id);
    }

    @Override
    public void init() {
        if (accidentTypes.isEmpty()) {
            AccidentType accidentType1 = AccidentType.of(1, "Две машины");
            AccidentType accidentType2 = AccidentType.of(2, "Машина и человек");
            AccidentType accidentType3 = AccidentType.of(3, "Машина и велосипед");
            addAccidentType(accidentType1);
            addAccidentType(accidentType2);
            addAccidentType(accidentType3);
        }

        if (accidents.isEmpty()) {
            Accident accident1 = new Accident(1, "name 1", "description 1", "adress 1", getAccidentTypeById(1));
            Accident accident2 = new Accident(2, "name 2", "description 2", "adress 2", getAccidentTypeById(2));
            Accident accident3 = new Accident(3, "name 3", "description 3", "adress 3", getAccidentTypeById(3));
            Accident accident4 = new Accident(4, "name 4", "description 4", "adress 4", getAccidentTypeById(1));
            Accident accident5 = new Accident(5, "name 5", "description 5", "adress 5", getAccidentTypeById(2));
            Accident accident6 = new Accident(6, "name 6", "description 6", "adress 6", getAccidentTypeById(3));
            addAccident(accident1);
            addAccident(accident2);
            addAccident(accident3);
            addAccident(accident4);
            addAccident(accident5);
            addAccident(accident6);
        }
    }
}