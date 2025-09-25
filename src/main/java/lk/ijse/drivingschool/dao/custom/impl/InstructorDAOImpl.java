package lk.ijse.drivingschool.dao.custom.impl;

import lk.ijse.drivingschool.dao.custom.InstructorDAO;
import lk.ijse.drivingschool.config.FactoryConfiguration;
import lk.ijse.drivingschool.entity.Instructor;
import lk.ijse.drivingschool.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class InstructorDAOImpl implements InstructorDAO {


    @Override
    public List<Instructor> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Query<Instructor> query = session.createQuery("FROM Instructor ", Instructor.class);
            return query.list();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean save(Instructor instructor) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(instructor);
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
            Instructor instructor = session.get(Instructor.class, id);
            if (instructor != null) {
                session.remove(instructor);
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
    public boolean update(Instructor instructor) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(instructor);
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Instructor getOne(String id){
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Instructor instructor = session.get(Instructor.class,id);
            return Optional.ofNullable(instructor).orElse(null);
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

}
