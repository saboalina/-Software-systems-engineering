package repository;

import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private Properties jdbcProps;

    public JdbcUtils(Properties props){
        jdbcProps=props;
    }

    private Connection instance=null;

    private Connection getNewConnection(){

        String url=jdbcProps.getProperty("jdbc.url");
        String user=jdbcProps.getProperty("jdbc.user");
        String pass=jdbcProps.getProperty("jdbc.pass");
        Connection con=null;
        try {

            if (user!=null && pass!=null)
                con= DriverManager.getConnection(url,user,pass);
            else
                con= DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return instance;
    }

    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            initialize();
        }
        return sessionFactory;
    }

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }
}
