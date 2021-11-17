package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.function.Function;

/*@Repository*/
public class AccidentHibernate implements Store {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void addAccident(Accident accident) {
        this.tx(
                session -> {
                    session.saveOrUpdate(accident);
                    return accident;
                }
        );
    }

    @Override
    public Accident getAccidentById(int id) {
        return this.tx(
                session -> {
                    String hql = "select distinct a from Accident a "
                            + "join fetch a.type "
                            + "join fetch a.rules "
                            + " where a.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return (Accident) hqlQuery.uniqueResult();
                });
    }

    @Override
    public Collection<Accident> getAllAccidents() {
        return this.tx(
                session -> {
                    String hql = "select distinct a from Accident a "
                            + "join fetch a.type "
                            + "join fetch a.rules ";
                    return session.createQuery(hql, Accident.class).list();
                });
    }

    @Override
    public void deleteAccident(int id) {
        this.tx(
                session -> {
                    String hql = "delete from Accident where id=:id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return hqlQuery.executeUpdate();
                }
        );
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return this.tx(
                session -> {
                    String hql = "select distinct at from AccidentType at "
                            + " where at.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return (AccidentType) hqlQuery.uniqueResult();
                });
    }

    @Override
    public Collection<AccidentType> getAllAccidentTypes() {
        return this.tx(
                session -> {
                    String hql = "from AccidentType";
                    return session.createQuery(hql, AccidentType.class).list();
                });
    }

    @Override
    public Rule getAccidentRuleById(int id) {
        return this.tx(
                session -> {
                    String hql = "select distinct r from Rule r "
                            + " where r.id = :id";
                    Query hqlQuery = session.createQuery(hql);
                    hqlQuery.setParameter("id", id);
                    return (Rule) hqlQuery.uniqueResult();
                });
    }

    @Override
    public Collection<Rule> getAllAccidentRules() {
        return this.tx(
                session -> {
                    String hql = "from Rule";
                    return session.createQuery(hql, Rule.class).list();
                });
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}