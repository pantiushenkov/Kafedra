package lib.java.dao2.interfaces;

import lib.java.model.Teacher;

public interface TeacherDao extends BaseDao<Teacher> {
    Teacher getTeacherByName(String name);
}
