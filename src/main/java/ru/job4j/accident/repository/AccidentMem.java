package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*@Repository*/
public class AccidentMem implements Store {

    private HashMap<Integer, AccidentType> accidentTypes = new HashMap<>();

    private HashMap<Integer, Accident> accidents = new HashMap<>();

    private HashMap<Integer, Rule> rules = new HashMap<>();

    private int accidentsCounter = 1;

    public AccidentMem() {
        init();
    }

    @Override
    public void addAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(accidentsCounter++);
        }
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

    private void addAccidentType(AccidentType accidentType) {
        accidentTypes.put(accidentType.getId(), accidentType);
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return accidentTypes.values();
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return accidentTypes.get(id);
    }

    private void addAccidentRule(Rule rule) {
        rules.put(rule.getId(), rule);
    }

    @Override
    public Collection<Rule> getAllAccidentRules() {
        return rules.values();
    }

    @Override
    public Rule getAccidentRuleById(int id) {
        return rules.get(id);
    }

    private void init() {
        if (accidentTypes.isEmpty()) {
            AccidentType accidentType1 = AccidentType.of(1, "Две машины");
            AccidentType accidentType2 = AccidentType.of(2, "Машина и человек");
            AccidentType accidentType3 = AccidentType.of(3, "Машина и велосипед");
            addAccidentType(accidentType1);
            addAccidentType(accidentType2);
            addAccidentType(accidentType3);
        }

        if (rules.isEmpty()) {
            Rule rule1 = Rule.of(1, "КоАП. Статья 1");
            Rule rule2 = Rule.of(2, "КоАП. Статья 2");
            Rule rule3 = Rule.of(3, "КоАП. Статья 3");
            Rule rule4 = Rule.of(4, "КоАП. Статья 4");
            addAccidentRule(rule1);
            addAccidentRule(rule2);
            addAccidentRule(rule3);
            addAccidentRule(rule4);
        }

        if (accidents.isEmpty()) {
            Set<Rule> set1 = new HashSet<>();
            set1.add(getAccidentRuleById(1));
            set1.add(getAccidentRuleById(2));
            Accident accident1 = new Accident(0, "name 1", "description 1",
                    "adress 1", getAccidentTypeById(1), set1);

            Set<Rule> set2 = new HashSet<>();
            set2.add(getAccidentRuleById(1));
            set2.add(getAccidentRuleById(3));
            Accident accident2 = new Accident(0, "name 2", "description 2",
                    "adress 2", getAccidentTypeById(2), set2);

            Set<Rule> set3 = new HashSet<>();
            set3.add(getAccidentRuleById(1));
            set3.add(getAccidentRuleById(4));
            Accident accident3 = new Accident(0, "name 3", "description 3",
                    "adress 3", getAccidentTypeById(3), set3);

            Set<Rule> set4 = new HashSet<>();
            set4.add(getAccidentRuleById(2));
            set4.add(getAccidentRuleById(3));
            Accident accident4 = new Accident(0, "name 4", "description 4",
                    "adress 4", getAccidentTypeById(1), set4);

            Set<Rule> set5 = new HashSet<>();
            set5.add(getAccidentRuleById(2));
            set5.add(getAccidentRuleById(4));
            Accident accident5 = new Accident(0, "name 5", "description 5",
                    "adress 5", getAccidentTypeById(2), set5);

            Set<Rule> set6 = new HashSet<>();
            set6.add(getAccidentRuleById(3));
            set6.add(getAccidentRuleById(4));
            Accident accident6 = new Accident(0, "name 6", "description 6",
                    "adress 6", getAccidentTypeById(3), set6);
            addAccident(accident1);
            addAccident(accident2);
            addAccident(accident3);
            addAccident(accident4);
            addAccident(accident5);
            addAccident(accident6);
        }
    }
}