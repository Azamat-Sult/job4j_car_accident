package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();
        StartUI uiFirst = context.getBean(StartUI.class);
        uiFirst.add("Petr Arsentev");
        System.out.print("First bean: ");
        uiFirst.print();
        StartUI uiSecond = context.getBean(StartUI.class);
        uiSecond.add("Ivan ivanov");
        System.out.print("Second bean: ");
        uiSecond.print();
    }
}