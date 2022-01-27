package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.model.User;
import peaksoft.util.HibernateUtil;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
            Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users(" +
                    "id serial not null," +
                    "name VARCHAR(50) NOT NULL," +
                    "lastName VARCHAR(50) NOT NULL," +
                    "age int not null" +
                    ");";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
            System.out.println("table is creat");
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
            Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery("drop table users").executeUpdate();
            session.getTransaction().commit();
            System.out.println("table is dropped");
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
            Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
            Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("user is removed");
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
            Session session =sessionFactory.openSession();
        try {
            session.beginTransaction();
            List getUser = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            System.out.println("Found :" + getUser.size());
            return getUser;
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }finally {
            session.close();
        }
    }
    @Override
    public void cleanUsersTable() {
            Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
}
