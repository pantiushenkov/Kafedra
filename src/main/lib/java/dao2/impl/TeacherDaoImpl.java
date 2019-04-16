package lib.java.dao2.impl;

import lib.java.UI.presenter.petlya.DBdata;
import lib.java.Utils.SQLQueries;
import lib.java.dao2.config.ConnectionFactory;
import lib.java.dao2.interfaces.TeacherDao;
import lib.java.model.Cathedra;
import lib.java.model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public boolean add(Teacher scientist) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            //TODO add check if scientist exists
            PreparedStatement ps = connection.prepareStatement(SQLQueries.INSERT_SCIENSIST);
            String newId = UUID.randomUUID().toString();
            ps.setString(1, newId);
            ps.setString(2, scientist.getSecondName());
            ps.setString(3, scientist.getPhoneNumber());
            ps.setString(4, scientist.getGender());
            int i = ps.executeUpdate();
            ps = connection.prepareStatement(SQLQueries.INSERT_TEACHER);
            ps.setString(1, newId);
            ps.setString(2, scientist.getCathedraId());
            ps.setString(3, scientist.getPosition());
            ps.setString(4, scientist.getDegree());
            i += ps.executeUpdate();
            ps.close();
            if (i == 2) {
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
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
    public boolean delete(String scientistId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQLQueries.DELETE_TEACHER)) {
            //TODO add check if scientist exists
            ps.setString(1, scientistId);
            int i = ps.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Teacher scientist) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQLQueries.UPDATE_SCIENTIST);
            ps.setString(1, scientist.getSecondName());
            ps.setString(2, scientist.getPhoneNumber());
            ps.setString(3, scientist.getGender());
            ps.setString(4, scientist.getScientistId());
            int i = ps.executeUpdate();
            ps = connection.prepareStatement(SQLQueries.UPDATE_TEACHER);
            ps.setString(1, scientist.getCathedraId());
            ps.setString(2, scientist.getPosition());
            ps.setString(3, scientist.getDegree());
            ps.setString(4, scientist.getScientistId());
            i += ps.executeUpdate();
            return i == 2;
        } catch (SQLException se) {
            se.printStackTrace();
        }
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

    @Override
    public List<Teacher> getAllByCathedra(Cathedra cathedra) {
        List<Teacher> teachers = new ArrayList();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLQueries.GET_ALL_TEACHERS_BY_CATHEDRA)) {
            stmt.setString(1, cathedra.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Teacher teacher = extractTeacherFromRS(rs);
                teachers.add(teacher);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return teachers;
    }
}
