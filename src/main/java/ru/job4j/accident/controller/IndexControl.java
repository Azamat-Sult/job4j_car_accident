package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        AccidentService service = new AccidentService(new AccidentMem());
        model.addAttribute("accidents", service.getAllAccidents());
        return "index";
    }
}