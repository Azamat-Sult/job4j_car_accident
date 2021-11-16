package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @EntityGraph(attributePaths = { "type", "rules" })
    Collection<Accident> findAll();

    @EntityGraph(attributePaths = { "type", "rules" })
    Accident findById(int id);

}