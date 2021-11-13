package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;

import java.util.Collection;

public interface Store {

    void addAccident(Accident accident);

    Collection<Accident> getAllAccidents();

    Accident getAccidentById(int id);

    void deleteAccident(int id);

    void init();

}