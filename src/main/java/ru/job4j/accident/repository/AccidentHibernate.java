package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

/*@Repository*/
public class AccidentHibernate implements Store {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void addAccident(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(accident);
            session.getTransaction().commit();
        }
    }

    @Override
    public Accident getAccidentById(int id) {
        try (Session session = sf.openSession()) {
            String hql = "select distinct a from Accident a "
                    + "join fetch a.type "
                    + "join fetch a.rules "
                    + " where a.id = :id";
            Query hqlQuery = session.createQuery(hql);
            hqlQuery.setParameter("id", id);
            return (Accident) hqlQuery.uniqueResult();
        }
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        try (Session session = sf.openSession()) {
            String sql = "select distinct a from Accident a "
                    + "join fetch a.type "
                    + "join fetch a.rules ";
            return session.createQuery(sql, Accident.class).list();
        }
    }

    @Override
    public void deleteAccident(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            String sql = "delete from Accident where id=:id";
            final Query query = session.createQuery(sql);
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        try (Session session = sf.openSession()) {
            String hql = "select distinct at from AccidentType at "
                    + " where at.id = :id";
            Query hqlQuery = session.createQuery(hql);
            hqlQuery.setParameter("id", id);
            return (AccidentType) hqlQuery.uniqueResult();
        }
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        try (Session session = sf.openSession()) {
            String sql = "from AccidentType";
            return session.createQuery(sql, AccidentType.class).list();
        }
    }

    @Override
    public Rule getAccidentRuleById(int id) {
        try (Session session = sf.openSession()) {
            String hql = "select distinct r from Rule r "
                    + " where r.id = :id";
            Query hqlQuery = session.createQuery(hql);
            hqlQuery.setParameter("id", id);
            return (Rule) hqlQuery.uniqueResult();
        }
    }

    @Override
    public Collection<Rule> getAllAccidentRules() {
        try (Session session = sf.openSession()) {
            String sql = "from Rule";
            return session.createQuery(sql, Rule.class).list();
        }
    }
}