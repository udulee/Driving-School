package lk.ijse.drivingschool.dao;

import lk.ijse.drivingschool.entity.Course;

import java.util.List;

public interface CrudDao<S> extends SuperDao {
    List<S> getAll();

    boolean save(S course);

    boolean delete(String id);

    boolean update(S course);

    S getOne(String id);
}
