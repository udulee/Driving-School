package lk.ijse.drivingschool.dao.custom.impl;

import lk.ijse.drivingschool.dao.custom.CourseDAO;
import lk.ijse.drivingschool.config.FactoryConfiguration;
import lk.ijse.drivingschool.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public List<Course> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Query<Course> query = session.createQuery("FROM Course ", Course.class);
            return query.list();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean save(Course course) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(course);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }


    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Course course = (Course) session.get(Course.class, id);
            if (course != null) {
                session.remove(course);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Course course) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(course);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Course getOne(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Course course = session.get(Course.class, id);
            return Optional.ofNullable(course).orElse(null);
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }
}
