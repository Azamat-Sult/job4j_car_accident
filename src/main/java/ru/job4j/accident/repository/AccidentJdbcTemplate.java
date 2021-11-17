package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate implements Store {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void addAccident(Accident accident) {
        if (accident.getId() == 0) {
            addNewAccident(accident);
        } else {
            updateAccident(accident);
        }
    }

    private void addNewAccident(Accident accident) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbc.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(
                        "insert into accidents (name, text, address, accidentType_id)"
                                + " values (?, ?, ?, ?)",
                        new String[] {"id"});
                statement.setString(1, accident.getName());
                statement.setString(2, accident.getText());
                statement.setString(3, accident.getAddress());
                statement.setInt(4, accident.getType().getId());
                return statement;
            }
        }, holder);
        long primaryKey = holder.getKey().longValue();
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accidents_accidentrules (accident_id, rules_id) values (?, ?)",
                    primaryKey, rule.getId());
        }
    }

    private void updateAccident(Accident accident) {
        jdbc.update("update accidents set name = ?, text = ?,"
                        + " address = ?, accidentType_id = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId());
        jdbc.update("delete from accidents_accidentrules where accident_id = ?",
                Long.valueOf(accident.getId()));
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accidents_accidentrules (accident_id, rules_id) values (?, ?)",
                    accident.getId(), rule.getId());
        }
    }

    @Override
    public Accident getAccidentById(int id) {
        return jdbc.queryForObject(
                "select * from accidents as a join accidentTypes as atypes"
                        + " on a.accidentType_id = atypes.id where a.id = ?",
                accidentRowMapper, id);
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return jdbc.query("select * from accidents as a join accidentTypes as atypes"
                        + " on a.accidentType_id = atypes.id", accidentRowMapper);
    }

    private final RowMapper<Accident> accidentRowMapper = (resultSet, rowNum) -> {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("id"));
        accident.setName(resultSet.getString("name"));
        accident.setText(resultSet.getString("text"));
        accident.setAddress(resultSet.getString("address"));
        accident.setType(AccidentType.of(resultSet.getInt("accidentType_id"),
                resultSet.getString("type_name")));
        accident.setRules(getAccidentsRulesById(accident.getId()));
        return accident;
    };

    @Override
    public void deleteAccident(int id) {
        jdbc.update("delete from accidents_accidentrules where accident_id = ?", Long.valueOf(id));
        jdbc.update("delete from accidents where id = ?", Long.valueOf(id));
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return jdbc.queryForObject(
                "select * from accidentTypes where id = ?",
                (resultSet, rowNum) -> {
                    int dbId = Integer.parseInt(resultSet.getString("id"));
                    String typeName = resultSet.getString("type_name");
                    return AccidentType.of(dbId, typeName);
                }, id);
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return jdbc.query(
                "select * from accidentTypes",
                (resultSet, rowNum) -> {
                    int dbId = resultSet.getInt("id");
                    String typeName = resultSet.getString("type_name");
                    return AccidentType.of(dbId, typeName);
                });
    }

    @Override
    public Rule getAccidentRuleById(int id) {
        return jdbc.queryForObject(
                "select * from accidentRules where id = ?",
                (resultSet, rowNum) -> {
                    int dbId = Integer.parseInt(resultSet.getString("id"));
                    String ruleName = resultSet.getString("name");
                    return Rule.of(dbId, ruleName);
                }, id);
    }

    @Override
    public Collection<Rule> getAllAccidentRules() {
        return jdbc.query(
                "select * from accidentRules",
                (resultSet, rowNum) -> {
                    int dbId = resultSet.getInt("id");
                    String ruleName = resultSet.getString("name");
                    return Rule.of(dbId, ruleName);
                });
    }

    private Set<Rule> getAccidentsRulesById(int id) {
        Collection<Rule> rulesFound = jdbc.query(
                "select * from accidents_accidentrules where accident_id = ?",
                (resultSet, rowNum) -> {
                    return getAccidentRuleById(resultSet.getInt("rules_id"));
                }, id);
        Set<Rule> rules = new HashSet<>();
        for (Rule rule : rulesFound) {
            rules.add(rule);
        }
        return rules;
    }

}