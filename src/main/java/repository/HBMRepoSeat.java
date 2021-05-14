package repository;

import domain.Seat;
import domain.Show;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HBMRepoSeat implements SeatRepository {
    private JdbcUtils dbUtils;

    public HBMRepoSeat(Properties props) {
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public Seat findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Seat> findAll() {
        try(Session session = dbUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Seat> seats = session.createQuery("from Seat", Seat.class).list();
                tx.commit();
                return seats;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Seat save(Seat entity) {
        return null;
    }

    @Override
    public Seat update(Seat entity) {
        return null;
    }

    @Override
    public Seat delete(Integer integer) {
        return null;
    }
}