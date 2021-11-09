package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("String " + i);
        }
        model.addAttribute("strings", strings);
        return "index";
    }
}