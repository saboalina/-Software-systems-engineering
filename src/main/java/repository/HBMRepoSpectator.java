package repository;

import domain.Spectator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.JdbcUtils;

import java.util.Properties;

public class HBMRepoSpectator implements SpectatorRepository{
    private JdbcUtils dbUtils;

    //private static final Logger logger= LogManager.getLogger();

    public HBMRepoSpectator(Properties props) {
        //logger.info("Initializing Repository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Spectator findOne(String s) {
        try(Session session = dbUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Spectator spectator = session.createQuery("from Spectator e where e.username like :id", Spectator.class).setParameter("id", s).setMaxResults(1).uniqueResult();
                //Spectator spectator = session.createQuery("from Spectator where username like '"+s+"'", Spectator.class).getSingleResult();
                tx.commit();
                return spectator;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public Iterable<Spectator> findAll() {
        return null;
    }

    @Override
    public Spectator save(Spectator entity) {
        return null;
    }

    @Override
    public Spectator update(Spectator entity) {
        return null;
    }

    @Override
    public Spectator delete(String s) {
        return null;
    }
/*
    @Override
    public Iterable<Employee> findAll() {
        try(Session session = dbUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
                tx.commit();
                return employees;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Employee save(Employee entity) {
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
    public Employee update(Employee entity) {
        try(Session session = dbUtils.getSessionFactory().openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                Employee employee = session.load( Employee.class, entity.getUsername());
                employee.setPassword(entity.getPassword());
                employee.setName(entity.getName());
                tx.commit();
                return null;
            } catch(RuntimeException ex){
                if (tx!=null)
                    tx.rollback();
            }
        }
        return entity;
    }

    @Override
    public Employee delete(String s) {
        try(Session session = dbUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Employee crit = session.createQuery("from Employee where id like :id", Employee.class)
                        .setParameter("id", s)
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
    }*/
}