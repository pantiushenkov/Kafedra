package lib.java.dao2.impl;

import lib.java.UI.presenter.petlya.DBdata;
import lib.java.Utils.SQLQueries;
import lib.java.dao2.config.ConnectionFactory;
import lib.java.dao2.interfaces.TeacherDao;
import lib.java.model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public Teacher getById(String id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_TEACHER_BY_ID)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractTeacherFromRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Teacher extractTeacherFromRS(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setScientistId(rs.getString("scientist_id"));
        teacher.setSecondName(rs.getString("second_name"));
        teacher.setPhoneNumber(rs.getString("phone_number"));
        teacher.setGender(rs.getString("gender"));
        teacher.setCathedraId(rs.getString("cathedra_id"));
        teacher.setPosition(rs.getString("position"));
        teacher.setDegree(rs.getString("degree"));
        return teacher;
    }

    @Override
    public boolean add(Teacher teacher) {
        return false;
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_TEACHERS);
            while (rs.next()) {
                Teacher teacher = extractTeacherFromRS(rs);
                teachers.add(teacher);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return teachers;
    }

    @Override
    public boolean delete(String teacher) {
        return false;
    }

    @Override
    public boolean update(Teacher teacher) {
        return false;
    }

    @Override
    public Teacher getTeacherByName(String name) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_TEACHER_BY_NAME)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractTeacherFromRS(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DBdata> getStatistics() {
        List<DBdata> teachers = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection(); Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_TEACHERS);
            while (rs.next()) {
                teachers.add(new DBdata(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return teachers;
    }
}
