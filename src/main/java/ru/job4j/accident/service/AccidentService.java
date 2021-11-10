package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.HashMap;

@Service
public class AccidentService {
    private static final class Lazy {
        private static final AccidentService INST = new AccidentService();
    }

    public static AccidentService instOf() {
        return AccidentService.Lazy.INST;
    }

    public HashMap<Integer, Accident> getAllAccidents() {
        AccidentMem.instOf().initAccidentMem();
        return AccidentMem.instOf().getAllAccidents();
    }
}
