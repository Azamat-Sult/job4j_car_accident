package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

public interface Store {

    void addAccident(Accident accident);

    Collection<Accident> getAllAccidents();

    Accident getAccidentById(int id);

    void deleteAccident(int id);

    Collection<AccidentType> getAllAccidentTypes();

    AccidentType getAccidentTypeById(int id);

    void init();

}