package lib.java.DAO.Entities;

import lib.java.DAO.Dao;
import lib.java.DAO.PersistException;
import lib.java.Entities.DepartmentEntity;
import lib.java.Utils.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DepartmentDao extends Dao<DepartmentEntity, String> {

    public DepartmentDao(Connection connection) {
        super(connection);
    }

    private String key = "id";
    private String name = "name";
    private String phone = "phone_number";
    private String tableName = "cathedras";

    private String[] attributes = new String[]{key, name, phone,};

    @Override
    public String getDbName() {
        return Config.getTable(tableName);
    }

    @Override
    public String getCreateQuery() {
        return super.getCreateQuery(attributes, tableName);
    }

    @Override
    public String getUpdateQuery() {
        return super.getUpdateQuery(key, new String[]{name, phone}, tableName);
    }

    @Override
    public String getDeleteQuery() {
        return super.getDeleteQuery(key);
    }

    @Override
    public String getSearchQuery() {
        return super.getSearchQuery(new String[]{name, phone});
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, DepartmentEntity object) throws PersistException {
        try {
            statement.setString(1, object.getKey());
            statement.setString(2, object.getName());
            statement.setString(3, object.getPhone());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForSearch(PreparedStatement statement, DepartmentEntity object) throws PersistException {
        try {
            super.prepareStatementForSearch(
                    statement,
                    new Object[]{object.getName(), object.getPhone()}
            );
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, DepartmentEntity object) throws PersistException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getPhone());
            statement.setString(3, object.getKey());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected List<DepartmentEntity> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<DepartmentEntity> result = new LinkedList<>();

        try {
            while (rs.next()) {
                DepartmentEntity department = new DepartmentEntity(
                        rs.getString(key),
                        rs.getString(name),
                        rs.getString(phone)
                );
                result.add(department);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public DepartmentEntity create() throws PersistException {
        DepartmentEntity department = new DepartmentEntity();
        return persist(department);
    }
}
