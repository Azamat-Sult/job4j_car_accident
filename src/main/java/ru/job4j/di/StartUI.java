package ru.job4j.di;

public class StartUI {
    private final Store store;
    private final ConsoleInput input;

    public StartUI(Store store, ConsoleInput input) {
        this.store = store;
        this.input = input;
    }

    public void add(String value) {
        store.add(value);
    }

    public void consoleInput(String question) {
        store.add(input.askStr(question));
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }

}