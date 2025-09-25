package lk.ijse.drivingschool.dao.custom.impl;

import lk.ijse.drivingschool.dao.custom.LessonDAO;
import lk.ijse.drivingschool.config.FactoryConfiguration;
import lk.ijse.drivingschool.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class LessonDAOImpl implements LessonDAO {
    @Override
    public List<Lesson> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Query<Lesson> query = session.createQuery("FROM Lesson", Lesson.class);
            return query.list();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }    }

    @Override
    public boolean save(Lesson lesson) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(lesson);
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
    public boolean delete(String lessonId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Lesson lesson = (Lesson) session.get(Lesson.class, lessonId);
            if (lesson != null) {
                session.remove(lesson);
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
    public boolean update(Lesson lesson) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(lesson);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            return false;
        } finally {
            session.close();
        }    }

    @Override
    public Lesson getOne(String lessonId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Lesson lesson = session.get(Lesson.class,lessonId);
            return Optional.ofNullable(lesson).orElse(null);
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }
}
