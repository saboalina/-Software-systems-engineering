package repository;

import domain.Manager;
import domain.Show;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HBMRepoShow implements ShowRepository{
    private JdbcUtils dbUtils;

    public HBMRepoShow(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Show findOne(Integer id) {
        Iterable<Show> all_shows = findAll();
        for (Show s:all_shows)
            if(s.getId()==id)
                return s;
        return null;
    }

    @Override
    public Iterable<Show> findAll() {
        try(Session session = dbUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Show> shows = session.createQuery("from Show", Show.class).list();
                tx.commit();
                return shows;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Show save(Show entity) {
        try(Session session = dbUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
                return null;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return entity;
    }

    @Override
    public Show update(Show entity) {
        return null;
    }

    @Override
    public Show delete(Integer integer) {
        try(Session session = dbUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Show crit = session.createQuery("from Show where id like :id", Show.class)
                        .setParameter("id", integer)
                        .setMaxResults(1)
                        .uniqueResult();
                session.delete(crit);
                tx.commit();
                return crit;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

}
