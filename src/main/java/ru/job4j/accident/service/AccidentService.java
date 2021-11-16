package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public interface AccidentService {

    void addAccident(Accident accident, String[] rIds);

    Accident getAccidentById(int id);

    Collection<Accident> getAllAccidents();

    Collection<AccidentType> getAllAccidentTypes();

    Collection<Rule> getAllAccidentRules();

    void deleteAccident(int id);

}