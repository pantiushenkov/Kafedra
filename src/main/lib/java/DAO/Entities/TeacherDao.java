package lib.java.DAO.Entities;

import lib.java.DAO.Dao;
import lib.java.DAO.PersistException;
import lib.java.Entities.TeacherEntity;
import lib.java.Utils.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class TeacherDao extends Dao<TeacherEntity, Integer> {

    public TeacherDao(Connection connection) {
        super(connection);
    }

    private String key = "idTeacher";
    private String department = "idDepartment";
    private String position = "Position";
    private String title = "Title";
    private String surname = "Surname";
    private String startDate = "StartDate";
    private String phone = "Phone";
    private String sex = "Sex";

    private String[] params = new String[]{department, position, title, surname, startDate, phone, sex};
    private String[] allParams = new String[]{key, department, position, title, surname, startDate, phone, sex};

    @Override
    public String getDbName() {
        return Config.getTable("Teacher");
    }

    @Override
    public String getCreateQuery() {
        return super.getCreateQuery(allParams);
    }

    @Override
    public String getUpdateQuery() {
        return super.getUpdateQuery(key, params);
    }

    @Override
    public String getDeleteQuery() {
        return super.getDeleteQuery(key);
    }

    @Override
    public String getSearchQuery() {
        return super.getSearchQuery(allParams);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, TeacherEntity object) throws PersistException {
        try {
            statement.setInt(1, object.getKey());
            prepareStatement(statement, object, 2);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    private void prepareStatement(PreparedStatement statement, TeacherEntity object, int start) throws PersistException {
        try {
            statement.setInt(start++, object.getDepartment());
            statement.setString(start++, object.getPosition());
            statement.setString(start++, object.getTitle());
            statement.setString(start++, object.getSurname());
            statement.setDate(start++, object.getStartDate());
            statement.setString(start++, object.getPhone());
            statement.setString(start, object.getSex());
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForSearch(PreparedStatement statement, TeacherEntity object) throws PersistException {
        try {
            super.prepareStatementForSearch(statement, new Object[]{
                            object.getKey(),
                            object.getDepartment(),
                            object.getPosition(),
                            object.getTitle(),
                            object.getSurname(),
                            object.getStartDate(),
                            object.getPhone(),
                            object.getSex(),
                    }
            );
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, TeacherEntity object) throws PersistException {
        try {
            prepareStatement(statement, object, 1);
            statement.setInt(allParams.length, object.getKey());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected List<TeacherEntity> parseResultSet(ResultSet rs) {
        LinkedList<TeacherEntity> result = new LinkedList<>();

        try {
            while (rs.next()) {
                TeacherEntity teacherEntity = new TeacherEntity(
                        rs.getInt(key),
                        rs.getInt(department),
                        rs.getString(position),
                        rs.getString(title),
                        rs.getString(surname),
                        rs.getDate(startDate),
                        rs.getString(phone),
                        rs.getString(sex)
                );
                result.add(teacherEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public TeacherEntity create() throws PersistException {
        TeacherEntity teacherEntity = new TeacherEntity();
        return persist(teacherEntity);
    }
}
