package lib.java.DAO.Entities;

import lib.java.DAO.Dao;
import lib.java.DAO.PersistException;
import lib.java.Entities.TeacherEntity;
import lib.java.Utils.Config;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TeacherDao extends Dao<TeacherEntity, String> {

    public TeacherDao(Connection connection) {
        super(connection);
    }

    private String key = "scientist_id";
    private String department = "cathedra_id";
    private String position = "position";
    private String title = "degree";
    private String surname = "second_name";
    private String startDate = "start_date";
    private String phone = "phone_number";
    private String sex = "gender";

    private String[] scientist = new String[]{key, surname, phone, sex};
    private String[] teacher = new String[]{key, department, position, title, startDate};
    private String innerJoin = "INNER JOIN scientists s ON teachers.scientist_id = s.scientist_id";

    @Override
    public String getDbName() {
        return Config.getTable("teachers");
    }

    @Override
    public String getCreateQuery() {
        return super.getCreateQuery(scientist, "scientists") +
                super.getCreateQuery(teacher, "teachers");
    }

    @Override
    public String getSelectQuery() {
        return super.getSelectQuery() + innerJoin;
    }

    @Override
    public String getUpdateQuery() {
        return super.getUpdateQuery(key, new String[]{surname, phone, sex}, "scientists") +
                super.getUpdateQuery(key, new String[]{department, position, title, startDate}, "teachers");
    }

    @Override
    public String getDeleteQuery() {
        return super.getDeleteQuery(key);
    }

    @Override
    public String getSearchQuery() {
        return super.getSearchQuery(new String[]{department, position, title, startDate, surname, phone, sex}, innerJoin);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, TeacherEntity object) throws PersistException {
        try {
            if (statement.getParameterMetaData().getParameterCount() == 4) {
                statement.setString(1, object.getKey());
                statement.setString(2, object.getSurname());
                statement.setString(3, object.getPhone());
                statement.setString(4, object.getSex());
            } else {
                statement.setString(1, object.getKey());
                statement.setString(2, object.getDepartment());
                statement.setString(3, object.getPosition());
                statement.setString(4, object.getTitle());
                statement.setDate(5, object.getStartDate());
            }
        } catch (Exception e) {
            throw new PersistException(e.getMessage());
        }
    }

    @Override
    protected void prepareStatementForSearch(PreparedStatement statement, TeacherEntity object) throws PersistException {
        try {
            super.prepareStatementForSearch(statement, new Object[]{
                            object.getDepartment(),
                            object.getPosition(),
                            object.getTitle(),
                            object.getStartDate(),
                            object.getSurname(),
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
            if (statement.getParameterMetaData().getParameterCount() == 4) {
                statement.setString(1, object.getSurname());
                statement.setString(2, object.getPhone());
                statement.setString(3, object.getSex());
                statement.setString(4, object.getKey());
            } else {
                statement.setString(1, object.getDepartment());
                statement.setString(2, object.getPosition());
                statement.setString(3, object.getTitle());
                statement.setDate(4, object.getStartDate());
                statement.setString(5, object.getKey());
            }
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
                        rs.getString(key),
                        rs.getString(department),
                        rs.getString(position),
                        rs.getString(title),
                        rs.getDate(startDate),
                        rs.getString(surname),
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
