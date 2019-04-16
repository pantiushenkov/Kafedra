package lib.java.dao2.interfaces;

import lib.java.UI.presenter.petlya.DBdata;
import lib.java.model.Cathedra;
import lib.java.model.Teacher;

import java.util.List;

public interface TeacherDao extends BaseDao<Teacher> {
    Teacher getTeacherByName(String name);

    List<DBdata> getStatistics();

    List<Teacher> getAllByCathedra(Cathedra cathedra);
}
