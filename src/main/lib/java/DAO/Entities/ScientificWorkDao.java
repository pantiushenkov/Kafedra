package lib.java.DAO.Entities;

import lib.java.DAO.Dao;
import lib.java.DAO.PersistException;
import lib.java.Entities.ScientificWorkEntity;
import lib.java.Utils.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.LinkedList;
import java.util.List;

public class ScientificWorkDao extends Dao<ScientificWorkEntity, Integer> {

    public ScientificWorkDao(Connection connection) {
        super(connection);
    }

    private String key = "idScientificWork";
    private String department = "idDepartment";
    private String name = "Name";
    private String manager = "Manager";
    private String customer = "Customer";
    private String start = "Start";
    private String end = "End";
    private String[] params = new String[]{department, name, manager, customer, start, end};
    private String[] allParams = new String[]{key, department, name, manager, customer, start, end};

    @Override
    public String getDbName() {
        return Config.getTable("ScientificWork");
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
    protected void prepareStatementForInsert(PreparedStatement statement, ScientificWorkEntity object) throws PersistException {
        try {
            statement.setInt(1, object.getKey());
            prepareStatement(statement, object, 2);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    private void prepareStatement(PreparedStatement statement, ScientificWorkEntity object, int start) throws PersistException {
        try {
            statement.setInt(start++, object.getDepartment());
            statement.setString(start++, object.getName());
            statement.setString(start++, object.getManager());
            statement.setString(start++, object.getCustomer());
            statement.setDate(start++, object.getStart());
            statement.setDate(start, object.getEnd());
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForSearch(PreparedStatement statement, ScientificWorkEntity object) throws PersistException {
        try {
            super.prepareStatementForSearch(statement, new Object[]{
                            object.getKey(), object.getDepartment(), object.getName(), object.getManager(), object.getCustomer(), object.getStart(), object.getEnd()
                    }
            );
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ScientificWorkEntity object) throws PersistException {
        try {
            prepareStatement(statement, object, 1);
            statement.setInt(allParams.length, object.getKey());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected List<ScientificWorkEntity> parseResultSet(ResultSet rs) {
        LinkedList<ScientificWorkEntity> result = new LinkedList<>();

        try {
            while (rs.next()) {
                ScientificWorkEntity scientificWorkEntity = new ScientificWorkEntity(
                        rs.getInt(key),
                        rs.getInt(department),
                        rs.getString(name),
                        rs.getString(manager),
                        rs.getString(customer),
                        rs.getDate(start),
                        rs.getDate(end)
                );
                result.add(scientificWorkEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ScientificWorkEntity create() throws PersistException {
        ScientificWorkEntity scientificWorkEntity = new ScientificWorkEntity();
        return persist(scientificWorkEntity);
    }
}
