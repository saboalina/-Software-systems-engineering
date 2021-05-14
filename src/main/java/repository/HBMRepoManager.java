package repository;

import domain.Manager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Properties;

public class HBMRepoManager implements ManagerRepository {
    private JdbcUtils dbUtils;

    //private static final Logger logger= LogManager.getLogger();

    public HBMRepoManager(Properties props) {
        //logger.info("Initializing Repository with properties: {} ",props);
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public Manager findOne(String s) {
        try (Session session = dbUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Manager manager = session.createQuery("from Manager e where e.username like :id", Manager.class).setParameter("id", s).setMaxResults(1).uniqueResult();
                //Spectator spectator = session.createQuery("from Spectator where username like '"+s+"'", Spectator.class).getSingleResult();
                tx.commit();
                return manager;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }

    @Override
    public Iterable<Manager> findAll() {
        return null;
    }

    @Override
    public Manager save(Manager entity) {
        return null;
    }

    @Override
    public Manager update(Manager entity) {
        return null;
    }

    @Override
    public Manager delete(String s) {
        return null;
    }
}