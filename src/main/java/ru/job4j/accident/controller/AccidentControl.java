package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {

    private final AccidentService accidentService;

    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.getAllAccidentTypes());
        model.addAttribute("rules", accidentService.getAllAccidentRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ruleIds = req.getParameterValues("rIds");
        accidentService.addAccident(accident, ruleIds);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidentService.getAllAccidentTypes());
        model.addAttribute("rules", accidentService.getAllAccidentRules());
        model.addAttribute("accident", accidentService.getAccidentById(id));
        return "accident/update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        accidentService.deleteAccident(id);
        return "redirect:/";
    }
}